package com.app.incomebalance.presentation.pages

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.incomebalance.R
import com.app.incomebalance.common.TransactionType
import com.app.incomebalance.common.appComponent
import com.app.incomebalance.contracts.PagesContract
import com.app.incomebalance.databinding.FragmentPagerBinding
import com.app.incomebalance.presentation.BaseFragment
import com.app.incomebalance.presentation.balance.BalanceFragment
import com.app.incomebalance.presentation.transactions.TransactionListFragment
import com.google.android.material.tabs.TabLayoutMediator

class PagerFragment : BaseFragment<FragmentPagerBinding,PagesContract.Presenter>(),PagesContract.View {

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewPager() {
        val adapter = TransactionPagesAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.tab_titles)[position]
        }.attach()

        adapter.notifyDataSetChanged()
    }

    override fun getBinding(container: ViewGroup?): FragmentPagerBinding {
        return FragmentPagerBinding.inflate(layoutInflater,container,false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PagerFragment()
    }
}

class TransactionPagesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return TransactionType.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TransactionListFragment.newInstance(TransactionType.OUTCOME.ordinal)
            1 -> TransactionListFragment.newInstance(TransactionType.INCOME.ordinal)
            2 -> BalanceFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }
}
