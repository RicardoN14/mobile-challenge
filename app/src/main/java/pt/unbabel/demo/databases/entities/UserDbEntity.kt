package pt.unbabel.demo.databases.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import pt.unbabel.demo.databases.*

/**
 * Created by Ricardo Neves on 22/09/2019.
 */

@Parcelize
@Entity(tableName = USERS_TABLE_NAME)
data class UserDbEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = USERS_ID_COLUMN_NAME) var id: Int? = null,
    @ColumnInfo(name = USERS_NAME_COLUMN_NAME) var name: String? = null,
    @ColumnInfo(name = USERS_USERNAME_COLUMN_NAME) var username: String? = null,
    @ColumnInfo(name = USERS_EMAIL_COLUMN_NAME) var email: String? = null,
    @ColumnInfo(name = USERS_PHONE_COLUMN_NAME) var phone: String? = null,
    @ColumnInfo(name = USERS_WEBSITE_COLUMN_NAME) var website: String? = null
) : Parcelable