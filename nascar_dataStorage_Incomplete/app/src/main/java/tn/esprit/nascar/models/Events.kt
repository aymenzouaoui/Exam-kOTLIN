package tn.esprit.nascar.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO 8 Change this class to a data class with Entity annotation and the val id to PrimaryKey with auto generation

@Entity
data class Events(

    @PrimaryKey(true)
    val id: Int,

    val imageRes: Int,

    val title: String,

    val description: String,

)