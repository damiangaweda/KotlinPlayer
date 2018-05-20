package com.example.damian.kotlinplayer.model

import com.example.damian.kotlinplayer.R
import kotlinx.android.synthetic.*

public class Song(var title: String = "Unknown Title",
                  var album: Album = Album(),
                  var artist: String = "Unknown Artist",
                  var cover: Int = R.drawable.default_cover)