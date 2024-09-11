package com.example.sportik.presentation.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportik.R

@Preview
@Composable
fun SettingsCompose() {
    val checkedState = remember { mutableStateOf(false) }
    val textColor = remember { mutableStateOf(Color.Unspecified) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 25.dp)
        ) {
            Icon(
                modifier = Modifier.padding(0.dp, 0.dp),
                painter = painterResource(R.drawable.ic_theme),
                contentDescription = "CommentIcon",
                tint = Color.Unspecified
            )
            Text(
                "Тёмная тема",
                fontSize = 22.sp,
                color = textColor.value,
                modifier = Modifier.padding(16.dp, 0.dp)
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Switch(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    checkedTrackColor = Color.LightGray,
                    uncheckedThumbColor = Color.Black,
                    uncheckedTrackColor = Color.LightGray
                )
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                modifier = Modifier.padding(0.dp, 0.dp),
                painter = painterResource(R.drawable.icon_trashcan),
                contentDescription = "CommentIcon",
                tint = Color.Unspecified
            )
            Text(
                "Очистить избранное",
                fontSize = 22.sp,
                color = textColor.value,
                modifier = Modifier.padding(16.dp, 0.dp)
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}
