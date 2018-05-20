package com.example.damian.kotlinplayer.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.adapters.SongsAdapter
import com.example.damian.kotlinplayer.model.Song

public class SelectedPlaylist: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_selected_playlist)
        loadPlaylistContents(SONGS)

    }

    fun loadPlaylistContents(values: ArrayList<Song>) {

        val listView: ListView = findViewById(R.id.playlist_content_list)

        val listAdapter = SongsAdapter(values, applicationContext)

        listView.adapter = listAdapter

    }


}