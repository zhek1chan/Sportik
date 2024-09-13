package com.example.sportik.presentation.ui.settings

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sportik.R
import com.example.sportik.presentation.themes.ComposeTheme
import com.example.sportik.presentation.ui.favourites.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel: FavouriteViewModel by viewModels()
    private var changedTheme: String = "null"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        changedTheme = sharedPref.getString(getString(R.string.changed_theme), changedTheme).toString()

        return ComposeView(requireContext()).apply {
            setContent {
                SettingsCompose()
            }
        }
    }

    private fun deleteAllFavs() {
        viewModel.deleteAll()
        Log.d("SettingsFragment", "Deleted all favs")
    }

    private fun changeTheme() {
        val darkMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val isDarkModeOn = darkMode == Configuration.UI_MODE_NIGHT_YES
        if (isDarkModeOn) {
            changedTheme = "white"
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            changedTheme = "black"
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    @Preview
    @Composable
    fun SettingsCompose() {
        ComposeTheme {
            var checkedState by remember { mutableStateOf(false) }
            ConstraintLayout {
                val (settings, description) = createRefs()
                Column(modifier = Modifier.constrainAs(settings) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
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
                            stringResource(R.string.dark_theme),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(16.dp, 0.dp)
                        )
                        Spacer(
                            Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                        if (changedTheme == "black") {
                            GradientSwitch(
                                checked = true,
                                onCheckedChange = {
                                    checkedState = it
                                    changeTheme()
                                })
                        } else {
                            GradientSwitch(
                                checked = checkedState,
                                onCheckedChange = {
                                    checkedState = it
                                    changeTheme()
                                })
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { deleteAllFavs() }
                    ) {
                        Icon(
                            modifier = Modifier.padding(0.dp, 0.dp),
                            painter = painterResource(R.drawable.icon_trashcan),
                            contentDescription = "TrashIcon",
                            tint = Color.Unspecified
                        )
                        Text(
                            stringResource(R.string.clear_favs),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(16.dp, 0.dp)
                        )
                        Spacer(
                            Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                    }
                }
                Column(modifier = Modifier
                    .constrainAs(description) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 25.dp)
                    ) {
                        Text(
                            stringResource(R.string.description_of_app),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(getString(R.string.changed_theme), changedTheme)
            apply()
        }
    }

}