package astf2.nlp.ml;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by alistair on 07/12/2016.
 */
public class NBClassifier {

    public static void main(String[] args) throws Exception {
        File testInput = new File("/Users/alistair/Documents/20_newsgroups/alt.atheism/54564");
        Double[] probVec = makeProbVec(testInput);
        double accum = 0.0;
        File outputFile = new File("/Users/alistair/Documents/svm_light_osx.8.4_i7/nbvecs/output");
        for (int i=0;i<categories.size();i++) {
            System.out.printf("%s: %f\n",categories.get(i).name,probVec[i]);
            accum += probVec[i];
        }
        DataFormatter.outputToFile(outputFile,true,probVec);
        System.out.println(accum);
        //NBTester nbtest = new NBTester();
        //nbtest.run(false);
        //System.out.println(nbtest.accuracy());
        /*SVMTrainer svmtrain = new SVMTrainer();
        svmtrain.run(true);
        SVMTester svmtest = new SVMTester();
        svmtest.run(false);*/
    }

    static boolean stemming = false;

    static ArrayList<Category> categories = NBTrainer.train(150);

    //the classifier produces a vector of probabilites, with one for each category

    static Double[] makeProbVec(File input) {
        Double[] result = new Double[categories.size()];
        for (int i = 0;i<result.length;i++) {
            result[i] = getProbability(input,categories.get(i));
            //System.out.printf("%s: %f", categories.get(i).name,result[i]);
        }
        //System.out.printf("\n");
        return result;
    }

    String[] testsubset = {"alt.atheism","comp.windows.x","rec.autos","rec.sport.hockey","sci.electronics","talk.politics"};

    static Double getProbability(File input, Category c) {
        int docLength = 0;
        double cumuProb = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                    String nopuncLine = removePunctuation(line);
                    String[] words = nopuncLine.split(" ");
                    for (String word : words) {
                        String normalizedWord = word.toLowerCase();
                        if (stemming) {
                            normalizedWord = MorphaStemmer.stem(normalizedWord);
                        }
                        if (NBTrainer.totalCount(normalizedWord)>0) {
                            cumuProb += c.probability(normalizedWord);
                            docLength++;
                        }
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cumuProb/docLength;
    }

    static String removePunctuation(String string) {
        String result = string.replaceAll("[^a-zA-Z\\s]", "");
        return result;
    }

}
