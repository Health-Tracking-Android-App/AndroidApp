package project.st991488104.krutik.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import project.st991488104.krutik.data.Database.HealthCareDatabase
import project.st991488104.krutik.data.models.AccountData
import project.st991488104.krutik.data.models.ToDoData
import project.st991488104.krutik.data.repository.AccountRepository


class AccountViewModel (application: Application) : AndroidViewModel(application) {

    private val accountDao = HealthCareDatabase.getDatabase(
        application
    ).accountDao()

    private val repository: AccountRepository

    val getAllData: LiveData<List<AccountData>>

    init {
        repository = AccountRepository(accountDao)
        getAllData = repository.getAllData

    }
    fun insertData(accountData: AccountData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(accountData)
        }
    }

//    fun loadEmail(accountEmail:String, accountPass:String) : LiveData<AccountData>{
//        return repository.loadAccount(accountEmail, accountPass)
//    }

        fun loadEmail(accountEmail:String, accountPass:String) : LiveData<Int>{
        return repository.loadAccount(accountEmail, accountPass)
    }

        fun checkEmail(accountEmail:String) : LiveData<Int>{
        return repository.checkAccount(accountEmail)
    }
}



