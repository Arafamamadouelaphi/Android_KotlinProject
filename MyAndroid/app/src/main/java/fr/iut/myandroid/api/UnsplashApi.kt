package fr.iut.myandroid.api

import fr.iut.myandroid.BuildConfig
import fr.iut.myandroid.BuildConfig.APPLICATION_ID
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface UnsplashApi {

    companion object{
        const val CLIENT_ID ="DqTWq4IuTHgzVZB8T60q33hoJOOK9ssGEbUl6Eeu6p4"
        const val BASE_URL = "https://api.unsplash.com/"
    }
    @Headers("Accept-Version: v1",
        "Authorization:Client-ID $CLIENT_ID")
    @GET("search/photos")

    suspend fun searchphoto(
       @Query("query") query: String,
       @Query("page") page : Int,
       @Query("per_page") per_page : Int
    ):ResponseApi
}