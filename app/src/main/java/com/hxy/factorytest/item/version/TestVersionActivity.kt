package com.hxy.factorytest.item.version

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hxy.factorytest.item.Fail
import com.hxy.factorytest.item.Pass
import com.hxy.factorytest.item.TestButton
import com.hxy.factorytest.item.ui.theme.FactoryTestTheme
import com.hxy.factorytest.util.Const

class TestVersionActivity : ComponentActivity() {
    private lateinit var values: Map<String, String>
    private var passVisible = true

    fun initVersionInfo() {
        val android = Build.VERSION.RELEASE
        val prop = "prop_version"
        val build = "display_version"
        val imei1 = VersionInfo().getIMEI(this,1).toString()
        val imei2 = VersionInfo().getIMEI(this,2).toString()
        val sn1 = VersionInfo().getSN(1).toString()
        val sn2 = VersionInfo().getSN(2).toString()
        values = mapOf(
            "android" to android, "prop" to prop, "build" to build,
            "imei1" to imei1, "imei2" to imei2, "sn1" to sn1, "sn2" to sn2
        )
        if (values.any { it == null }) {
            passVisible = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVersionInfo();
        setContent {
            FactoryTestTheme {
                // A surface container using the 'background' color from the theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VersionScreen(
                        values,
                        passVisible,
                        passClick = { Pass(this, Const.KEY_VERSION) },
                        failClick = { Fail(this, Const.KEY_VERSION) }
                    )
                }
            }
        }
    }
}

@Composable
fun VersionScreen(
    values: Map<String, String>,
    passVisible: Boolean,
    passClick: () -> Unit,
    failClick: () -> Unit
) {
    val android = values["android"] ?: "11"
    val prop = values["prop"] ?: ""
    val build = values["build"] ?: ""
    val imei1 = values["imei1"] ?: "000000000000000"
    val imei2 = values["imei2"] ?: "000000000000000"
    val sn1 = values["sn1"] ?: "123456789"
    val sn2 = values["sn2"] ?: "123456789"
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        VersionInfo(android, prop, build, imei1, imei2, sn1, sn2)
        Spacer(Modifier.weight(1f))
        TestButton(
            passVisible,
            passClick = passClick,
            failClick = failClick
        )
    }
}

@Composable
fun VersionInfo(
    android: String, prop: String, build: String,
    imei1: String, imei2: String, sn1: String, sn2: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row {
            Text("Android Version: ")
            Text(text = android)
        }
        Row {
            Text("Prop Version: ")
            Text(text = prop)
        }
        Row {
            Text("Build Version: ")
            Text(text = build)
        }
        Row {
            Text("Device IMEI1: ")
            Text(text = imei1)
        }
        Row {
            Text("Device IMEI2: ")
            Text(text = imei2)
        }
        Row {
            Text("SN1: ")
            Text(text = sn1)
        }
        Row {
            Text("SN2: ")
            Text(text = sn2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FactoryTestPreview() {
    FactoryTestTheme {
        VersionScreen(
            mapOf(),
            true,
            passClick = {},
            failClick = {}
        )
    }
}