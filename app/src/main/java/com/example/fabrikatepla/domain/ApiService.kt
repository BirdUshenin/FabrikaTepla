package com.example.fabrikatepla.domain

import com.example.fabrikatepla.data.CategoryItem
import com.example.fabrikatepla.data.Item
import com.example.fabrikatepla.data.Profile
import retrofit2.http.GET

interface ApiService {

    @GET("/HeatFactoryAPI/products.json")
    suspend fun getItems(): List<Item>

    @GET("/HeatFactoryAPI/boilers.json")
    suspend fun getBoilers(): List<CategoryItem>

    @GET("/HeatFactoryAPI/radiators.json")
    suspend fun getRadiators(): List<CategoryItem>


    @GET("/HeatFactoryAPI/Waterheaters.json")
    suspend fun getWaterHeaters(): List<CategoryItem>

    @GET("/HeatFactoryAPI/Pumps.json")
    suspend fun getPumps(): List<CategoryItem>

    @GET("/HeatFactoryAPI/Pipes.json")
    suspend fun getPipes(): List<CategoryItem>

    @GET("/HeatFactoryAPI/Floors.json")
    suspend fun getFloors(): List<CategoryItem>

    @GET("/HeatFactoryAPI/profile.json")
    suspend fun getProfiles(): Profile

}
