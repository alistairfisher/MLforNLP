package astf2.nlp.ml;

import java.util.HashMap;

/**
 * Created by alistair on 12/12/2016.
 */
public class WordCounter {

    //A hashmap for word counts in each category, with added functionality

    private HashMap <String,Integer> map = new HashMap<>();
    private int totalWordCount = 0;

    Integer getTotalWordCount() {
        return totalWordCount;
    }

    int getCount(String word) {
        if (map.containsKey(word)) {
            return map.get(word);
        }
        else return 0;
    }

    void addWord(String word) {
        if (map.containsKey(word)) {
            map.put(word,getCount(word)+1);
            totalWordCount++;
        }
        else map.put(word,1);
        totalWordCount++;
    }

}
