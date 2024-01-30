package com.hxy.factorytest.item.version

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FactoryTestTheme {
                // A surface container using the 'background' color from the theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val versionInfo = VersionInfo()
                    VersionScreen(
                        versionInfo.getVersionInfo(),
                        versionInfo.passVisible,
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
    results: Map<String, String>,
    passVisible: Boolean,
    passClick: () -> Unit,
    failClick: () -> Unit
) {
    val android = results["android"] ?: "11"
    val prop = results["prop"] ?: ""
    val build = results["build"] ?: ""
    val imei1 = results["imei1"] ?: "000000000000000"
    val imei2 = results["imei2"] ?: "000000000000000"
    val sn1 = results["sn1"] ?: "123456789"
    val sn2 = results["sn2"] ?: "123456789"
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