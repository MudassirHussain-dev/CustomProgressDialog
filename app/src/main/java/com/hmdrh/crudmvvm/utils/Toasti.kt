package com.hmdrh.crudmvvm.utils

import android.content.Context
import android.widget.Toast

fun Context.Toasti(s: String) {
    Toast.makeText(applicationContext, s,Toast.LENGTH_SHORT).show()
}