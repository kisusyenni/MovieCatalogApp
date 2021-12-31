package com.kisusyenni.moviecatalog.data.source.local.entity

data class MovieEntity(
    var movieId: Int?,
    var title: String?,
    var overview: String?,
    var quote: String?,
    var image: String?,
    var production: String?,
    var rating: Float,
    var genres: String?,
    var releaseYear: String?,
    var duration: String?
)