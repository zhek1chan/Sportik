package com.example.sportik.presentation.ui.details

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sportik.R
import com.example.sportik.domain.model.NewsWithContent

@Composable
fun ItemDetails(news: NewsWithContent) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 25.dp, 16.dp, 16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .clickable(onClick = {NewsDetailsFragment().onBackIconClick()})
                    .padding(0.dp, 0.dp),
                painter = painterResource(R.drawable.icon_back),
                contentDescription = "BackIcon",
                tint = Color.Unspecified
            )
            Text(
                "Новость",
                fontSize = 22.sp,
                color = Color.Black,
                modifier = Modifier.padding(16.dp, 0.dp)
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Icon(
                modifier = Modifier.clickable(onClick = {/* ... */}),
                painter = painterResource(R.drawable.icon_favs),
                contentDescription = "FavsIcon",
                tint = Color.Unspecified
            )
        }
        HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            ConstraintLayout {
                val (image, title, content, data, icon, num) = createRefs()
                //GLIDE
                //val image: Painter = rememberAsyncImagePainter(news.socialImage)
                Image(
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                        }
                        .fillMaxWidth()
                        .height(181.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    //painter = image,
                    painter = painterResource(id = R.drawable.sports_logo),
                    contentDescription = "img",
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = news.title,
                    modifier = Modifier
                        .padding(0.dp, 20.dp, 0.dp, 0.dp)
                        .constrainAs(title) {
                            top.linkTo(image.bottom)
                            start.linkTo(image.start)
                            end.linkTo(image.end)
                        },
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = typography.bodyMedium,
                    textAlign = (TextAlign.Start)
                )

                Text(
                    text = news.content,
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 16.dp)
                        .constrainAs(content) {
                            top.linkTo(title.bottom)
                            start.linkTo(title.start)
                            end.linkTo(title.end)
                        },
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
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
                /*if (news.isFavourite) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FavouriteTag()
            }
        }*/
            }
        }
    }
}

@Preview
@Composable
fun NewsDetails() {
    LazyColumn {
        val list: List<NewsWithContent> = listOf(
            NewsWithContent(
                313131,
                "Test Title One Test Title One Test Title One Test Title OneTest Title OneTest Title OneTest Title One Test Title One Test Title One",
                "3",
                "lol",
                "31",
                " fdavbdanda doiaubdy agbdya gbdya bvdaysdb asdhjb asnd bsahbdasgv tusafvcafvasf vfaif va" +
                        "f anfui afui nauif bnaibf abf ab" +
                        "af abf ab fbaif baubifabuif abif baf bnafbs" +
                        "ab fibfiuabf ab nf a fan fnf lasnf nis nasf " +
                        "f nauf naioufn aiubf absf baxscvbndmsdafafu3weqfbnde fsdfqwe fqe8fy qsgfdsqbdqkn3reuqh gefsbadbajnd qw7gqshfdsabfna bfqy8w gfqfnasbfanbfwy8q"
            )
        )
        items(list) {
            ItemDetails(it)
        }
    }
}