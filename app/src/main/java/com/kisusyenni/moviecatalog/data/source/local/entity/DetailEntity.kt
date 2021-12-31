package com.kisusyenni.moviecatalog.data.source.local.entity

data class DetailEntity(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val quote: String?,
    val image: String?,
    val production: String?,
    val rating: Float,
    val genres: String?,
    val releaseYear: String?,
    val durationEpisodes: String?
)
