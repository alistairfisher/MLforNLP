package astf2.nlp.ml;

import java.io.File;

/**
 * Created by alistair on 21/12/2016.
 */
public class NBTester extends TwentyNGInterface {

    int correct;
    int total;

    @Override //(sloppy code...)
    protected void trainClass(File F) throws Exception {
        int classcorrect = 0;
        int classtotal = 0;
        File[] files = F.listFiles();
        for (int i=NBClassifier.trainingThreshold;i<files.length;i++) {
            Double[] probVec = NBClassifier.makeProbVec(files[i]);
            String classifierChoice = getChoice(probVec,F);
            if (classifierChoice.equals(F.getName())) {
                correct++;
                classcorrect++;
            }
            total++;
            classtotal++;
        }
        System.out.printf("Class: %s, accuracy %f\n",F.getName(),((double) classcorrect)/classtotal);
    }

    String getChoice(Double[] probVec, File F) {
        int max = 0;
        for (int i=1;i<probVec.length;i++) {
            if (probVec[i] > probVec[max]) {
                max = i;
            }
        }
        return NBTrainer.categoryNames()[max];
    }

    Double accuracy() {
        return ((double) correct)/total;
    }

}
