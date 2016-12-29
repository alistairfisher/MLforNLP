package astf2.nlp.ml;

/**
 * Created by alistair on 21/12/2016.
 */
public class Result {

    Double accuracy;
    Double precision;
    Double recall;
    Double F1 = (recall*precision)/(recall+precision);

    Result(Double accuracy,Double precision, Double recall) {
        this.accuracy = accuracy;
        this.precision = precision;
        this.recall = recall;
    }

}
