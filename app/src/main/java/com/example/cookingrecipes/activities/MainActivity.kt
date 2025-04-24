package com.example.cookingrecipes.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cookingrecipes.adapters.RecipeAdapter
import com.example.cookingrecipes.data.Recipe
import com.example.cookingrecipes.databinding.ActivityMainBinding
import com.example.cookingrecipes.api.RetrofitInstance.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecipeAdapter
    private var recipeList: List<Recipe> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("RECETAS", "ONCREATE")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecipeAdapter(recipeList) { position ->
            val recipe = recipeList[position]
            // Aquí puedes abrir una nueva Activity con detalles si quieres
        }

        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
        binding.recyclerView.adapter = adapter

        supportActionBar?.title = "Cooking Recipes"
        supportActionBar?.setHomeButtonEnabled(true)

        //findRecipeByName("salad")
        getAllRecipes("salad")
    }

    private fun getAllRecipes(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.searchRecipes(query)

                recipeList = result.recipes

                CoroutineScope(Dispatchers.Main).launch {
                    Log.d("RECETAS", "Recetas obtenidas: ${recipeList.size}")
                    adapter.updateItems(recipeList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    private fun findRecipeByName(query: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val service = getRetrofit()
//                val result = service.findRecipeByName(query)
//
//                recipeList = result.results
//
//                CoroutineScope(Dispatchers.Main).launch {
//                    Log.d("RECETAS", "Recetas obtenidas: ${recipeList.size}")
//
//                    adapter.items = recipeList
//                    adapter.notifyDataSetChanged()
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}
