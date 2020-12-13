package project.st991488104.krutik.data.Database
//Krutik Parikh | 991488104
//991435185 Nathaniel Kawal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import project.st991488104.krutik.data.Converters.Converter
import project.st991488104.krutik.data.Dao.AccountDao
import project.st991488104.krutik.data.Dao.ExerciseDao
import project.st991488104.krutik.data.Dao.ToDoDao
import project.st991488104.krutik.data.models.AccountData
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.data.models.ToDoData

@Database(entities = [ToDoData::class, ExerciseData::class, AccountData::class], version = 5, exportSchema = false)
@TypeConverters(Converter::class)
abstract class HealthCareDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun accountDao(): AccountDao

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