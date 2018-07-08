package jameskjiang.com.sleepreminders

import android.content.Context

//Used to store the set of phrases user has saved
class PhrasesSharedPreferences(context: Context) {

    val PREFERENCE_NAME = "Shared Preference Example"
    val PREFERENCE_PHRASE_SET = "PhraseSet"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE )

    fun getPhrasesSet(): MutableSet<String> {
        return preference.getStringSet(PREFERENCE_PHRASE_SET, mutableSetOf<String>())
    }

    fun setPhrasesSet(newPhraseSet:MutableSet<String>) {
        val editor = preference.edit()
        editor.putStringSet(PREFERENCE_PHRASE_SET, newPhraseSet)
        editor.apply()
    }


}