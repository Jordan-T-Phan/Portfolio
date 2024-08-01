package com.example.ticketmaster30

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketmasterService {
    @GET("events.json")
    fun getEventInfo(
        @Query("apikey") apikey1: String,
        @Query("classificationName") classificationName1: String,
        @Query("city") city1: String,
        @Query("sort") sort1: String
    ): Call<TicketmasterData>

}