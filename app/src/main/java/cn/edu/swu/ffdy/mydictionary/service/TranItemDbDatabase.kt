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
import android.util.Log
import android.view.View
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import cn.edu.swu.ffdy.mydictionary.data.Converters
import cn.edu.swu.ffdy.mydictionary.data.TranItemDb
import cn.edu.swu.ffdy.mydictionary.ui.MainActivity

@Database(entities = [TranItemDb::class], version = 1)
@TypeConverters(Converters::class)
abstract class TranItemDbDatabase:RoomDatabase() {
//    abstract fun todoDao(): TodoDao
    abstract fun tranItemDbDao():TranItemDbDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: TranItemDbDatabase? = null

        fun getInstance(context: MainActivity): TranItemDbDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): TranItemDbDatabase {
            val DATABASE_NAME = "todo.db"
            return Room.databaseBuilder(context, TranItemDbDatabase::class.java, DATABASE_NAME)
//                .addCallback(
//                    object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
////                            val nowTime = java.util.Date()
////                            Log.d("@@", nowTime.toString())
////                            todoDao.insertOne(TodoEntity(0, "test", null, nowTime))
////
////                            val todos:List<TodoEntity> = todoDao.getAll()
////                            Log.d("@@", todos.toString())
////                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
////                                .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
////                                .build()
////                            WorkManager.getInstance(context).enqueue(request)
//                        }
//                    }
//                )
                .allowMainThreadQueries()
                .build()
        }
    }
}