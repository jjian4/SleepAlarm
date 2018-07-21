package me.jamesjiang.sleepalarm

import android.content.Context

//Used to store the set of phrases user has saved
class PhrasesSharedPreferences(context: Context) {

    val PREFERENCE_NAME = "Phrases Shared Preference"
    val PREFERENCE_PHRASE_SET = "PhraseSet"

    val phrasesSharedRef = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE )

    fun getPhrasesSet(): MutableSet<String> {
        return phrasesSharedRef.getStringSet(PREFERENCE_PHRASE_SET, mutableSetOf<String>())
    }

    fun setPhrasesSet(newPhraseSet:MutableSet<String>) {
        val editor = phrasesSharedRef.edit()
        editor.putStringSet(PREFERENCE_PHRASE_SET, newPhraseSet)
        editor.apply()
    }


}