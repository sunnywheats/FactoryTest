package com.hxy.factorytest.item

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hxy.factorytest.R
import com.hxy.factorytest.data.TestSpf
import com.hxy.factorytest.util.Const

@Composable
fun TestButton(passVisible: Boolean,passClick: () -> Unit,failClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        if(passVisible) {
            Button(
                onClick = { passClick() }
            ) {
                Text(
                    text = stringResource(id = R.string.pass),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Button(
            onClick = { failClick() }
        ) {
            Text(
                text = stringResource(id = R.string.fail),
                fontSize = 24.sp
            )
        }
    }
}

fun Pass(activity: Activity,key:String){
    TestSpf(activity.baseContext).setState(key,Const.PASS)
    activity?.finish()
}
fun Fail(activity: Activity,key:String){
    TestSpf(activity.baseContext).setState(key,Const.FAIL)
    activity?.finish()
}