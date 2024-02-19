package com.prafullkumar.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [FavouriteEntity::class], version = 1)
@TypeConverters(SourceTypeConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}