package project.st991488104.krutik.data.Dao

import android.accounts.Account
import androidx.lifecycle.LiveData
import androidx.room.*
import project.st991488104.krutik.data.models.AccountData
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.data.models.ToDoData


@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(accountData: AccountData)

    @Query("SELECT * FROM account_table ORDER BY accountId ASC")
    fun getAllData(): LiveData<List<AccountData>>

    @Update
    suspend fun updateData(accountData: AccountData)

    @Delete
    suspend fun deleteItem(accountData: AccountData)


    @Query("SELECT * FROM account_table WHERE email LIKE :email AND password LIKE :password")
    fun loadAccount(email: String, password: String): LiveData<AccountData>


}
