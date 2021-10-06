package com.app.incomebalance.di

import android.content.Context
import com.app.incomebalance.contracts.*
import com.app.incomebalance.data.DatabaseTransactionRepository
import com.app.incomebalance.data.TransactionDatabase
import com.app.incomebalance.domain.repository.TransactionRepository
import com.app.incomebalance.presentation.MainActivity
import com.app.incomebalance.presentation.MainPresenter
import com.app.incomebalance.presentation.balance.BalanceFragment
import com.app.incomebalance.presentation.balance.BalancePresenter
import com.app.incomebalance.presentation.pages.PagePresenter
import com.app.incomebalance.presentation.pages.PagerFragment
import com.app.incomebalance.presentation.transaction_edit.CreateEditBaseFragment
import com.app.incomebalance.presentation.transaction_edit.CreateTransactionFragment
import com.app.incomebalance.presentation.transaction_edit.EditTransactionFragment
import com.app.incomebalance.presentation.transaction_edit.EditTransactionPresenter
import com.app.incomebalance.presentation.transactions.TransactionListFragment
import com.app.incomebalance.presentation.transactions.TransactionListPresenter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NavigationModule::class])
interface AppComponent {
    fun inject(transactionListFragment: TransactionListFragment)
    fun inject(balanceFragment: BalanceFragment)
    fun inject(editTransactionFragment: EditTransactionFragment)
    fun inject(createTransactionFragment: CreateTransactionFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(createEditBaseFragment: CreateEditBaseFragment)
    fun inject(pagerFragment: PagerFragment)
}

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = context

    @Provides
    @Singleton
    fun provideRepository(repository: DatabaseTransactionRepository): TransactionRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(context: Context): TransactionDatabase {
        return TransactionDatabase.getInstance(context)!!
    }

    @Provides
    fun provideMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter {
        return mainPresenter
    }//TODO create providers for all presenters through interfaces

    @Provides
    fun providePagePresenter(pagePresenter: PagePresenter): PagesContract.Presenter {
        return pagePresenter
    }

    @Provides
    fun provideBalancePresenter(balancePresenter: BalancePresenter): BalanceContract.Presenter {
        return balancePresenter
    }

    @Provides
    fun provideTransactionListPresenter(transactionListPresenter: TransactionListPresenter)
            : TransactionListContract.Presenter {
        return transactionListPresenter
    }

    @Provides
    fun provideEditTransactionPresenter(editTransactionPresenter: EditTransactionPresenter)
            : BaseEditTransactionContract.Presenter {
        return editTransactionPresenter
    }
}

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}