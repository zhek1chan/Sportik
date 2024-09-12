package com.example.sportik.data.converters

import android.text.Html
import com.example.sportik.data.dto.NewsDto
import com.example.sportik.data.dto.NewsWithContentDto
import com.example.sportik.data.entity.NewsEntity
import com.example.sportik.domain.model.News
import com.example.sportik.domain.model.NewsWithContent

class DtoMappers {
    fun newsDtoToNews(dto: List<NewsDto>): List<News> {
        return dto.map {
            val item = News(
                id = it.id,
                title = it.title,
                postedTime = convertData(it.posted_time),
                socialImage = it.social_image,
                commentCount = it.comment_count
            )
            item
        }
    }

    fun newsWithContentDtoToNewsWithContent(dto: NewsWithContentDto): NewsWithContent {
        return NewsWithContent(
            id = dto.id,
            title = dto.title,
            postedTime = convertData(dto.posted_time),
            socialImage = dto.social_image,
            commentCount = dto.comment_count,
            content = Html.fromHtml(dto.content).toString().re
            //без понятия как по другому убрать html функции из текста
        )
    }

    fun newsWithContentToNewsEntity(item: NewsWithContent): NewsEntity {
        return NewsEntity(
            id = item.id,
            title = item.title,
            postedTime = item.postedTime,
            socialImage = item.socialImage,
            commentCount = item.commentCount,
            content = item.content
        )
    }

    fun newsEntityToNewsWithContent(item: NewsEntity): NewsWithContent {
        return NewsWithContent(
            id = item.id,
            title = item.title,
            postedTime = item.postedTime,
            socialImage = item.socialImage,
            commentCount = item.commentCount,
            content = item.content
        )
    }

    private fun convertData(t: String): String {
        var formatted = java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(t.toLong()))
        formatted = formatted.replace('T', ' ')
        formatted = formatted.replace(":00Z", "")
        val splitted = formatted.split(" ").toMutableList()
        splitted[0] = splitted[0].replace('-', '.')
        formatted = splitted[1] + " " + splitted[0]
        return formatted
    }
}

