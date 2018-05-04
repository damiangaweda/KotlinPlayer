package com.example.damian.kotlinplayer.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.adapters.QueueAdapter
import com.example.damian.kotlinplayer.model.Song


public class SelectedPlaylist: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_selected_playlist)
        loadPlaylistContents(SONGS)

    }

    fun loadPlaylistContents(values: ArrayList<Song>) {

        val listView: ListView = findViewById(R.id.playlist_content_list)

        val listAdapter = QueueAdapter(values, applicationContext)

        listView.adapter = listAdapter

    }


}