package com.example.weatherapplication.modules.search.utils

import android.content.Context
import com.example.weatherapplication.modules.search.SearchFragment
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object JSONUtils {
    private fun loadJSONFromAsset(context: Context): String {
        val json: String
        try {
            val inputStream: InputStream = context.assets.open("towns-russia.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    fun loadCities(context: Context): ArrayList<String> {
        val cities = arrayListOf<String>()
        try {
            val jsObject = JSONObject(loadJSONFromAsset(context))
            val array: JSONArray = jsObject.getJSONArray("data")
            for (i in SearchFragment.ZERO until array.length()) {
                val jsonObject = array.getJSONObject(i)
                val type = jsonObject.getString("type")
                val cityName = jsonObject.getString("slug")
                val localities = jsonObject.getJSONArray("localities")
                if (type == "city") {
                    cities.add(cityName)
                } else {
                    for (v in SearchFragment.ZERO until localities.length()) {
                        val jsCityName = localities.getJSONObject(v).getString("slug")
                        cities.add(jsCityName)
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return cities
    }
}