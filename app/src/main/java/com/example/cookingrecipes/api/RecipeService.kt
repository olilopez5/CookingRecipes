package com.example.cookingrecipes.api

import com.example.cookingrecipes.data.Recipe
import com.example.cookingrecipes.data.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes")
        suspend fun getAllRecipes(): RecipeResponse

        @GET("recipes/search")
        suspend fun searchRecipes(@Query("q") query: String): RecipeResponse




    @GET("search/{name}")
    suspend fun findRecipeByName(@Path("name") query: String): RecipeResponse

    @GET("{id}")
    suspend fun findRecipeById(@Path("id") id: String): Recipe
}
