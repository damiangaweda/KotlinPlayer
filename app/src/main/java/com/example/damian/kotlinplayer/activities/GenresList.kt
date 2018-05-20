package com.example.damian.kotlinplayer.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.adapters.AlbumAdapter
import com.example.damian.kotlinplayer.model.Album

public class GenresList: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_genres)
    }
}