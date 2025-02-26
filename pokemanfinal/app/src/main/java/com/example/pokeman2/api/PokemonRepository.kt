package com.example.pokeman2.api

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object PokemonRepository {

    fun fetchPokemonNames(): List<String> {
        val apiUrl = "https://pokeapi.co/api/v2/pokemon?limit=10"
        val pokemonList = mutableListOf<String>()

        try {
            val url = URL("https://pokeapi.co/api/v2/pokemon?limit=10")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val response = connection.inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(response)
            val results = jsonObject.getJSONArray("results")

            for (i in 0 until results.length()) {
                val pokemon = results.getJSONObject(i)
                pokemonList.add(pokemon.getString("name"))
            }

            connection.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return pokemonList
    }
}
