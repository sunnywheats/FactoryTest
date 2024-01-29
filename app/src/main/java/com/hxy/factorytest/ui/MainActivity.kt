package com.hxy.factorytest.ui

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.LayoutParams
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        private const val PERMISSIONS_REQUEST_CODE = 1
        private val PERMISSIONS_REQUEST = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            //Manifest.permission.READ_EXTERNAL_STORAGE,
            //Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private lateinit var mMenuRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init view
        initView()
        setContentView(mMenuRecyclerView)

    }

    override fun onResume() {
        super.onResume()
        checkPermission()
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
    private fun initMenu() {
        Log.d(TAG, "initMenu()");
        val menus = TestRepository(LocalDataSource(this)).getMainTest()
        //adapter
        val menuAdapter = TestItemAdapter(menus) { menu -> adapterOnClick(menu) }
        mMenuRecyclerView.adapter = menuAdapter
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

    private fun checkPermission() {
        if (hasPermission()) {
            initMenu()
        } else if (shouldShowRequest()) {
            val text =
                "The permission is denied, the application cannot be used normally, it will exit soon"
            Toast.makeText(this,text,Toast.LENGTH_LONG).show()
        } else {
            // You can directly ask for the permission.
            requestPermissions(
                PERMISSIONS_REQUEST,
                PERMISSIONS_REQUEST_CODE
            )
        }
    }

    private fun shouldShowRequest(): Boolean {
        for (permission in PERMISSIONS_REQUEST) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return true
            }
        }
        return false
    }

    private fun hasPermission(): Boolean {
        for (permission in PERMISSIONS_REQUEST) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (PERMISSIONS_REQUEST_CODE == requestCode) {
            var granted = true
            for (result in grantResults) {
                if (PackageManager.PERMISSION_GRANTED != result) {
                    granted = false
                    break
                }
            }
            Log.d(TAG, "granted = " + granted);
            if (granted) initMenu()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

