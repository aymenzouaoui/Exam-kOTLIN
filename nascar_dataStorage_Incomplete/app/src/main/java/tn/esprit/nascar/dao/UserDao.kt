package tn.esprit.nascar.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import tn.esprit.nascar.models.Events
import tn.esprit.nascar.models.User


@Dao
interface UserDao {

    @Insert
    fun insetUser(user: User)
    @Delete
    fun deletUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("select * from user ")
    fun  getAllUser():MutableList<User>

    @Query("select * from user where id = :id")
    fun getuser(id: Int): User?


}