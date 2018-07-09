package jameskjiang.com.sleepreminders

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_phrases.*

//List of all notification quotes the reminder app will use
class PhrasesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phrases)

        //Retrieve phrases from app memory
        val phrasesSharedPreferences = PhrasesSharedPreferences(this)
        var phrasesSet = phrasesSharedPreferences.getPhrasesSet()

        //Create layout
        recyclerView_phrases.layoutManager = LinearLayoutManager(this)
        recyclerView_phrases.adapter = PhrasesAdapter(phrasesSet, this)

        //Go to Schedule (Main activity)
        button_goto_main.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Go to Add phrase activity
        button_goto_add_phrase.setOnClickListener {
            val intent = Intent(this, AddPhraseActivity::class.java)
            startActivity(intent)
        }

    }

}
