package com.app.incomebalance.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.incomebalance.contracts.MainContract
import com.app.incomebalance.presenters.MainPresenter


open class BaseActivity : AppCompatActivity(), MainContract.View {

    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = MainPresenter()
    }


    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun getViewContext(): Context {
        return baseContext
    }
}