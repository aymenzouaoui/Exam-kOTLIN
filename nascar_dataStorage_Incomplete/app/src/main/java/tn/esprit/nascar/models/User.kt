package tn.esprit.nascar.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(true)
    val id:Int =0,
    val email:String,
    val password:String)