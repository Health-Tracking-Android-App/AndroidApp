package project.st991488104.krutik.data.models
//Krutik Parikh | 991488104

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    var taskId: Int,
    var title: String,
    var priority: Priority,
    var description: String,
    var exerciseId: Int
): Parcelable