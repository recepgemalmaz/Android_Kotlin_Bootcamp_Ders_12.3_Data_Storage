package com.recepgemalmaz.datastoragekullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.recepgemalmaz.datastoragekullanimi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appPref = AppPref(this)

        CoroutineScope(Dispatchers.Main).launch {
            //appPref.kayitAd("Recep Gemalmaz")
            appPref.silAd()
            val gelenAd = appPref.okuAd()
            Log.e("gelen Ad", gelenAd)

            val liste = HashSet<String>()
            liste.add("Ali")
            liste.add("Ece")
            appPref.kayitListe(liste)

            val gelenListe = appPref.okuListe()

            if (gelenListe != null) {
                for (item in gelenListe){
                    Log.e("gelen arkadas", item)
                }
            }

            var gelenSayac = appPref.okuSayac()
            appPref.kayitSayac(++gelenSayac)
            binding.textViewSayac.text = "Acilis Sayisi: $gelenSayac"

        }
    }
}