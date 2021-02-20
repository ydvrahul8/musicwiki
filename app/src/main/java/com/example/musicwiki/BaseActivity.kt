package com.example.musicwiki

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity<T : ViewDataBinding?> : DaggerAppCompatActivity() {
    abstract val layout: Int @LayoutRes get
    abstract val toolbarVisible: Boolean

    abstract fun init(bind: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<T>(this, layout)
        if (toolbarVisible) {
            setToolbar()
        }
        init(binding!!)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        toolbar.title = ""
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}