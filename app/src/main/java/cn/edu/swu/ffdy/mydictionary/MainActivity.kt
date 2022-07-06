package cn.edu.swu.ffdy.mydictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.main_dict -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.main_search -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.main_setting -> {
                    true
                }
                else -> false
            }
        }
    }
}