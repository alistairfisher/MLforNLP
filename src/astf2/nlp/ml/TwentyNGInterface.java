package astf2.nlp.ml;

import java.io.File;

/**
 * Created by alistair on 20/12/2016.
 */
public abstract class TwentyNGInterface {

    protected static File twentyNewsGroupsHome = NBTrainer.twentyNewsGroupsHome;
    protected static String dataLocation = "/Users/alistair/Documents/svm_light_osx.8.4_i7/nbvecs/";

    protected void run(boolean sanitise) throws Exception { //create an SVM vector file in dataLocation for every 20 News Class
        File trainingLocation = new File(dataLocation); //sanitise training location
        if (sanitise) {
            for (File f: trainingLocation.listFiles()) {
                f.delete();
            }
        }
        for (File F:twentyNewsGroupsHome.listFiles()) {
            if (F.isDirectory()) {
                trainClass(F);
            }
        }
    }

    abstract protected void trainClass(File F) throws Exception;
}
