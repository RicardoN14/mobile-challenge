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
@Entity(tableName = COMMENTS_TABLE_NAME)
data class CommentDbEntity(
    @ColumnInfo(name = COMMENTS_POST_ID_COLUMN_NAME) var postId: Int? = null,
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = COMMENTS_ID_COLUMN_NAME) var id: Int? = null,
    @ColumnInfo(name = COMMENTS_NAME_COLUMN_NAME) var name: String? = null,
    @ColumnInfo(name = COMMENTS_EMAIL_COLUMN_NAME) var email: String? = null,
    @ColumnInfo(name = COMMENTS_DESCRIPTION_COLUMN_NAME) var description: String? = null
) : Parcelable