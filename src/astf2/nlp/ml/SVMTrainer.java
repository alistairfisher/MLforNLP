package astf2.nlp.ml;

import java.io.File;

/**
 * Created by alistair on 21/12/2016.
 */
public class SVMTrainer extends TwentyNGInterface {

    protected void trainClass(File F) throws Exception { //use the results from a single class for SVM training
        File[] files = F.listFiles();
        for (int i=150;i<300;i++) {
            Double[] probVec = NBClassifier.makeProbVec(files[i]);
            for (String category:NBTrainer.categoryNames()) {
                File outputFile = new File(dataLocation+category);
                if (F.getName().equals(category)) {
                    DataFormatter.outputToFile(outputFile,true,probVec);
                }
                else DataFormatter.outputToFile(outputFile,false,probVec);
            }
        }
    }

}
