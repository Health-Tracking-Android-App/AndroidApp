package project.st991488104.krutik.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "account_table")
@Parcelize
data class AccountData(
    @PrimaryKey(autoGenerate = true)
    var accountId: Int,
    var email: String,
    var password: String


): Parcelable