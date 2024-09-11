package com.example.sportik.data.converters

import com.example.sportik.data.dto.NewsDto
import com.example.sportik.data.dto.NewsWithContentDto
import com.example.sportik.domain.model.News
import com.example.sportik.domain.model.NewsWithContent

class DtoMappers {
    fun newsDtoToNews(dto: List<NewsDto>): List<News> {
        return dto.map {
            val item = News(
                newsId = it.newsId,
                title = it.title,
                postedTime = it.posted_time,
                socialImage = it.social_image,
                commentCount = it.comment_count
            )
            item
        }
    }

    fun newsWithContentDtoToNewsWithContent(dto: NewsWithContentDto): NewsWithContent {
        return NewsWithContent(
            newsId = dto.newsId,
            title = dto.title,
            postedTime = dto.postedTime,
            socialImage = dto.socialImage,
            commentCount = dto.commentCount,
            content = dto.content
        )
    }
}

/*
fun newsToNewsDto(dto: List<News>): List<News> {
    return dto.map {
        val item = News(
            newsId = it.newsId,
            title = it.title,
            postedTime = it.postedTime,
            socialImage = it.socialImage,
            commentCount = it.commentCount
        )
        item
    }
}

fun ticketRecDTOToTicketsRec(dto: List<RecTicketDto>): List<TicketsRec> {
    return dto.map {
        val newTicketRecs = TicketsRec(
            id = it.id,
            title = it.title,
            price = getPriceString(it.price.value.toInt()),
            timeRange = getTimeRangeString(it.timeRange)
        )
        newTicketRecs
    }
}
private fun getOptions(transfer: Boolean, duration: String): String {
    val options = StringBuilder()
    options.append("${duration}ч в пути")
    if (!transfer) options.append(" / Без пересадок")
    return options.toString()
}

private fun getDuration(departure: String, arrival: String): String {
    val diff = convertToDate(arrival).time - convertToDate(departure).time
    val hours = diff.milliseconds.toDouble(DurationUnit.HOURS)
    return String.format(Locale.US, "%.1f", hours)
}

private fun getDate(date: String): String {
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(convertToDate(date))
}

private fun convertToDate(date: String): Date {
    val dateFormat = try {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return if (dateFormat is Date) dateFormat
    else Calendar.getInstance().time
}

private fun getTimeRangeString(array: ArrayList<String>): String {
    val timeRange = StringBuilder()
    array.forEachIndexed { index, time ->
        timeRange.append(time)
        if (index != array.size - 1) timeRange.append("  ")
    }
    return timeRange.toString()
}

private fun getPriceString(price: Int): String {
    val result = StringBuilder()
    with(result) {
        append(NumberFormat.getInstance().format(price))
        append(" ₽")
    }
    return result.toString().replace(',', ' ')
}*/