package com.example.damian.kotlinplayer.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.model.Playlist
import com.github.zawadz88.materialpopupmenu.popupMenu
import java.util.stream.Collectors


class PlaylistAdapter(text: ArrayList<Playlist> , private val context: Context) : BaseAdapter(), ListAdapter {
    private var titleList = ArrayList<String>()
    private var countList = ArrayList<String>()


    init {
        val tmpTitleList = text.stream().map(Playlist::title).collect(Collectors.toList())
        this.titleList = ArrayList(tmpTitleList)

        val tmpCountList = text.stream().map(Playlist::count).collect(Collectors.toList())
        this.countList = ArrayList(tmpCountList)
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
            view = inflater.inflate(R.layout.playlist_list_item, null)
        }

        //Handle TextView and display string from your list
        val listTitleText = view!!.findViewById(R.id.playlist_title) as TextView
        listTitleText.text = titleList[position]

        val listCountText = view!!.findViewById(R.id.playlist_count) as TextView
        listCountText.text = countList[position]


        //Handle buttons and add onClickListeners
        val buttonPlaylistOptions = view!!.findViewById(R.id.buttonPlaylistOptions) as ImageButton

        buttonPlaylistOptions.setOnClickListener {
            val popupMenu = popupMenu {
                dropdownGravity = Gravity.END
                section {
                    item {
                        label = "Edit"
                        icon = android.R.drawable.ic_menu_edit
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