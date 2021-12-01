package com.kisusyenni.moviecatalog.data.source.remote.response

data class MovieDetailResponse(
	val originalLanguage: String? = null,
	val imdbId: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val backdropPath: String? = null,
	val revenue: Int? = null,
	val genres: List<MovieGenresItem?>? = null,
	val popularity: Double? = null,
	val productionCountries: List<MovieProductionCountriesItem>? = null,
	val id: Int? = null,
	val voteCount: Int? = null,
	val budget: Int? = null,
	val overview: String? = null,
	val originalTitle: String? = null,
	val runtime: Int? = null,
	val posterPath: String? = null,
	val spokenLanguages: List<MovieSpokenLanguagesItem>? = null,
	val productionCompanies: List<MovieProductionCompaniesItem>? = null,
	val releaseDate: String? = null,
	val voteAverage: Double? = null,
	val belongsToCollection: Any? = null,
	val tagline: String? = null,
	val adult: Boolean? = null,
	val homepage: String? = null,
	val status: String? = null
)

data class MovieProductionCompaniesItem(
	val logoPath: Any? = null,
	val name: String? = null,
	val id: Int? = null,
	val originCountry: String? = null
)

data class MovieGenresItem(
	val name: String? = null,
	val id: Int? = null
)

data class MovieProductionCountriesItem(
	val iso31661: String? = null,
	val name: String? = null
)

data class MovieSpokenLanguagesItem(
	val name: String? = null,
	val iso6391: String? = null,
	val englishName: String? = null
)

