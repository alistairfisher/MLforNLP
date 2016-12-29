package astf2.nlp.ml;

import java.io.File;

/**
 * Created by alistair on 20/12/2016.
 */
public class SVMTester extends TwentyNGInterface { //similar to the trainer, but doesn't reveal the true class

    protected void trainClass(File F) throws Exception { //use the results from a single class for SVM training
        File[] files = F.listFiles();
        for (int i=301;i<files.length;i++) {
            Double[] probVec = NBClassifier.makeProbVec(files[i]);
            for (String category:NBTrainer.categoryNames()) {
                File outputFile = new File(dataLocation+category+"test");
                if (F.getName().equals(category)) {
                    DataFormatter.outputToFile(outputFile,true,probVec);
                }
                else DataFormatter.outputToFile(outputFile,false,probVec);
            }
        }
    }

}
