package com.hxy.factorytest.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hxy.factorytest.adapters.TestItemAdapter
import com.hxy.factorytest.data.LocalDataSource
import com.hxy.factorytest.data.TestRepository
import com.hxy.factorytest.data.model.TestItem

class TestItemActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TestItemActivity"
    }

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init view
        initView()
        setContentView(mRecyclerView)
    }

    override fun onResume() {
        super.onResume()
        //init menu
        val tests = TestRepository(LocalDataSource(this)).getAllTest()
        //adapter
        val menuAdapter = TestItemAdapter(tests) { test -> adapterOnClick(test) }
        mRecyclerView.adapter = menuAdapter
    }
    private fun initView() {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        mRecyclerView = RecyclerView(this)
        mRecyclerView.layoutParams = params;
        mRecyclerView.setBackgroundColor(Color.BLACK)
        mRecyclerView.layoutManager = GridLayoutManager(this,2)
    }

    private fun adapterOnClick(test: TestItem) {
        Log.d(TAG, "test = " + test.name);
        try {
            val intent = Intent()
            intent.setClassName(packageName, packageName+test.cls)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.d(TAG, "ActivityNotFoundException e = " + e.message);
        }
    }

}

