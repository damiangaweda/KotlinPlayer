package com.example.damian.kotlinplayer.activities

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle

class SearchResultsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
    }

    protected override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
        }
    }
}
