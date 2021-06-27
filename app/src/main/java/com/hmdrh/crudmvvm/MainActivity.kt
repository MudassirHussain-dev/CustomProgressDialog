package com.hmdrh.crudmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hmdrh.crudmvvm.connectivitymanager.MyConnectivityManagers
import com.hmdrh.crudmvvm.models.LoginResponse
import com.hmdrh.crudmvvm.models.RegisterResponse
import com.hmdrh.crudmvvm.models.nested_model.GSLogin
import com.hmdrh.crudmvvm.mvvm.MainViewModel
import com.hmdrh.crudmvvm.mvvm.MainViewModelFactory
import com.hmdrh.crudmvvm.repository.Repository
import com.hmdrh.crudmvvm.utils.LoadingDialog
import com.hmdrh.crudmvvm.utils.Toasti
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    lateinit var repository: Repository
    lateinit var registerResponse: RegisterResponse
    lateinit var loginResponse: LoginResponse

    lateinit var loading: LoadingDialog

    val TAG = "main"

    lateinit var myConnectivityManagers: MyConnectivityManagers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loading = LoadingDialog(this)


        repository = Repository()

        mainViewModel = ViewModelProvider(
            this@MainActivity,
            MainViewModelFactory(repository)
        ).get(MainViewModel::class.java)

        myConnectivityManagers = MyConnectivityManagers(application)

        btn_Signup.setOnClickListener {
            Snackbar.make(
                it,
                "UserID",
                Snackbar.LENGTH_SHORT
            ).show()

            myConnectivityManagers.observe(this, {
                when (it) {
                    true -> {
                        loading.startLoading()
                        SignupTask()
                        // Toasti("Connection Success")
                    }

                    false

                    -> Toasti("Connection Failed...")
                }
            })


        }
        /*Snackbar.make(
              mView,
              "UserID: ${currentPost.userId}\nId: ${currentPost.id}",
              Snackbar.LENGTH_SHORT
          ).show()*/

        btn_Login.setOnClickListener {
            mainViewModel.Login(
                et_Email.text.toString(),
                et_Password.text.toString()
            )

            mainViewModel.loginResponse.observe(this@MainActivity, Observer {
                loginResponse = it.body() as LoginResponse
                Toasti(loginResponse.message)
                //Toasti(loginResponse.user.username)
            })
        }

    }

    private fun SignupTask() {

        val gsLogin = GSLogin(
            et_UserName.text.toString(),
            et_Email.text.toString(),
            et_Password.text.toString()
        )

        mainViewModel.RegisterResponse(
            /* et_UserName.text.toString(),
             et_Email.text.toString(),
             et_Password.text.toString()*/
            gsLogin
        )
        mainViewModel._registernResponse.observe(this@MainActivity, Observer {
            loading.isDismiss()
            if (it.isSuccessful) {
                registerResponse = it.body() as RegisterResponse
                Toasti(registerResponse.message)
            } else {
                Toasti(registerResponse.error)
            }
        })
    }
}