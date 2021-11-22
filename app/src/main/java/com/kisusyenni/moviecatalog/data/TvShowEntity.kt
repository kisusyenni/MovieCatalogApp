package com.kisusyenni.moviecatalog.data

data class TvShowEntity (
    var tvShowId: String,
    var title: String,
    var overview: String,
    var quote: String,
    var image: String,
    var director: String,
    var rating: Int,
    var genres: String,
    var releaseYear: String,
    var season: Int,
    var episodes: Int
)