package project.st991488104.krutik.data.Dao
//Krutik Parikh | 991488104

import androidx.lifecycle.LiveData
import androidx.room.*
import project.st991488104.krutik.data.models.ToDoData

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table WHERE exerciseId LIKE :exerciseId")
    fun getAllData(exerciseId: Int): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery AND exerciseId LIKE :exerciseId")
    fun searchDatabase(searchQuery: String, exerciseId: Int): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table WHERE exerciseId LIKE :exerciseId ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(exerciseId: Int): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table WHERE exerciseId LIKE :exerciseId ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(exerciseId: Int): LiveData<List<ToDoData>>

}