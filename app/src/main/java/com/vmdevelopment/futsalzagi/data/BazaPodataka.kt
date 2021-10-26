package com.vmdevelopment.futsalzagi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vmdevelopment.futsalzagi.data.models.Clan
import com.vmdevelopment.futsalzagi.data.models.Clanarina

@Database(entities = [Clan::class, Clanarina::class], version = 3, exportSchema = false)
@TypeConverters(Converter::class)
abstract class BazaPodataka: RoomDatabase() {

    abstract fun clanoviDao(): ClanoviDao

    companion object {

        @Volatile
        private var INSTANCE: BazaPodataka? = null

        fun stvoriBazuPodataka(context: Context): BazaPodataka {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BazaPodataka::class.java,
                    "clanovi_baza"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}