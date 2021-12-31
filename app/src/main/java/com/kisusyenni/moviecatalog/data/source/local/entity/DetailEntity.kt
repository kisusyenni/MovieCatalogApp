package com.kisusyenni.moviecatalog.data.source.local.entity

data class DetailEntity(
    var id: Int?,
    var title: String?,
    var overview: String?,
    var quote: String?,
    var image: String?,
    var production: String?,
    var rating: Float,
    var genres: String?,
    var releaseYear: String?,
    var durationSeasons: String?
)
