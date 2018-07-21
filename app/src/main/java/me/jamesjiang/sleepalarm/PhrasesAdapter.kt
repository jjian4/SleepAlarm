package me.jamesjiang.sleepalarm

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.phrases_row.view.*

//Adapter for PhrasesActivity to make the list view
class PhrasesAdapter(val phrases:MutableSet<String>, val phrasesActivity: PhrasesActivity):
        RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return phrases.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.phrases_row, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val phrasesList = phrases.toMutableList()
        phrasesList.sort()
        holder.view.textView_phrase?.text = phrasesList.get(position)

        //Edit reminder
        holder.itemView.setOnClickListener {
            val intent = Intent(phrasesActivity, AddPhraseActivity::class.java)
            intent.putExtra("Edit", phrasesList.get(position))
            phrasesActivity.startActivity(intent)
        }
    }


}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
}