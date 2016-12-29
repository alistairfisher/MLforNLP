package astf2.nlp.ml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by alistair on 18/12/2016.
 */
public class DataFormatter {

    /*
    * Outputs training and test vectors into a file format in the form readable by SVMLight.
    *
    */

    private static String format(boolean cl, Double[] vector) {
        StringBuilder builder = new StringBuilder();
        if (cl) builder.append("+1");
        else builder.append("-1");
        builder.append(" ");
        for (int i=0;i<NBClassifier.categories.size();i++) {
            builder.append(i+1);
            builder.append(":");
            builder.append(vector[i]);
            builder.append(" ");
        }
        builder.append('\n');
        return builder.toString();
    }

    static void outputToFile(File F, boolean cl, Double[] vector) throws IOException {
        String string = format(cl,vector);
        FileWriter fstream = new FileWriter(F, true);
        fstream.write(string);
        fstream.close();
    }

}
