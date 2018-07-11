package jameskjiang.com.sleepreminders

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_add_phrase.*

//Activity for adding and editing phrases
class AddPhraseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_phrase)

        val phrasesSharedPreferences = PhrasesSharedPreferences(this)
        var phrasesSet = phrasesSharedPreferences.getPhrasesSet()

        Log.d("James", phrasesSet.toString())

        //If editing, get the original string
        var phrase = ""
        if (intent.hasExtra("Edit")) {
            phrase = intent.getStringExtra("Edit")

            //Replace editText hint with phrase and set cursor
            editText_new_phrase.setText(phrase)
            editText_new_phrase.setSelection(editText_new_phrase.text.length)

            //Show delete button
            button_delete_phrase.visibility = View.VISIBLE

            //Change activity title
            textView_add_phrases_activity.text = "Edit Quote"
        }
        else {
            textView_add_phrases_activity.text = "Add Quote"
        }

        //Cancel adding new phrase
        button_cancel_add_phrase.setOnClickListener {
            val intent = Intent(this, PhrasesActivity::class.java)
            startActivity(intent)
        }


        //Delete this phrase, only shows when editing
        button_delete_phrase.setOnClickListener {
            phrasesSet.remove(phrase)
            phrasesSharedPreferences.setPhrasesSet((phrasesSet))

            //Go back to PhrasesActivity
            val returnIntent = Intent(this, PhrasesActivity::class.java)
            startActivity(returnIntent)
        }

        //Save new phrase
        button_save_phrase.setOnClickListener {
            if(!editText_new_phrase.text.isNullOrEmpty()) {

                //Add new text to set
                phrasesSet.add(editText_new_phrase.text.toString().capitalize())

                Log.d("James", phrasesSet.toString())

                //Edited phrase replaces original
                if(intent.hasExtra("Edit")) {
                    phrasesSet.remove(phrase)
                }

                //Save to shared preferences
                phrasesSharedPreferences.setPhrasesSet(phrasesSet)

                //Go back to PhrasesActivity
                val returnIntent = Intent(this, PhrasesActivity::class.java)
                startActivity(returnIntent)

            }
        }
    }
}
