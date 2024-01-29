package com.hxy.factorytest.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hxy.factorytest.R
import com.hxy.factorytest.adapters.TestItemAdapter
import com.hxy.factorytest.data.LocalDataSource
import com.hxy.factorytest.data.TestRepository
import com.hxy.factorytest.data.model.TestItem

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var mMenuRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init view
        initView()
        setContentView(mMenuRecyclerView)
        //init menu
        val menus = TestRepository(LocalDataSource(this)).getMainTest()
        //adapter
        val menuAdapter = TestItemAdapter(menus) { menu -> adapterOnClick(menu) }
        mMenuRecyclerView.adapter = menuAdapter
    }

    private fun initView() {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        mMenuRecyclerView = RecyclerView(this)
        mMenuRecyclerView.layoutParams = params;
        mMenuRecyclerView.setBackgroundColor(Color.BLACK)
        mMenuRecyclerView.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        getDrawable(R.drawable.divider)?.let { divider.setDrawable(it) }
        mMenuRecyclerView.addItemDecoration(divider)
    }

    private fun adapterOnClick(currentMenu: TestItem) {
        Log.d(TAG, "currentMenu = " + currentMenu.name);
        try {
            val intent = Intent()
            intent.setClassName(packageName, packageName + currentMenu.cls)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.d(TAG, "ActivityNotFoundException e = " + e.message);
        }
    }

}

