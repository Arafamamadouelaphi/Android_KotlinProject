package fr.iut.myandroid.data

import androidx.paging.PagingSource
import fr.iut.myandroid.api.ResponseApi
import fr.iut.myandroid.api.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

private  const val UNSPLASH_START_PAGE_INDEX=1
class UnsplashPageSource (
    private  val  UnsplashApi:UnsplashApi,
    private val  query:String):PagingSource<Int,PhotoUnsplash>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoUnsplash>
    {
        val position = params.key ?: UNSPLASH_START_PAGE_INDEX

        return try
        {
            val response = UnsplashApi.searchphoto(query, position, params.loadSize)
            val photos =response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_START_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    }