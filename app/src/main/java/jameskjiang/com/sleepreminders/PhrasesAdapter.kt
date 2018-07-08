package jameskjiang.com.sleepreminders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.phrases_row.view.*

//Adapter for PhrasesActivity to make the list view
class PhrasesAdapter(val phrases:MutableSet<String>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return phrases.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.phrases_row, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, position: Int) {
        val phrasesList = phrases.toList()
        p0.view.textView_phrase?.text = phrasesList.get(position)
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
}