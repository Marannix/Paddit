package com.example.paddit.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.example.paddit.R

// A loading dialog is shown to indicate to the user something is happening in the background
class FullscreenLoadingDialog(context: Context) : Dialog(context, R.style.FullscreenLoading) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(R.layout.fullscreen_layout)
    }
}