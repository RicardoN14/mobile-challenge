package pt.unbabel.demo.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.unbabel.demo.databases.daos.CommentsDao
import pt.unbabel.demo.databases.daos.PostsDao
import pt.unbabel.demo.databases.daos.UsersDao
import pt.unbabel.demo.databases.entities.CommentDbEntity
import pt.unbabel.demo.databases.entities.PostDbEntity
import pt.unbabel.demo.databases.entities.UserDbEntity

/**
 * Created by Ricardo Neves on 22/09/2019.
 */
@Database(
    entities = [UserDbEntity::class, PostDbEntity::class, CommentDbEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UnbabelDatabase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDao
    abstract fun getCommentsDao(): CommentsDao
    abstract fun getPostsDao(): PostsDao
}