package com.example.sportik.presentation.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.compose.rememberAsyncImagePainter
import com.example.sportik.R
import com.example.sportik.domain.model.NewsWithContent
import com.example.sportik.presentation.themes.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {
    private var id: Int = 0
    private val viewModel: NewsDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        id = arguments?.getInt("newsId")!!
        viewModel.getNews(id)
        viewModel.onFavCheck(id)
        //viewModel.onFavCheck(id)
        return ComposeView(requireContext()).apply {
            setContent {
                ItemDetails()
            }
        }
    }

    private fun onFavouriteIconClick(id: Int) {
        viewModel.deleteNewsToFav(id)
        viewModel.likeIndicator.postValue(false)
    }

    private fun onUnFavouriteIconClick(news: NewsWithContent) {
        viewModel.addNewsToFav(news)
        viewModel.likeIndicator.postValue(true)
    }

    private fun onBackIconClick() {
        findNavController().navigateUp()
    }

    @Composable
    fun ItemDetails() {
        val value by viewModel.getStateLiveData().observeAsState()
        when (value) {
            is DetailsScreenState.SearchIsOk -> {
                SetData((value as DetailsScreenState.SearchIsOk).data)
            }

            DetailsScreenState.ConnectionError -> Unit
            DetailsScreenState.NothingFound -> Unit
            null -> Unit
        }
    }

    @Composable
    fun SetData(news: NewsWithContent) {
        ComposeTheme {
            Log.d("NewsDetailsFragment", "Data has been set")
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(16.dp, 25.dp, 16.dp, 16.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = { onBackIconClick() })
                            .padding(0.dp, 0.dp),
                        painter = painterResource(R.drawable.icon_back),
                        contentDescription = "BackIcon",
                        tint = Color.Unspecified
                    )
                    Text(
                        stringResource(R.string.news_details),
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(16.dp, 0.dp)
                    )
                    Spacer(
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                    val value by viewModel.getFavLiveData().observeAsState()
                    Log.d("DetailsFragment", "is fav = $value")
                    when (value) {
                        true -> {
                            IconButton(onClick = { onFavouriteIconClick(id) }) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_favs_active),
                                    contentDescription = "FavsIcon",
                                    tint = Color.Red
                                )
                            }
                        }

                        false -> {
                            IconButton(onClick = { onUnFavouriteIconClick(news) }) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_favs_inactive),
                                    contentDescription = "FavsIcon",
                                    tint = Color.Unspecified
                                )
                            }
                        }

                        null -> {
                            IconButton(onClick = { onUnFavouriteIconClick(news) }) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_favs_inactive),
                                    contentDescription = "FavsIcon",
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                }
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                ) {
                    ConstraintLayout {
                        val (image, title, content, data, icon, num) = createRefs()
                        Image(
                            modifier = Modifier
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                }
                                .fillMaxWidth()
                                .height(181.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            painter = rememberAsyncImagePainter(news.socialImage),
                            contentDescription = "img",
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = news.title,
                            modifier = Modifier
                                .padding(0.dp, 20.dp, 0.dp, 10.dp)
                                .constrainAs(title) {
                                    top.linkTo(image.bottom)
                                    start.linkTo(image.start)
                                    end.linkTo(image.end)
                                },
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            style = typography.bodyMedium,
                            textAlign = (TextAlign.Start)
                        )

                        Text(
                            text = news.content,
                            modifier = Modifier
                                .constrainAs(content) {
                                    top.linkTo(title.bottom)
                                    start.linkTo(title.start)
                                    end.linkTo(title.end)
                                },
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = typography.bodyMedium,
                            textAlign = (TextAlign.Start)
                        )

                        Icon(
                            modifier = Modifier
                                .constrainAs(icon) {
                                    top.linkTo(content.bottom)
                                    start.linkTo(content.start)
                                },
                            painter = painterResource(R.drawable.icon_comment),
                            contentDescription = "CommentIcon",
                            tint = Color.Unspecified
                        )

                        Text(
                            text = news.postedTime,
                            modifier = Modifier
                                .height(16.dp)
                                .constrainAs(data) {
                                    top.linkTo(icon.top)
                                    bottom.linkTo(icon.bottom)
                                    end.linkTo(parent.end)
                                },
                            color = Color.Gray,
                            style = typography.bodySmall,
                            maxLines = 1
                        )

                        Text(
                            text = news.commentCount,
                            modifier = Modifier
                                .padding(4.dp, 0.dp)
                                .constrainAs(num) {
                                    top.linkTo(icon.top)
                                    bottom.linkTo(icon.bottom)
                                    start.linkTo(icon.end)
                                },
                            color = Color.Gray,
                            style = typography.bodySmall,
                            maxLines = 1
                        )
                    }
                }

            }
        }
    }
}