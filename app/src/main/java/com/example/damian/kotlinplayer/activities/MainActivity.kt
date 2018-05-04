package com.example.damian.kotlinplayer.activities

import com.example.damian.kotlinplayer.adapters.QueueAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.View
import com.example.damian.kotlinplayer.adapters.PagerAdapter
import com.example.damian.kotlinplayer.adapters.PlaylistAdapter
import android.support.design.widget.TabLayout
import android.widget.*
import com.example.damian.kotlinplayer.R
import com.example.damian.kotlinplayer.model.Playlist
import com.example.damian.kotlinplayer.model.Song

internal val SONGS = arrayListOf(
        Song("Title 1","Album 1"),
        Song("Title 2","Album 2"),
        Song("Title 3","Album 3"),
        Song("Title 4","Album 4"),
        Song("Title 5","Album 5"),
        Song("Title 6","Album 6"),
        Song("Title 7","Album 7"),
        Song("Title 8","Album 8"),
        Song("Title 9","Album 9"),
        Song("Title 10","Album 10")
)

internal val PLAYLISTS = arrayListOf(
        Playlist("Title 1", 1),
        Playlist("Title 2", 2),
        Playlist("Title 3", 3),
        Playlist("Title 4", 4),
        Playlist("Title 5", 5)

)

class MainActivity : AppCompatActivity() {

    var queueLoaded = false
    var playlistLoaded = false

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "Kotlin Player"

        println("Im in main activity")

        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<View>(R.id.tab_layout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Queue"))
        tabLayout.addTab(tabLayout.newTab().setText("Player"))
        tabLayout.addTab(tabLayout.newTab().setText("Playlist"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val viewPager = findViewById<View>(R.id.pager) as ViewPager
        val adapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.currentItem = 1   // Player tab is the main one
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position

                if(viewPager.currentItem == 0 ) {
                    loadQueue(SONGS)
                }

                if(viewPager.currentItem == 2 && !playlistLoaded) {
                    loadPlaylist(PLAYLISTS)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


    fun loadQueue(values: ArrayList<Song>) {

        var previousView: View? = null

        val listView: ListView = findViewById(R.id.listQueue)

        println("Im in list loader")


        val listAdapter = QueueAdapter(values, applicationContext)


        // Assign adapter to ListView
        listView.adapter = listAdapter

        // ListView Item Click Listener
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // ListView Clicked item index

            // ListView Clicked item value
            val itemValue = listView.getItemAtPosition(position) as String

            // Show Alert
            Toast.makeText(applicationContext,
                    "Position :$position  ListItem : $itemValue", Toast.LENGTH_LONG)
                    .show()

            if(previousView != null){
                val buttonPlay = previousView!!.findViewById(R.id.buttonPlay) as ImageButton
                buttonPlay.visibility = View.INVISIBLE
            }
            val buttonPlay = view!!.findViewById(R.id.buttonPlay) as ImageButton
            buttonPlay.visibility = View.VISIBLE
            previousView = view

            this.title = itemValue
        }

    }

    fun loadPlaylist(values: ArrayList<Playlist>) {

        val listView: ListView = findViewById(R.id.listPlaylist)

        val listAdapter = PlaylistAdapter(values,applicationContext)


        // Assign adapter to ListView
        listView.adapter = listAdapter

        // ListView Item Click Listener
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            startActivity(Intent(applicationContext,SelectedPlaylist::class.java))

        }
    }

}
