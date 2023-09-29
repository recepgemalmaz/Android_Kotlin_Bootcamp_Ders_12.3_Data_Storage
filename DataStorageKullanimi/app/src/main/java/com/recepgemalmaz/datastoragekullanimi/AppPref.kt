package com.recepgemalmaz.datastoragekullanimi

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref(var contex: Context) {
    val Context.ds : DataStore<Preferences> by preferencesDataStore("bilgiler")
    companion object{
        val AD_KEY = stringPreferencesKey("AD")
        //liste için
        val LISTE_KEY = stringSetPreferencesKey("LISTE")
        val SAYAC_KEY = intPreferencesKey("SAYAC")
    }

    suspend fun kayitSayac(sayac : Int){
        contex.ds.edit { preferences ->
            preferences[SAYAC_KEY] = sayac
        }
    }

    suspend fun okuSayac() : Int{
        val preferences = contex.ds.data.first()
        return preferences[SAYAC_KEY] ?: 0
    }

    suspend fun kayitAd(ad : String){
        contex.ds.edit { preferences ->
            preferences[AD_KEY] = ad
        }
    }
    suspend fun okuAd() : String{
        val preferences = contex.ds.data.first()
        return preferences[AD_KEY] ?: "isim yok"
    }
    suspend fun silAd(){
        contex.ds.edit { preferences ->
            preferences.remove(AD_KEY)
        }
    }

    suspend fun kayitListe(liste : Set<String>){
        contex.ds.edit { it
            it[LISTE_KEY] = liste
        }
    }
    suspend fun okuListe() : Set<String>?{
        val preferences = contex.ds.data.first()
        return preferences[LISTE_KEY]
    }

}
