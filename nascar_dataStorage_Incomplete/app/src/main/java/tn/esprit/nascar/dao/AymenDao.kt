package tn.esprit.nascar.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import tn.esprit.nascar.models.Aymen
import tn.esprit.nascar.models.Events
import tn.esprit.nascar.models.User


@Dao
interface AymenDao {

    @Insert
    fun insetUser(aymen: Aymen)
    @Delete
    fun deletUser(user: Aymen)

    @Update
    fun updateUser(user: Aymen)

    @Query("select * from aymen ")
    fun  getAllUser():MutableList<Aymen>

    @Query("select * from aymen where id = :id")
    fun getuser(id: Int): Aymen?


}