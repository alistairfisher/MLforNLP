package astf2.nlp.ml;

/**
 * Created by alistair on 12/12/2016.
 */
public class Category {

    double categoryPrior = 0.05;

    private WordCounter wordCounter;

    Category(String name, WordCounter wordCounter) {
        this.name = name;
        this.wordCounter = wordCounter;
    }


    final String name;

    double probability(String word) {
        double PwordGivenCat = ((double) (wordCounter.getCount(word)))/wordCounter.getTotalWordCount();
        double Pword = ( (double) NBTrainer.totalCount(word)) / NBTrainer.totalWordCount();
        return PwordGivenCat*categoryPrior/Pword;
        //return ((double) (wordCounter.getCount(word)))/wordCounter.getTotalWordCount();
    }

}
