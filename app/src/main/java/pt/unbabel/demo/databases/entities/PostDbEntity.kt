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
@Entity(tableName = POSTS_TABLE_NAME)
data class PostDbEntity(
    @ColumnInfo(name = POSTS_USER_ID_COLUMN_NAME) var userId: Int? = null,
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = POSTS_ID_COLUMN_NAME) var id: Int? = null,
    @ColumnInfo(name = POSTS_TITLE_COLUMN_NAME) var title: String? = null,
    @ColumnInfo(name = POSTS_DESCRIPTION_COLUMN_NAME) var description: String? = null
) : Parcelable