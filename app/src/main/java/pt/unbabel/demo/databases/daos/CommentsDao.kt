package pt.unbabel.demo.databases.daos

import androidx.room.*
import pt.unbabel.demo.databases.*
import pt.unbabel.demo.databases.entities.CommentDbEntity

/**
 * Created by Ricardo Neves on 22/09/2019.
 */

@Dao
interface CommentsDao {

    @Query("SELECT * FROM $COMMENTS_TABLE_NAME")
    fun getAll(): List<CommentDbEntity>

    @Query("SELECT * FROM $COMMENTS_TABLE_NAME WHERE $COMMENTS_POST_ID_COLUMN_NAME = :postId")
    fun getAllFromPost(postId: Int): List<CommentDbEntity>

    @Insert
    fun insert(vararg user: CommentDbEntity)

    @Query("DELETE from $COMMENTS_TABLE_NAME")
    fun deleteAll()

}