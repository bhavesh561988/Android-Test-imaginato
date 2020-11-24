package client.modules.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import client.modules.room.dao.UserDao
import client.modules.room.entity.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}