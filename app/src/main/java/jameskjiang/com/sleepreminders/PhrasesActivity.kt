package jameskjiang.com.sleepreminders

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_phrases.*

//List of all notification quotes the reminder app will use
class PhrasesActivity : AppCompatActivity() {

    var phrases = Phrases(mutableListOf("Hello", "Bye"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phrases)

        recyclerView_phrases.layoutManager = LinearLayoutManager(this)
        recyclerView_phrases.adapter = PhrasesAdapter(phrases.phraseList)

        //Go to Add phrase activity
        button_goto_add_phrase.setOnClickListener {
            val intent = Intent(this, AddPhraseActivity::class.java)
            intent.putExtra("phrases", phrases)
            startActivityForResult(intent, 1)
        }
    }

    //Retrieve new phrase from AddPhraseActivity and reload the recyclerView
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            phrases = data.getSerializableExtra("return_phrases") as Phrases

            recyclerView_phrases.adapter = PhrasesAdapter(phrases.phraseList)
        }
    }
}
