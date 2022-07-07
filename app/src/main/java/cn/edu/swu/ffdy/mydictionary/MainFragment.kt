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
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.edu.swu.ffdy.mydictionary.R
import cn.edu.swu.ffdy.mydictionary.adapter.TranSearchDbAdapter
import cn.edu.swu.ffdy.mydictionary.data.TranData
import cn.edu.swu.ffdy.mydictionary.data.TranItemDb
import cn.edu.swu.ffdy.mydictionary.data.TranSearchDb
import cn.edu.swu.ffdy.mydictionary.service.BaiduTranApiServer
import cn.edu.swu.ffdy.mydictionary.service.ServiceCreator
import cn.edu.swu.ffdy.mydictionary.service.TranDbDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.util.*

class MainFragment : Fragment() {
    lateinit var inputDesc:String
    lateinit var tranSearchDbAdapter: TranSearchDbAdapter
//    lateinit var db:TranDbDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.main_edit)
        ed
        val searchBtn = view.findViewById<Button>(R.id.main_btn_search)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_history)

//        val db = TranDbDatabase.getInstance(MainActivity())
        tranSearchDbAdapter = TranSearchDbAdapter(TranDbDatabase.getInstance(context as MainActivity).tranSearchDbDao().getAll())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = tranSearchDbAdapter

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("@@","beforeTextChanged : $s")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("@@","onTextChanged : $s")
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i("@@","afterTextChanged : $s")
//                password = s?.toString()
                inputDesc = s.toString()
            }
        })

        searchBtn.setOnClickListener {
            val baiduTranApiServer = ServiceCreator.create(BaiduTranApiServer::class.java)
            Log.d("@@", "check button")

            val APPID = "20220706001266121"
            val SALT = "123456"
            val KEY = "STMw9LUYnIo6KZ0HWPYM"
            baiduTranApiServer.getTran(inputDesc, "en", "zh", APPID, SALT, md5(APPID+inputDesc+SALT+KEY))
                .enqueue(object : Callback<TranData> {
                    override fun onResponse(call: Call<TranData>, response: Response<TranData>) {
                        val tranData = response.body()
                        Log.d("@@", "getNetWork" + tranData.toString())
                        if (tranData != null) {
//                            tranText.text = tranData.tranResult[0].dst+ ":" +tranData.tranResult[0].src

                            val tranSearchDb = TranSearchDb(0,tranData.tranResult[0].src, tranData.tranResult[0].dst, Date())
                            val db = TranDbDatabase.getInstance(MainActivity())
                            db.tranSearchDbDao().insertOne(tranSearchDb)
                            val tranItemDb = db.tranItemDbDao().getItemByDst(inputDesc)
                            tranSearchDbAdapter.addItem(tranSearchDb)

                            if(tranItemDb.isEmpty()) {
                                Log.d("@@", "new item")
                                db.tranItemDbDao().insertOne(TranItemDb(0, tranData.from, tranData.to,
                                    tranData.tranResult[0].src, tranData.tranResult[0].dst, Date()))
                            }

                        }

                    }

                    override fun onFailure(call: Call<TranData>, t: Throwable) {
                        Log.d("@@", "error")
                        t.printStackTrace()
                    }

                })
//            recyclerView?.adapter.add
//            recyclerView
            Log.d("@@", "check search btn")

//            Thread() {
//                MainActivity().db.todoDao().insertOne(newTodo)
//            }
//                TodoDatabase.getInstance(MainActivity()).todoDao().insertOne(newTodo)
//            MainActivity().todoAdapter.notifyItemInserted()
//            db.todoDao().insertOne(newTodo)
//            todoAdapter.addItem(newTodo)
        }
    }

    fun md5(content: String): String {
        val hash = MessageDigest.getInstance("MD5").digest(content.toByteArray())
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            var str = Integer.toHexString(b.toInt())
            if (b < 0x10) {
                str = "0$str"
            }
            hex.append(str.substring(str.length -2))
        }
        return hex.toString()
    }
}