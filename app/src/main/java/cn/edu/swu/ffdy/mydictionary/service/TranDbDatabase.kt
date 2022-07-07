/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.edu.swu.ffdy.mydictionary.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cn.edu.swu.ffdy.mydictionary.data.Converters
import cn.edu.swu.ffdy.mydictionary.data.TranItemDb
import cn.edu.swu.ffdy.mydictionary.data.TranSearchDb
import cn.edu.swu.ffdy.mydictionary.MainActivity

@Database(entities = [TranSearchDb::class, TranItemDb::class], version = 1)
@TypeConverters(Converters::class)
abstract class TranDbDatabase:RoomDatabase() {
    //    abstract fun todoDao(): TodoDao
    abstract fun tranSearchDbDao(): TranSearchDbDao
    abstract fun tranItemDbDao():TranItemDbDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: TranDbDatabase? = null

        fun getInstance(context: MainActivity): TranDbDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): TranDbDatabase {
            return Room.databaseBuilder(context, TranDbDatabase::class.java, "dictionary.db")

                .allowMainThreadQueries()
                .build()
        }
    }
}