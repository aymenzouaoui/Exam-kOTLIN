package tn.esprit.nascar.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Movie (


    @PrimaryKey(true)
    val id:Int =0,
    val name:String,
    val description:String)



