package com.kisusyenni.moviecatalog.utils

import com.kisusyenni.moviecatalog.data.source.local.entity.DetailEntity
import com.kisusyenni.moviecatalog.data.source.local.entity.ListEntity
import com.kisusyenni.moviecatalog.data.source.remote.response.*

object DataDummy {
    fun getMovies(): List<ListEntity> {
        return listOf(
            ListEntity(
                634649,
                "Spider-Man: No Way Home",
                "2021-12-15",
                4.2F,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
            ),
            ListEntity(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "2020",
                4.2F,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            ),
            ListEntity(
                1930,
                "The Amazing Spider-Man",
                "2012",
                3.3F,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/moNJa86FgDYy8SWeDym2gnsLLMa.jpg"
            )
        )
    }

    fun getDetailMovie(): DetailEntity {
        return DetailEntity(
            634649,
            "Spider-Man: No Way Home",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "The Multiverse unleashed.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "Marvel Studios, Pascal Pictures, Columbia Pictures",
            4.2F,
            "Action, Adventure, Science Fiction",
            "2021-12-15",
            "148"
        )
    }

    fun getTvShows(): List<ListEntity> {
        return listOf(
            ListEntity(
                84958,
                "Hawkeye",
                "2021-11-24",
                4.25F,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg"
            ),
            ListEntity(
                93405,
                "Squid Game",
                "2021",
                8.1F,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
            ),
            ListEntity(
                65650,
                "The Good Doctor",
                "2017",
                6.2F,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cXUqtadGsIcZDWUTrfnbDjAy8eN.jpg"
            )
        )
    }

    fun getDetailTvShow(): DetailEntity {
        return DetailEntity(
            84958,
            "Hawkeye",
            "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
            "This holiday season, the best gifts come with a bow.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
            "Disney+",
            4.25F,
            "Action & Adventure, Drama",
            "2021-11-24",
            "6 Episode",
        )
    }


    fun generateRemoteMovies(): List<MovieResultsItem> {
        return listOf(
            MovieResultsItem(
                adult = false,
                backdropPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
                genreIds =  listOf(28, 12, 878),
                id = 634649,
                originalLanguage = "en",
                originalTitle = "Spider-Man: No Way Home",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                popularity = 10507.821,
                posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                releaseDate = "2021-12-15",
                title = "Spider-Man: No Way Home",
                video = false,
                voteAverage = 8.4,
                voteCount = 2998
            ),
            MovieResultsItem(
                adult = false,
                backdropPath = "/hv7o3VgfsairBoQFAawgaQ4cR1m.jpg",
                genreIds =  listOf(28, 878),
                id = 624860,
                originalLanguage = "en",
                originalTitle = "The Matrix Resurrections",
                overview = "Plagued by strange memories, Neo's life takes an unexpected turn when he finds himself back inside the Matrix.",
                popularity = 9082.388,
                posterPath = "/8c4a8kE7PizaGQQnditMmI1xbRp.jpg",
                releaseDate = "2021-12-16",
                title = "The Matrix Resurrections",
                video = false,
                voteAverage = 7.2,
                voteCount = 1290
            ),
            MovieResultsItem(
                adult = false,
                backdropPath = "/o76ZDm8PS9791XiuieNB93UZcRV.jpg",
                genreIds =  listOf(27, 28, 878),
                id = 460458,
                originalLanguage = "en",
                originalTitle = "Resident Evil: Welcome to Raccoon City",
                overview = "Once the booming home of pharmaceutical giant Umbrella Corporation, Raccoon City is now a dying Midwestern town. The company’s exodus left the city a wasteland…with great evil brewing below the surface. When that evil is unleashed, the townspeople are forever…changed…and a small group of survivors must work together to uncover the truth behind Umbrella and make it through the night.",
                popularity = 8993.756,
                posterPath = "/sR3iV0Jt080jgvPBtJhs3Tta1y9.jpg",
                releaseDate = "2021-11-24",
                title = "Resident Evil: Welcome to Raccoon City",
                video = false,
                voteAverage = 6.1,
                voteCount = 544
            )
        )
    }

    fun generateRemoteDetailMovie(): MovieDetailResponse {
        return MovieDetailResponse(
            adult = false,
            backdropPath = "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
            belongsToCollection = BelongsToCollection(
                id = 531241,
                name = "Spider-Man (Avengers) Collection",
                posterPath = "/nogV4th2P5QWYvQIMiWHj4CFLU9.jpg",
                backdropPath = "/AvnqpRwlEaYNVL6wzC4RN94EdSd.jpg"
            ),
            budget = 200000000,
            genres = listOf(
                MovieGenresItem(
                    id = 28,
                    name = "Action"
                ),
                MovieGenresItem(
                    id = 12,
                    name = "Adventure"
                ),
                MovieGenresItem(
                    id = 878,
                    name = "Science Fiction"
                )
            ),
            homepage = "https://www.spidermannowayhome.movie",
            id = 634649,
            imdbId = "tt10872600",
            originalLanguage = "en",
            originalTitle = "Spider-Man: No Way Home",
            overview = "WPeter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            popularity = 10507.821,
            posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            productionCompanies = listOf(
                MovieProductionCompaniesItem(
                    id = 420,
                    logoPath = "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
                    name = "Marvel Studios",
                    originCountry = "US"
                ),
                MovieProductionCompaniesItem(
                    id = 84041,
                    logoPath = "/nw4kyc29QRpNtFbdsBHkRSFavvt.png",
                    name = "Pascal Pictures",
                    originCountry = "US"
                ),
                MovieProductionCompaniesItem(
                    id = 5,
                    logoPath = "/71BqEFAF4V3qjjMPCpLuyJFB9A.png",
                    name = "Columbia Pictures",
                    originCountry = "US"
                )
            ),
            productionCountries = listOf(
                MovieProductionCountriesItem(
                    iso31661 = "US",
                    name = "United States of America"
                )
            ),
            releaseDate = "2021-12-15",
            revenue = 1161281855,
            runtime = 148,
            spokenLanguages = listOf(
                MovieSpokenLanguagesItem(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                ),
                MovieSpokenLanguagesItem(
                    englishName = "Tagalog",
                    iso6391 = "tl",
                    name = ""
                )
            ),
            status = "Released",
            tagline = "The Multiverse unleashed.",
            title = "Spider-Man: No Way Home",
            video = false,
            voteAverage = 8.4,
            voteCount = 2998

        )
    }

