package tn.esprit.nascar.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import tn.esprit.nascar.models.Events


//TODO 9 Change this interface to a DAO and add 4 methods with the proper annotation:
// insertEvent(events: Events)
// deleteEvent(events: Events)
// getAllEvent() : MutableList<Events>
// getFindEventById(id: Int) : Events

@Dao
interface EventDao {

    @Insert
    fun insertEvent(events: Events)

    @Delete
    fun deleteEvent(events: Events)

    @Update
    fun updateEvent(events: Events)


    @Query("select * from events")
    fun getAllEvent(): MutableList<Events>
/*
    @Query("select * from events where id = :id")
    fun getEventById(id: Int): Events?
*/
}