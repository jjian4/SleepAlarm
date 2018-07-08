package jameskjiang.com.sleepreminders

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_phrase.*

//Activity for adding and editing phrases
class AddPhraseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_phrase)

        val phrasesSharedPreferences = PhrasesSharedPreferences(this)
        var phrasesSet = phrasesSharedPreferences.getPhrasesSet()

        //Cancel adding new phrase
        button_cancel_add_phrase.setOnClickListener {
            val intent = Intent(this, PhrasesActivity::class.java)
            startActivity(intent)
        }

        //Save new phrase by appending to phrases list and returning it back to PhrasesActivity
        button_save_phrase.setOnClickListener {
            if(!editText_new_phrase.text.isNullOrEmpty()) {

                //Convert set to list to append, then convert back to set to send to shared preferences
                val newSet = phrasesSet.toMutableList()
                newSet.add(editText_new_phrase.text.toString())
                phrasesSharedPreferences.setPhrasesSet(newSet.toMutableSet())

                //Go back to PhrasesActivity
                val returnIntent = Intent(this, PhrasesActivity::class.java)
                startActivity(returnIntent)
            }
        }
    }
}
