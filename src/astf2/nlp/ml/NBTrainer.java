package astf2.nlp.ml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.jar.Pack200;

import static astf2.nlp.ml.NBClassifier.removePunctuation;

/**
 * Created by alistair on 12/12/2016.
 */
public class NBTrainer {

    //produce a Category instance for each category in the training data

    static File twentyNewsGroupsHome = new File("/Users/alistair/Documents/20_newsgroups/");

    private static WordCounter generalCount = new WordCounter();

    static int totalCount(String word) {
        return generalCount.getCount(word)+20; //20 accounts for smoothing
    }

    static int totalWordCount() {
        return generalCount.getTotalWordCount()+20*vocabSize;
    }

    static HashSet<String> vocabulary = new HashSet<>();

    static int vocabSize = vocabulary.size();

    static String[] categoryNames() {
        String[] result = new String[20];
        int i = 0;
        for (File f:twentyNewsGroupsHome.listFiles()) {
            if (f.isDirectory()) {
                result[i] = f.getName();
                i++;
            }
        }
        return result;
    }

    static ArrayList<Category> train(int examples) {
        ArrayList<Category> result = new ArrayList<>();
        for (File category:twentyNewsGroupsHome.listFiles()) { //TODO: add some error checking behavior for I/O here
            if (category.isDirectory()) {
                result.add(trainCategory(category, examples));
            }
        }
        return result;
    }

    static Category trainCategory(File category, int examples) {
        String categoryName = category.getName();
        WordCounter counter = new WordCounter();
        File[] files = category.listFiles();
        for (int i = 0;i<examples;i++) { //first iteration: read in every line, delimit only with spaces
            File file = files[i];
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String nopuncLine = removePunctuation(line);
                    String[] words = nopuncLine.split(" ");
                    for (String word:words) {
                        String normalizedWord = word.toLowerCase();
                        if (NBClassifier.stemming) {
                            normalizedWord = MorphaStemmer.stem(normalizedWord);
                        }
                        counter.addWord(normalizedWord);
                        generalCount.addWord(normalizedWord);
                        vocabulary.add(normalizedWord);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Category(categoryName,counter);
    }



}
