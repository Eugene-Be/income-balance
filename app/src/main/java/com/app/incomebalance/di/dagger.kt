package com.app.incomebalance.di

import android.content.Context
import com.app.incomebalance.data.DatabaseTransactionRepository
import com.app.incomebalance.data.TransactionRepository
import com.app.incomebalance.view.balance.BalanceFragment
import com.app.incomebalance.view.balance.BalancePresenter
import com.app.incomebalance.view.transactionEdit.CreateTransactionFragment
import com.app.incomebalance.view.transactionEdit.EditTransactionFragment
import com.app.incomebalance.view.transactions.TransactionListFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(transactionListFragment: TransactionListFragment)
    fun inject(balanceFragment: BalanceFragment)
    fun inject(editTransactionFragment: EditTransactionFragment)
    fun inject(createTransactionFragment: CreateTransactionFragment)

    //    fun inject(detailsFragment: DetailsFragment)
    var repository: DatabaseTransactionRepository
}

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = context

    @Provides
    @Singleton
    fun provideRepository(repository: DatabaseTransactionRepository): TransactionRepository{
        return repository
    }
}

