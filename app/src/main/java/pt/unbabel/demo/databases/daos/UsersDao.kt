package pt.unbabel.demo.databases.daos

import androidx.room.*
import pt.unbabel.demo.databases.*
import pt.unbabel.demo.databases.entities.UserDbEntity

/**
 * Created by Ricardo Neves on 22/09/2019.
 */

@Dao
interface UsersDao {

    @Query("SELECT * FROM $USERS_TABLE_NAME")
    fun getAll(): List<UserDbEntity>

    @Query("SELECT * FROM $USERS_TABLE_NAME WHERE $USERS_ID_COLUMN_NAME = :id")
    fun getById(id: Int): UserDbEntity?

    @Insert
    fun insert(vararg user: UserDbEntity)

    @Query("DELETE from $USERS_TABLE_NAME")
    fun deleteAll()

}