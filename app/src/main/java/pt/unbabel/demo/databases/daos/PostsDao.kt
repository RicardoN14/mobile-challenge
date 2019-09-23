package pt.unbabel.demo.databases.daos

import androidx.room.*
import pt.unbabel.demo.databases.*
import pt.unbabel.demo.databases.entities.PostDbEntity

/**
 * Created by Ricardo Neves on 22/09/2019.
 */

@Dao
interface PostsDao {

    @Query("SELECT * FROM $POSTS_TABLE_NAME")
    fun getAll(): List<PostDbEntity>

    @Insert
    fun insert(vararg user: PostDbEntity)

    @Query("DELETE from $POSTS_TABLE_NAME")
    fun deleteAll()

}