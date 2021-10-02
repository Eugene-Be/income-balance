package com.app.incomebalance.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.app.incomebalance.R
import com.app.incomebalance.databinding.FragmentPagerBinding
import com.app.incomebalance.domain.TransactionType
import com.app.incomebalance.view.balance.BalanceFragment
import com.app.incomebalance.view.transactions.TransactionListFragment

class PagerFragment : BaseFragment() {
    private var _binding: FragmentPagerBinding? = null
    private val binding get() = _binding!!

    val TAG = "PagerFragmentTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentPagerBinding.bind(inflater.inflate(R.layout.fragment_pager, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "onViewCreated: ")
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = TransactionPagesAdapter(this)
        binding.viewPager.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PagerFragment()
    }
}

class TransactionPagesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val TAG = "PagesAdapterTag"

    init {
        Log.i(TAG, "createFragment:")
    }

    override fun getItemCount(): Int {
        Log.i(TAG, "getItemCount:")
        return TransactionType.values().size
    }



    override fun createFragment(position: Int): Fragment {
        Log.i(TAG, "createFragment: $position")
        return when (position) {
            0 -> TransactionListFragment.newInstance(TransactionType.OUTCOME.ordinal)
            1 -> TransactionListFragment.newInstance(TransactionType.INCOME.ordinal)
            2 -> BalanceFragment.newInstance()
            else -> throw IllegalArgumentException()
        }
    }
}
