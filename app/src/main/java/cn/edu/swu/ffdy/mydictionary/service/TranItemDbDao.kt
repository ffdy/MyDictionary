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

import androidx.room.*
import cn.edu.swu.ffdy.mydictionary.data.TranItemDb

@Dao
interface TranItemDbDao {
    @Query("SELECT * FROM dictionary ORDER BY created DESC")
    fun getAll(): List<TranItemDb>

    @Query("SELECT * FROM dictionary WHERE dst = :dst")
    fun getItemByDst(dst:String): List<TranItemDb>

    @Query("SELECT * FROM dictionary WHERE src = :src")
    fun getItemBySrc(src:String): List<TranItemDb>

    @Insert
    fun insertOne(tranItemDb: TranItemDb)

    @Delete
    fun deleteOne(tranItemDb: TranItemDb)
//
//    @Update
//    fun updateOne(todo: TodoEntity)
//
//    @Delete
//    fun deleteOne(todo: TodoEntity)

//    @Query("SELECT * FROM todo WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<TodoEntity>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): TodoEntity
//
//    @Insert
//    fun insertAll(vararg users: User)
//
//    @Delete
//    fun delete(user: User)
}