package com.gsm.networking.ui.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gsm.networking.R
import com.gsm.networking.ui.theme.GSMNetworkingTheme
import com.gsm.networking.util.CheckBackHandler

@Composable
fun IntentPlayStoreDialog(
    packageName: String,
    finishAction: () -> Unit,
) {
    val context = LocalContext.current
    GSMNetworkingTheme {
        Dialog(onDismissRequest = { }) {
            CheckBackHandler(finishAction = finishAction)
            Column(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 30.dp, horizontal = 15.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(60.dp),
                        painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "업데이트 정보가 있습니다",
                        style = GSMNetworkingTheme.typography.h4
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "업데이트를 진행해주세요",
                    style = GSMNetworkingTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("market://details?id=$packageName")
                        context.startActivity(intent)
                    }) {
                    Text(
                        text = "플레이 스토어로 이동",
                        style = GSMNetworkingTheme.typography.button
                    )
                }
            }
        }
    }
}