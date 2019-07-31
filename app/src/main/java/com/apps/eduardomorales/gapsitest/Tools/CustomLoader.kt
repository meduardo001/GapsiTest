package com.apps.eduardomorales.gapsitest.Tools

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.apps.eduardomorales.gapsitest.R
import kotlinx.android.synthetic.main.loader_layout.view.*


class CustomLoader constructor(context: Activity) {
    private var dialog: Dialog

    init {

        var inflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view: View = inflator.inflate(R.layout.loader_layout,null)

        dialog = Dialog(context,R.style.LoaderStyle)
        view.loader.setAnimation("loader_animation.json")
        dialog.setContentView(view)
    }

    fun initLoader() {
        if(!dialog.isShowing)
            dialog.show()
    }

    fun closeLoader() {
        if(dialog.isShowing)
            dialog.dismiss()
    }
}