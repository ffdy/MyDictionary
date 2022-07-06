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

import androidx.room.*
import cn.edu.swu.ffdy.mydictionary.data.TranSearchDb

@Dao
interface TranSearchDbDao {
    @Query("SELECT * FROM history ORDER BY last DESC LIMIT 50")
    fun getAll(): List<TranSearchDb>

    @Query("SELECT * FROM history WHERE dst = :dst")
    fun getItemByDst(dst: String): List<TranSearchDb>

    @Query("SELECT * FROM history WHERE src = :src")
    fun getItemBySrc(src: String): List<TranSearchDb>

    @Insert
    fun insertOne(tranSearchDb: TranSearchDb)
}