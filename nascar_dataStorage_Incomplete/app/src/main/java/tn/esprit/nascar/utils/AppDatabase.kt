package tn.esprit.nascar.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tn.esprit.nascar.dao.AymenDao
import tn.esprit.nascar.dao.EventDao
import tn.esprit.nascar.dao.MovieDao
import tn.esprit.nascar.dao.UserDao
import tn.esprit.nascar.models.Aymen
import tn.esprit.nascar.models.Events
import tn.esprit.nascar.models.Movie
import tn.esprit.nascar.models.User
import java.lang.reflect.Modifier

//TODO 10 Change this class to an abstract class that inherit from ROOMDatabase
@Database(entities = [Events::class, User::class,Movie::class,Aymen::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {


    abstract fun eventDao(): EventDao
    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
    abstract fun aymenDao(): AymenDao

    companion object {
        //TODO 11 Apply the Design Pattern singleton
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "db_even")
                .allowMainThreadQueries()
                .build()
        }

    }
}