package com.example.sportik.presentation.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SettingsCompose() {
    val checkedState = remember { mutableStateOf(false) }
    val textColor = remember { mutableStateOf(Color.Unspecified) }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Тёмная тема",
                fontSize = 22.sp,
                color = textColor.value,
                modifier = Modifier.padding(8.dp, 0.dp)
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
    }
}
