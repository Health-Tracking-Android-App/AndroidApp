package project.st991488104.krutik.fragments
//Krutik Parikh | 991488104

import android.content.Context

class PreferenceProvider(context: Context) {
    private val sharedPreferences= context.getSharedPreferences("myPreference",0)

    fun putString(key: String, value: String){
        sharedPreferences.edit().putString(key,value).apply()
    }

    fun getString(key: String, toString: String): String?{
        return sharedPreferences.getString(key,null)
    }
}