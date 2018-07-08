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
            val intent = Intent(this, PhrasesActivity::class.java)
            startActivity(intent)
        }
    }

}


/* PLAN:
Create Phrases class DONE / UNDONE(Switched to SharedPreferences method to store internal memory)
Make list in PhrasesActivity DONE
Allow user to add phrases as well as edit existing ones NEED TO IMPLEMENT EDITING

Create Time class
Create AddTimeActivity
Allow user to add and edit times
Display list of times in ActivityMain

Set up notifications
SET UP MEMORY!!
 */