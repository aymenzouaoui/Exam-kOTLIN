package tn.esprit.nascar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import tn.esprit.nascar.models.Movie
import tn.esprit.nascar.models.User

@Dao
interface MovieDao {


    @Insert
    fun insetMovie(movie: Movie)

    @Query("select * from movie ")
    fun  getAllmovie():MutableList<Movie>

}