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

package cn.edu.swu.ffdy.mydictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.edu.swu.ffdy.mydictionary.R
import cn.edu.swu.ffdy.mydictionary.adapter.TranItemDbAdapter
import cn.edu.swu.ffdy.mydictionary.service.TranDbDatabase


class MyFragment : Fragment() {

//    val db = TranDbDatabase.getInstance(context as MainActivity)
    lateinit var tranItemDbAdapter: TranItemDbAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private fun initView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_my)
        tranItemDbAdapter = TranItemDbAdapter(TranDbDatabase.getInstance(context as MainActivity).tranItemDbDao().getAll())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = tranItemDbAdapter
    }
}