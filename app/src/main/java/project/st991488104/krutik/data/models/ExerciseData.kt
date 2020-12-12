package project.st991488104.krutik.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize

//@Entity(tableName = "exercise_table")
@Entity(tableName = "exercise_table",foreignKeys = [ForeignKey(entity = AccountData::class,
    parentColumns = arrayOf("accountId"),
    childColumns = arrayOf("accountId"),
    onDelete = ForeignKey.CASCADE)]
)

data class ExerciseData(
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Int,
    var name: String,
    var date: Date,
    var accountId: Int
): Parcelable