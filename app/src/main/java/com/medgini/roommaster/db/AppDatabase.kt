package com.medgini.roommaster.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.medgini.roommaster.model.Todos

@Database(entities = [Todos::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    //singleton approach
    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "TodoDb"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}