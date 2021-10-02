package com.app.incomebalance.view

import androidx.fragment.app.Fragment
import com.app.incomebalance.contracts.BaseContract
import javax.inject.Inject

open class BaseFragment: Fragment() {


    private var _presenter: BaseContract.Presenter? = null

    override fun onDetach() {
        super.onDetach()
        _presenter = null
    }
}