package com.kisusyenni.moviecatalog.data

data class MovieEntity (
    var movieId: String,
    var title: String,
    var overview: String,
    var quote: String,
    var image: String,
    var director: String,
    var rating: Int,
    var genres: String,
    var releaseYear: String,
    var duration: String
)