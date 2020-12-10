package project.st991488104.krutik.data.repository

import androidx.lifecycle.LiveData
import project.st991488104.krutik.data.Dao.AccountDao
import project.st991488104.krutik.data.models.AccountData

class AccountRepository(private val accountDao: AccountDao)  {

    val getAllData: LiveData<List<AccountData>> = accountDao.getAllData()

    suspend fun insertData(accountData: AccountData){
        accountDao.insertData(accountData)
    }

    suspend fun updateData(accountData: AccountData){
        accountDao.updateData(accountData)
    }

    suspend fun deleteItem(accountData: AccountData){
        accountDao.deleteItem(accountData)
    }

     fun loadAccount(accountEmail:String,accountPass:String) :Int {
       return accountDao.loadAccount(accountEmail,accountPass)
    }



}