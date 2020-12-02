package project.st991488104.krutik.data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import project.st991488104.krutik.data.models.ExerciseData

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercise_table ORDER BY exerciseId ASC")
    fun getAllData(): LiveData<List<ExerciseData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(exerciseData: ExerciseData)

    @Update
    suspend fun updateData(exerciseData: ExerciseData)

    @Delete
    suspend fun deleteItem(exerciseData: ExerciseData)

    @Query("DELETE FROM exercise_table")
    suspend fun deleteAll()

}