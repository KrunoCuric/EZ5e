package edu.rit.kc2736.simple5e.api

import edu.rit.kc2736.simple5e.databse.SpellDetail
import edu.rit.kc2736.simple5e.databse.Spells
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://www.dnd5eapi.co/"

interface APIService {
    @GET("api/spells")
    suspend fun getSpells(): Spells

    @GET("/spells/{index}")
    suspend fun getSpell(@Path("index") index: String): SpellDetail

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if(apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}