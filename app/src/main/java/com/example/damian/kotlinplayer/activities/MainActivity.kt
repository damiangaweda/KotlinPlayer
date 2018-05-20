package com.example.damian.kotlinplayer.activities

import com.example.damian.kotlinplayer.adapters.SongsAdapter
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
import com.example.damian.kotlinplayer.model.Album
import com.example.damian.kotlinplayer.model.Artist
import com.example.damian.kotlinplayer.model.Playlist
import com.example.damian.kotlinplayer.model.Song

internal val SONGS = arrayListOf(
        Song("Title 1",Album()),
        Song("Title 2",Album()),
        Song("Title 3",Album()),
        Song("Title 4",Album()),
        Song("Title 5",Album()),
        Song("Title 6",Album()),
        Song("Title 7",Album()),
        Song("Title 8",Album()),
        Song("Title 9",Album()),
        Song("Title 10",Album())
)

internal val PLAYLISTS = arrayListOf(
        Playlist("Playlist 1", 1),
        Playlist("Playlist 2", 2),
        Playlist("Playlist 3", 3),
        Playlist("Playlist 4", 4),
        Playlist("Playlist 5", 5)

)

internal val ALBUMS = arrayListOf(
        Album("Album 1","1999","Artist 1"),
        Album("Album 2","2006","Artist 1"),
        Album("Album 3","2001","Artist 2"),
        Album("Album 4","1987","Artist 1"),
        Album("Album 5","1999","Artist 3")
)

internal val ARTISTS = arrayListOf(
        Artist("Artist 1",10,2),
        Artist("Artist 2",42,1),
        Artist("Artist 3",10,3),
        Artist("Artist 4",5,2),
        Artist("Artist 5",13,0)
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
        tabLayout.addTab(tabLayout.newTab().setText("Library"))
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
                    val albumButton = findViewById<Button>(R.id.button_albums)
                    albumButton.setOnClickListener {
                        startActivity(Intent(applicationContext,AlbumsList::class.java))
                    }

                    val artistButton = findViewById<Button>(R.id.button_artists)
                    artistButton.setOnClickListener {
                        startActivity(Intent(applicationContext,ArtistList::class.java))
                    }

                    val genresButton = findViewById<Button>(R.id.button_genres)
                    genresButton.setOnClickListener {
                        startActivity(Intent(applicationContext,GenresList::class.java))
                    }

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


        val listAdapter = SongsAdapter(values, applicationContext)


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
