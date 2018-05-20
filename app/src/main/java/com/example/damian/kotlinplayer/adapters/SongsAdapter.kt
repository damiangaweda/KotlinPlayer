package com.example.damian.kotlinplayer.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.model.Album
import com.example.damian.kotlinplayer.model.Song
import com.github.zawadz88.materialpopupmenu.popupMenu
import java.util.stream.Collectors


class SongsAdapter(text: ArrayList<Song>, private val context: Context) : BaseAdapter(), ListAdapter {
    private var titleList = ArrayList<String>()
    private var artistList = ArrayList<String>()
    private var albumList = ArrayList<String>()
    private var imageList = ArrayList<Int>()

    init {
        val tmpTitleList = text.stream().map(Song::title).collect(Collectors.toList())
        this.titleList = ArrayList(tmpTitleList)

        val tmpAlbumList = text.stream().map(Song::album).map(Album::title).collect(Collectors.toList())
        this.albumList = ArrayList(tmpAlbumList)

        val tmpArtistList = text.stream().map(Song::artist).collect(Collectors.toList())
        artistList = ArrayList(tmpArtistList)

        val tmpImageList = text.stream().map(Song::cover).collect(Collectors.toList())
        this.imageList = ArrayList(tmpImageList)
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
            view = inflater.inflate(R.layout.queue_list_item, null)
        }

        //Handle TextView and display title string from your list
        val listItemText = view!!.findViewById(R.id.song_title) as TextView
        listItemText.text = titleList[position]

        //Handle TextView and display album text from your list
        val listItemAlbum = view!!.findViewById(R.id.album_title) as TextView
        listItemAlbum.text = albumList[position]

        //Handle TextView and display album text from your list
        val listItemArtist = view!!.findViewById(R.id.artist_title) as TextView
        listItemArtist.text = artistList[position]

        //Handle TextView and display images from your list
        val listItemImage = view!!.findViewById(R.id.list_item_image) as ImageView
        listItemImage.setImageResource(imageList[position])

        //Handle buttons and add onClickListeners
        val optionsButton = view!!.findViewById(R.id.buttonOptionQueue) as ImageButton
        val playButton: ImageButton = view!!.findViewById(R.id.buttonPlay) as ImageButton

        optionsButton.setOnClickListener {
            val popupMenu = popupMenu {
                dropdownGravity = Gravity.END
                section {
                    item {
                        label = "Add to playlist"
                        icon = android.R.drawable.ic_input_add
                        callback = {
                            Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    item {
                        label = "Delete"
                        icon = android.R.drawable.ic_delete
                        callback = {
                            Toast.makeText(context, "Text pasted!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            popupMenu.show(context, view)
        }

        return view
    }
}