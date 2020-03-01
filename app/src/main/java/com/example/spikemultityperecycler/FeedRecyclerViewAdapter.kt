package com.example.spikemultityperecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.spikemultityperecycler.models.ScanShare
import com.example.spikemultityperecycler.models.ScanSummary

class FeedRecyclerViewAdapter (private val context: Context, private val dataList : List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        private const val TYPE_SUMMARY = 0
        private const val TYPE_SHARE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_SUMMARY -> SummaryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_scan_summary, parent, false))
        TYPE_SHARE -> ShareViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_scan_share, parent, false))
        else -> throw IllegalArgumentException()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_SUMMARY -> onBindSummary(holder, dataList[position] as ScanSummary)
            TYPE_SHARE -> onBindShare(holder, dataList[position] as ScanShare)
            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int): Int =
        when (dataList[position]) {
            is ScanSummary -> TYPE_SUMMARY
            is ScanShare -> TYPE_SHARE
            else -> throw IllegalArgumentException()
        }

    private fun onBindSummary(holder: RecyclerView.ViewHolder, row: ScanSummary) {
        (holder as SummaryViewHolder).textSummary.text = "SUMMARY\nScanned: ${row.scannedCount} \nManual: ${row.manualCount}"
    }

    private fun onBindShare(holder: RecyclerView.ViewHolder, row: ScanShare) {
        val h = (holder as ShareViewHolder)

        h.textShare.text = "Hey Kelley, looks like you started a physical inventory. Would you like to share ${row.scanCount} vehicles with accounting?"
        h.buttonShare.setOnClickListener { Toast.makeText(context, "Data has been shared", Toast.LENGTH_LONG) }
    }

    class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textSummary: TextView = itemView.findViewById(R.id.textViewSummary)
//        val titleView: TextView = itemView.findViewById(R.id.title)
    }

    class ShareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textShare: TextView = itemView.findViewById(R.id.textViewShare)
        val buttonShare: Button = itemView.findViewById(R.id.buttonShare)
//        val titleView: TextView = itemView.findViewById(R.id.title)
    }
}