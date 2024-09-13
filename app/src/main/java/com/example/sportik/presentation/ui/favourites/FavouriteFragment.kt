package com.example.sportik.presentation.ui.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.compose.rememberAsyncImagePainter
import com.example.sportik.R
import com.example.sportik.domain.model.NewsWithContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private var newsList: MutableList<NewsWithContent> = ArrayList()
    private val viewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getNews()
        return ComposeView(requireContext()).apply {
            setContent {
                //запрос к вьюмодели на список избранного
                NewsList()
            }
        }
    }

    private fun onItemClicked(news: NewsWithContent) {
        val bundle = Bundle()
        bundle.putInt("newsId", news.id)
        findNavController().navigate(R.id.navigation_details, bundle)
    }

    @Composable
    fun ItemNewsCard(news: NewsWithContent) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(136.5.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = { onItemClicked(news) }),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Box(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .padding(16.dp)
            ) {
                ConstraintLayout {
                    val (image, title, data, icon, num) = createRefs()
                    Image(
                        modifier = Modifier
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                            }
                            .size(80.dp, 80.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        painter = rememberAsyncImagePainter(news.socialImage),
                        contentDescription = "img",
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = news.title,
                        modifier = Modifier
                            .padding(12.dp, 0.dp, 0.dp, 0.dp)
                            .height(80.dp)
                            .width(260.dp)
                            .constrainAs(title) {
                                top.linkTo(parent.top)
                                start.linkTo(image.end)
                            },
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = typography.bodyMedium,
                        textAlign = (TextAlign.Start)
                    )


                    Icon(
                        modifier = Modifier
                            .constrainAs(icon) {
                                top.linkTo(image.bottom)
                                start.linkTo(image.start)
                            }
                            .padding(0.dp, 7.dp),
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
                            .padding(6.dp, 0.dp)
                            .constrainAs(num) {
                                top.linkTo(icon.top)
                                bottom.linkTo(icon.bottom)
                                start.linkTo(icon.end)
                            },
                        color = Color.Gray,
                        style = typography.titleSmall,
                        maxLines = 1
                    )
                }
            }
        }
    }

    @Composable
    fun NewsList() {
        Log.d("NewsFragment", "$newsList")
        val value by viewModel.getStateLiveData().observeAsState()
        when (value) {
            is FavouriteScreenState.Data -> {
                newsList = (value as FavouriteScreenState.Data).data
                LazyColumn {
                    items(newsList) {
                        ItemNewsCard(it)
                    }
                }
            }

            FavouriteScreenState.NothingFound -> Unit
            null -> Unit
        }
    }
}