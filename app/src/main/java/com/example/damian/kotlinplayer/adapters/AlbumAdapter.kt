package com.example.damian.kotlinplayer.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.model.Album
import com.github.zawadz88.materialpopupmenu.popupMenu
import java.util.stream.Collectors

class AlbumAdapter(text: ArrayList<Album>, private val context: Context) : BaseAdapter(), ListAdapter {
    private var titleList = ArrayList<String>()
    private var artistList = ArrayList<String>()
    private var yearList = ArrayList<String>()
    private var imageList = ArrayList<Int>()


    init {
        val tmpTitleList = text.stream().map(Album::title).collect(Collectors.toList())
        this.titleList = ArrayList(tmpTitleList)

        val tmpArtistList = text.stream().map(Album::artist).collect(Collectors.toList())
        this.artistList = ArrayList(tmpArtistList)

        val tmpYearList = text.stream().map(Album::year).collect(Collectors.toList())
        this.yearList = ArrayList(tmpYearList)

        val tmpCoverList = text.stream().map(Album::cover).collect(Collectors.toList())
        this.imageList = ArrayList(tmpCoverList)
    }

    override fun getCount(): Int {
        return titleList.size
    }

    override fun getItem(pos: Int): Any {
        return titleList[pos]
    }

    override fun getItemId(pos: Int): Long {
        return 0
        //just return 0 if your list items do not have an Id variable.
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.album_list_item, null)
        }

        //Handle TextView and display string from your list
        val albumTitle = view!!.findViewById(R.id.album_title) as TextView
        albumTitle.text = titleList[position]

        val artistName = view!!.findViewById(R.id.artist_title) as TextView
        artistName.text = artistList[position]

        val year = view!!.findViewById(R.id.album_year) as TextView
        year.text = "Year: ${yearList[position]}"

        val cover = view!!.findViewById(R.id.album_image) as ImageView
        cover.setImageResource(imageList[position])

        return view
    }
}