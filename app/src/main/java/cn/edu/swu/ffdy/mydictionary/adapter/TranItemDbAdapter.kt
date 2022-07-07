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
import cn.edu.swu.ffdy.mydictionary.data.TranItemDb
import cn.edu.swu.ffdy.mydictionary.service.TranDbDatabase
import cn.edu.swu.ffdy.mydictionary.MainActivity

class TranItemDbAdapter(tranItemDbList:List<TranItemDb>): RecyclerView.Adapter<TranItemDbAdapter.TranItemDbViewHolder>() {
    private val tranItemDbs = ArrayList(tranItemDbList)

    class TranItemDbViewHolder(itemView: View, tranItemDbAdapter: TranItemDbAdapter):
        RecyclerView.ViewHolder(itemView) {
        val dstText: TextView = itemView.findViewById(R.id.history_item_text_dst)
        val srcText: TextView = itemView.findViewById(R.id.history_item_text_src)
        private val deleteBtn: ImageButton = itemView.findViewById(R.id.history_item_btn_delete)
        var currencyItem: TranItemDb? = null

        init {
            deleteBtn.setOnClickListener {
                val db = TranDbDatabase.getInstance(MainActivity())
                currencyItem?.let { it1 -> db.tranItemDbDao().deleteOne(it1)
                    tranItemDbAdapter.removeItem(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranItemDbViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return TranItemDbViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: TranItemDbViewHolder, position: Int) {
        val tranItemDb = tranItemDbs[position]
        holder.currencyItem = tranItemDb
        holder.dstText.text = tranItemDb.dst
        holder.srcText.text = tranItemDb.src
    }

    override fun getItemCount(): Int {
        return tranItemDbs.size
    }

    private fun removeItem(position: Int) {
        tranItemDbs.removeAt(position)
        notifyItemRemoved(position)
    }
}