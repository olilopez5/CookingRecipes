package com.example.cookingrecipes.data


class RecipeResponse (
   val recipes: List<Recipe>,
   val total: Int,
   val skip: Int,
   val limit: Int
)


data class Recipe(
   var id:Int,
   var name:String,
   var image:String
)
