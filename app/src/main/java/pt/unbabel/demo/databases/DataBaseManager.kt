package pt.unbabel.demo.databases

import android.content.Context
import androidx.room.Room
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pt.unbabel.demo.databases.daos.CommentsDao
import pt.unbabel.demo.databases.daos.PostsDao
import pt.unbabel.demo.databases.daos.UsersDao
import pt.unbabel.demo.databases.entities.CommentDbEntity
import pt.unbabel.demo.databases.entities.PostDbEntity
import pt.unbabel.demo.databases.entities.UserDbEntity

/**
 * Created by Ricardo Neves on 22/09/2019.
 *
 * This manager don't extends BaseManager because is used by Application (that is not an Screen)
 */
class DataBaseManager(applicationContext: Context) {

    companion object {
        const val DATABASE_NAME: String = "UnbabelDatabase"
    }

    private val unbabelDatabase: UnbabelDatabase by lazy {
        Room.databaseBuilder(applicationContext, UnbabelDatabase::class.java, DATABASE_NAME).build()
    }

    private val usersDao: UsersDao by lazy {
        unbabelDatabase.getUsersDao()
    }

    private val postsDao: PostsDao by lazy {
        unbabelDatabase.getPostsDao()
    }

    private val commentsDao: CommentsDao by lazy {
        unbabelDatabase.getCommentsDao()
    }

    /** region Users */

    fun replaceUsers(users: Array<UserDbEntity>, afterAction: (() -> Unit)? = null) {
        doAsync {
            unbabelDatabase.runInTransaction {
                usersDao.apply {
                    deleteAll()
                    insert(*users)
                }
            }
            uiThread {
                afterAction?.invoke()
            }
        }
    }

    fun getUserById(id: Int, userResponseAction: (UserDbEntity?) -> Unit) {
        doAsync {
            val user = usersDao.getById(id)
            uiThread {
                userResponseAction(user)
            }
        }
    }

    fun gerAllUsers(usersResponseAction: (ArrayList<UserDbEntity>) -> Unit) {
        doAsync {
            val users = usersDao.getAll()
            uiThread {
                usersResponseAction(users as ArrayList<UserDbEntity>)
            }
        }
    }

    /** endregion **/

    /** region Posts */

    fun replacePosts(posts: Array<PostDbEntity>, afterAction: (() -> Unit)? = null) {
        doAsync {
            unbabelDatabase.runInTransaction {
                postsDao.apply {
                    deleteAll()
                    insert(*posts)
                }
            }
            uiThread {
                afterAction?.invoke()
            }
        }
    }

    fun getAllPosts(postsResponseAction: (ArrayList<PostDbEntity>) -> Unit) {
        doAsync {
            val posts = postsDao.getAll()
            uiThread {
                postsResponseAction(posts as ArrayList<PostDbEntity>)
            }
        }
    }

    /** endregion **/

    /** region Comments */

    fun replaceComments(comments: Array<CommentDbEntity>, afterAction: (() -> Unit)? = null) {
        doAsync {
            unbabelDatabase.runInTransaction {
                commentsDao.apply {
                    deleteAll()
                    insert(*comments)
                }
            }
            uiThread {
                afterAction?.invoke()
            }
        }
    }

    fun getAllPostComments(
        postId: Int,
        commentsResponseAction: (ArrayList<CommentDbEntity>) -> Unit
    ) {
        doAsync {
            val comments = commentsDao.getAllFromPost(postId)
            uiThread {
                commentsResponseAction(comments as ArrayList<CommentDbEntity>)
            }
        }
    }

    fun getAllComments(commentsResponseAction: (ArrayList<CommentDbEntity>) -> Unit) {
        doAsync {
            val comments = commentsDao.getAll()
            uiThread {
                commentsResponseAction(comments as ArrayList<CommentDbEntity>)
            }
        }
    }

    /** endregion **/
}