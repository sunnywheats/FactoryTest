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
            //Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.DISABLE_KEYGUARD,
            Manifest.permission.VIBRATE,
            //Manifest.permission.BLUETOOTH_ADVERTISE,
            //Manifest.permission.BLUETOOTH_PRIVILEGED,
            //Manifest.permission.CALL_PRIVILEGED,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WAKE_LOCK,
            //Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            //Manifest.permission.WRITE_SECURE_SETTINGS,
            //Manifest.permission.WRITE_SETTINGS,
            //Manifest.permission.DELETE_PACKAGES,
            //Manifest.permission.REBOOT,
            //Manifest.permission.BLUETOOTH_CONNECT,
            //Manifest.permission.BLUETOOTH_SCAN,
            //Manifest.permission.CONFIGURE_WIFI_DISPLAY,
            //Manifest.permission.OVERRIDE_WIFI_CONFIG,
            //Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.USE_BIOMETRIC,
            //Manifest.permission.MASTER_CLEAR
        )
    }

    private lateinit var mMenuRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init view
        initView()
        setContentView(mMenuRecyclerView)
        initMenu()
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
            //initMenu()
        } else if (shouldShowRequest()) {
            val text =
                "The permission is denied, the application cannot be used normally, please set permissions!"
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
                Log.d(TAG, "permission = " + permission);
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
            //if (granted) initMenu()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

