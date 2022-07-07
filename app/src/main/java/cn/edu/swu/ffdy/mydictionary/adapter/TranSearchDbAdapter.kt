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

package cn.edu.swu.ffdy.mydictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.edu.swu.ffdy.mydictionary.R
import cn.edu.swu.ffdy.mydictionary.data.TranSearchDb
import cn.edu.swu.ffdy.mydictionary.service.TranDbDatabase
import cn.edu.swu.ffdy.mydictionary.MainActivity

class TranSearchDbAdapter(private val tranSearchDbList:List<TranSearchDb>): RecyclerView.Adapter<TranSearchDbAdapter.TranSearchDbViewHolder>() {
    val tranSearchDbs = ArrayList(tranSearchDbList)

    class TranSearchDbViewHolder(itemView: View, tranSearchDbAdapter: TranSearchDbAdapter):RecyclerView.ViewHolder(itemView) {
        val dstText = itemView.findViewById<TextView>(R.id.history_item_text_dst)
        val srcText = itemView.findViewById<TextView>(R.id.history_item_text_src)
        val deleteBtn = itemView.findViewById<ImageButton>(R.id.history_item_btn_delete)
        var currencyItem: TranSearchDb? = null

        init {
            deleteBtn.setOnClickListener {
                val db = TranDbDatabase.getInstance(MainActivity())
                currencyItem?.let { it1 -> db.tranSearchDbDao().deleteOne(it1)
                    tranSearchDbAdapter.removeItem(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranSearchDbViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent, false)
        val holder = TranSearchDbViewHolder(view,  this)
        return holder
    }

    override fun onBindViewHolder(holder: TranSearchDbViewHolder, position: Int) {
        val tranSearchDb = tranSearchDbs[position]
        holder.currencyItem = tranSearchDb
        holder.dstText.text = tranSearchDb.dst
        holder.srcText.text = tranSearchDb.src
    }

    override fun getItemCount(): Int {
        return tranSearchDbs.size
    }

    fun addItem(tranSearchDb: TranSearchDb) {
        tranSearchDbs.add(0, tranSearchDb)
        notifyItemInserted(0)
    }

    fun removeItem(position: Int) {
        tranSearchDbs.removeAt(position)
        notifyItemRemoved(position)
    }
}