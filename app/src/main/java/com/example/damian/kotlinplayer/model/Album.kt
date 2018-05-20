package com.example.damian.kotlinplayer.model

import com.example.damian.kotlinplayer.R

public class Album(var title: String = "Unknown Album",
                   var year: String = "Unknown Year",
                   var artist: String = "Unknown Artist",
                   var cover: Int = R.drawable.default_cover,
                   var songList: MutableList<Song> = arrayListOf())