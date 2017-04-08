package voskoboynikov.interpolyation.diff2.a4;

import voskoboynikov.interpolyation.diff2.a4.packs.QuadricFunction;

/**
 * Polytech
 * Created by Игорь on 18.02.2017.
 */
public class ReportCreator {
    public static void main(final String[] args){
        final RepeatedMatrix repeatedMatrix = new RepeatedMatrix();

        repeatedMatrix.setFunctionPack(new QuadricFunction());
        repeatedMatrix.fill();

        repeatedMatrix.showMatrix();
    }
}
