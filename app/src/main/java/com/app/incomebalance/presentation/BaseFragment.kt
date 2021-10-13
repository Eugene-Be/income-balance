package com.app.incomebalance.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.app.incomebalance.common.DispatcherProvider
import com.app.incomebalance.contracts.BaseContract
import javax.inject.Inject

abstract class BaseFragment <V: ViewBinding,P:BaseContract.Presenter> : Fragment(), BaseContract.View {
    private var _binding: V? = null
    internal val binding get() = _binding!!

    @Inject
    lateinit var presenter: P

    @Inject
    lateinit var dispatchers: DispatcherProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(container)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    abstract fun getBinding(container: ViewGroup?): V
}