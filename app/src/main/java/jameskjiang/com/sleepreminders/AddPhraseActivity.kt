package jameskjiang.com.sleepreminders

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_phrase.*

//Activity for adding and editing phrases
class AddPhraseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_phrase)

        var phrases = intent.getSerializableExtra("phrases") as Phrases

        //Save new phrase by appending to phrases list and returning it back to PhrasesActivity
        button_save_phrase.setOnClickListener {
           phrases.phraseList.add(editText_new_phrase.text.toString())

            val returnIntent = Intent()
            returnIntent.putExtra("return_phrases", phrases)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
