package project.st991488104.krutik.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "exercise_table")
@Parcelize
data class ExerciseData(
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Int,
    var name: String
): Parcelable