    fun generateRemoteTvShows(): List<TvShowResultsItem> {
        return listOf(
            TvShowResultsItem(
                backdropPath = "/1R68vl3d5s86JsS2NPjl8UoMqIS.jpg",
                firstAirDate = "2021-11-24",
                genreIds = listOf(10759, 18),
                id = 88329,
                name = "Hawkeye",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Hawkeye",
                overview = "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
                popularity = 2203.365,
                posterPath = "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                voteAverage = 8.5,
                voteCount = 1247
            ),
            TvShowResultsItem(
                backdropPath = "/1P3QtW1IkivqDrKbbwuR0zCYIf8.jpg",
                firstAirDate = "2021-11-18",
                genreIds = listOf(10765, 18),
                id = 71914,
                name = "The Wheel of Time",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Wheel of Time",
                overview = "Follow Moiraine, a member of the shadowy and influential all-female organization called the “Aes Sedai” as she embarks on a dangerous, world-spanning journey with five young men and women. Moiraine believes one of them might be the reincarnation of an incredibly powerful individual, whom prophecies say will either save humanity or destroy it.",
                popularity = 1746.809,
                posterPath = "/mpgDeLhl8HbhI03XLB7iKO6M6JE.jpg",
                voteAverage = 8.0,
                voteCount = 801
            ),
            TvShowResultsItem(
                backdropPath = "/weqx7IQSQz9EzNBNk5u6IJZrM6f.jpg",
                firstAirDate = "2021-12-15",
                genreIds = listOf(18, 10765),
                id = 89614,
                name = "Sword Snow Stride",
                originCountry = listOf("CN"),
                originalLanguage = "zh",
                originalName = "雪中悍刀行",
                overview = "",
                popularity = 1798.682,
                posterPath = "/avUmZDbbCcvnIFw0yrTM3A4CLlW.jpg",
                voteAverage = 6.2,
                voteCount = 6
            )
        )
    }

    fun generateRemoteDetailTvShow(): TvShowDetailResponse {
        return TvShowDetailResponse(
            backdropPath = "/1R68vl3d5s86JsS2NPjl8UoMqIS.jpg",
            createdBy = listOf(
                CreatedByItem(
                    id = 1757552,
                    creditId = "602c5041cedac4003f4ff8ec",
                    name = "Jonathan Igla",
                    profilePath = "/qZEOruWxWZZMGXPnQcSDC38w1R2.jpg"
                )
            ),
            episodeRunTime = listOf(50),
            firstAirDate = "2021-11-24",
            genres = listOf(
                GenresItem(
                    id = 10759,
                    name = "Action & Adventure"
                ),
                GenresItem(
                    id = 18,
                    name = "Drama"
                )
            ),
            homepage = "https://www.disneyplus.com/series/hawkeye/11Zy8m9Dkj5l",
            id = 88329,
            inProduction = false,
            languages = listOf("en"),
            lastAirDate = "2021-12-22",
            lastEpisodeToAir = LastEpisodeToAir(
                airDate = "2021-12-22",
                episodeNumber = 6,
                id = 3301369,
                name = "So This Is Christmas?",
                overview = "Clint and Kate’s partnership is tested as they face the consequences of exposing the conspiracy.",
                productionCode = "",
                seasonNumber = 1,
                stillPath = "/nXuI42V8HgmyMlewU2tiOEEjKsC.jpg",
                voteAverage = 7.917,
                voteCount = 12
            ),
            name = "Hawkeye",
            nextEpisodeToAir = null,
            networks = listOf(
                NetworksItem(
                    name = "Disney+",
                    id = 2739,
                    logoPath = "/gJ8VX6JSu3ciXHuC2dDGAo2lvwM.png",
                    originCountry = "US"
                )
            ),
            numberOfEpisodes = 6,
            numberOfSeasons = 1,
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "Hawkeye",
            overview = "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
            popularity = 2203.365,
            posterPath = "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
            productionCompanies = listOf(
                TvShowProductionCompaniesItem(
                    id = 420,
                    logoPath = "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
                    name = "Marvel Studios",
                    originCountry = "US"
                )
            ),
            productionCountries = listOf(
                TvShowProductionCountriesItem(
                    iso31661 = "US",
                    name = "United States of America"
                )
            ),
            seasons = listOf(
                SeasonsItem(
                    airDate = "2021-11-24",
                    episodeCount = 6,
                    id = 122165,
                    name = "Season 1",
                    overview = "",
                    posterPath = "/nGcqeKieycPFvsxOeLJssCTt3mL.jpg",
                    seasonNumber = 1
                )
            ),
            spokenLanguages = listOf(
                TvShowSpokenLanguagesItem(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                )
            ),
            status = "Ended",
            tagline = "This holiday season, the best gifts come with a bow.",
            type = "Miniseries",
            voteAverage = 8.5,
            voteCount = 1247
        )
    }
}