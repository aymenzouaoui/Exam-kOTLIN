package tn.esprit.nascar.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Aymen( @PrimaryKey(true)
                  val id:Int =0,
                  val nom:String,
                  val poid:Int)

