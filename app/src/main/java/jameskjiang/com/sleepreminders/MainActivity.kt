package jameskjiang.com.sleepreminders

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Go to list of phrases
        button_goto_phrases.setOnClickListener {
            val intent: Intent = Intent(this, PhrasesActivity::class.java)
            startActivity(intent)
        }
    }
}


/* PLAN:
Create Phrases class
Make list in PhrasesActivity
Allow user to add phrases as well as edit existing ones

Create Time class
Create AddTimeActivity
Allow user to add and edit times
Display list of times in ActivityMain

Set up notifications
 */