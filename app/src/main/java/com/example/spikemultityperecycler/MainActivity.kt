package com.example.spikemultityperecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spikemultityperecycler.models.ScanShare
import com.example.spikemultityperecycler.models.ScanSummary
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listFeed = ArrayList<Any>()

        var scanSummary = ScanSummary()
        scanSummary.scannedCount = 234
        scanSummary.manualCount = 24

        var scanShare = ScanShare()
        scanShare.scanCount = 257

        listFeed.add(scanSummary)
        listFeed.add(scanShare)

        val timelineRecyclerViewAdapter = FeedRecyclerViewAdapter(this, listFeed)
        recyclerViewFeed.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewFeed.adapter = timelineRecyclerViewAdapter

    }
}
