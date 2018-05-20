package com.example.damian.kotlinplayer.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.adapters.ArtistAdapter
import com.example.damian.kotlinplayer.model.Artist


public class ArtistList: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_artists)
        loadArtists(ARTISTS)

    }

    fun loadArtists(values: ArrayList<Artist>) {

        val listView: ListView = findViewById(R.id.artist_list)

        val listAdapter = ArtistAdapter(values, applicationContext)

        listView.adapter = listAdapter

    }


}