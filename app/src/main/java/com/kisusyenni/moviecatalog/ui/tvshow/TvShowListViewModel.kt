package com.kisusyenni.moviecatalog.ui.tvshow

import androidx.lifecycle.ViewModel
import com.kisusyenni.moviecatalog.data.TvShowEntity
import com.kisusyenni.moviecatalog.utils.TvShowDummyData

class TvShowListViewModel: ViewModel() {
    fun getTvShows(): List<TvShowEntity> = TvShowDummyData.generateTvShows()
}