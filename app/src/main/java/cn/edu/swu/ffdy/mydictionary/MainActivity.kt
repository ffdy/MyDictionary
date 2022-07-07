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
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var manager: FragmentManager

//    companion object TAGS{
//        val TAG_CHAT = "chat"
//        val TAG_LINKMAN = "linkman"
//        val TAG_FIND = "find"
//        val TAG_MY = "my"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        manager = supportFragmentManager
        val tran = manager.beginTransaction()
        tran.replace(R.id.nav_host, MainFragment()).commit()
//        tran.add(R.id.nav_host, MainFragment()).commit()
        val buttomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        buttomNavigationView.selectedItemId = R.id.main_search
        buttomNavigationView.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.main_dict -> {
                        // Respond to navigation item 1 click
                        Log.d("@@", "change fra")
                        val tran = manager.beginTransaction()
                        tran.replace(R.id.nav_host, MyFragment()).commit()
                        true
                    }
                    R.id.main_search -> {
                        // Respond to navigation item 2 click
                        Log.d("@@", "change fra")
                        val tran = manager.beginTransaction()
                        tran.replace(R.id.nav_host, MainFragment()).commit()
                        true
                    }
                    R.id.main_setting -> {
                        Log.d("@@", "change fra")
                        val tran = manager.beginTransaction()
                        tran.replace(R.id.nav_host, SettingFragment()).commit()
                        true
                    }
                    else -> false
                }
            }
        )

        val floatBtn = findViewById<FloatingActionButton>(R.id.btn_float)
        floatBtn.setOnClickListener{
            val tran = manager.beginTransaction()
            tran.replace(R.id.nav_host, MainFragment()).commit()
            buttomNavigationView.selectedItemId = R.id.main_search
            findViewById<EditText>(R.id.main_edit)
//            editText.requestFocus()
        }

//        showFragment(TAG_CHAT)
    }

//    fun showFragment(tag:String) {
//        val tran = manager.beginTransaction()
//
//        if(tag == TAG_CHAT) {
//            tran.replace(R.id.fragment_container_view, ChatFragment()).commit()
//        } else if (tag == TAG_LINKMAN) {
//            tran.replace(R.id.fragment_container_view, LinkmanFragment()).commit()
//        } else if (tag == TAG_FIND) {
//            tran.replace(R.id.fragment_container_view, FindFragment()).commit()
//        } else if (tag == TAG_MY) {
//            tran.replace(R.id.fragment_container_view, MyFragment()).commit()
//        }
//    }

}