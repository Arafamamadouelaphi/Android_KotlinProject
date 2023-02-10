package fr.iut.myandroid.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import fr.iut.myandroid.api.UnsplashApi
import fr.iut.myandroid.data.UnsplashPageSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor  (private val unsplashApi: UnsplashApi ) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {UnsplashPageSource(unsplashApi, query) }
        ).liveData

}