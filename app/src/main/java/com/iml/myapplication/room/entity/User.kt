package client.modules.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "userName")
    var userName: String? = null

    @ColumnInfo(name = "userId")
    var userId: String? = null

    @ColumnInfo(name = "token")
    var token: String? = null
}