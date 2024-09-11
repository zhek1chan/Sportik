package com.example.sportik.presentation.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sportik.R
import com.example.sportik.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return ComposeView(requireContext()).apply {
            setContent {
                //
                SettingsCompose()

            }
        }
    }

    private fun deleteAllFavs() {
        //viemodel.deleteAllFavs()
        Log.d("SettingsFragment", "Delete all favs")
    }

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
                        deleteAllFavs()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}