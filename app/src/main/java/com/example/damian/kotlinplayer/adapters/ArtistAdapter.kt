package com.example.damian.kotlinplayer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.model.Artist
import java.util.stream.Collectors

class ArtistAdapter(text: ArrayList<Artist>, private val context: Context) : BaseAdapter(), ListAdapter {
    private var nameList = ArrayList<String>()
    private var albumsCountList = ArrayList<Int>()
    private var songCountList = ArrayList<Int>()


    init {
        val tmpTitleList = text.stream().map(Artist::name).collect(Collectors.toList())
        this.nameList = ArrayList(tmpTitleList)

        val tmpAlbumsCountList = text.stream().map(Artist::albumCount).collect(Collectors.toList())
        this.albumsCountList = ArrayList(tmpAlbumsCountList)

        val tmpSongsCountList = text.stream().map(Artist::songCount).collect(Collectors.toList())
        this.songCountList = ArrayList(tmpSongsCountList)
    }

    override fun getCount(): Int {
        return nameList.size
    }

    override fun getItem(pos: Int): Any {
        return nameList[pos]
    }

    override fun getItemId(pos: Int): Long {
        return 0
        //just return 0 if your list items do not have an Id variable.
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.artist_list_item, null)
        }

        //Handle TextView and display string from your list
        val albumTitle = view!!.findViewById(R.id.artist_name) as TextView
        albumTitle.text = nameList[position]

        val songCount = view!!.findViewById(R.id.songs_count) as TextView
        songCount.text = "Songs: ${songCountList[position]}"

        val albumCount = view!!.findViewById(R.id.albums_count) as TextView
        albumCount.text = "Albums: ${albumsCountList[position]}"


        return view
    }
}