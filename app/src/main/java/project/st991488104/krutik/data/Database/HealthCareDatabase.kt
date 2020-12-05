package project.st991488104.krutik.data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import project.st991488104.krutik.data.Converter
import project.st991488104.krutik.data.Dao.ExerciseDao
import project.st991488104.krutik.data.Dao.ToDoDao
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.data.models.ToDoData

@Database(entities = [ToDoData::class, ExerciseData::class], version = 3, exportSchema = false)
@TypeConverters(Converter::class)
abstract class HealthCareDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: HealthCareDatabase? = null

        fun getDatabase(context: Context): HealthCareDatabase =
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(
                            context
                        )
                            .also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HealthCareDatabase::class.java, "healthcare_database"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}