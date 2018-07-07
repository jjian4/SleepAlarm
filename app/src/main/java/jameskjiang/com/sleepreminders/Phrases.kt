package jameskjiang.com.sleepreminders

import java.io.Serializable

//Made a class for list of phrases to pass between different actvities
data class Phrases(
        val phraseList:MutableList<String>
): Serializable