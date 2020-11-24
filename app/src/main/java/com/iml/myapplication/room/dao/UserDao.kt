package client.modules.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import client.modules.room.entity.User
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Maybe<List<User>>

    @Insert
    fun insert(user: User): Completable

    @Delete
    fun delete(user: User)
}