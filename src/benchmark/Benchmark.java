/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benchmark;

import Model.TestingUnits.*;

/**
 *
 * @author XtraSonic
 */
public class Benchmark {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        IntegerTestingUnit itu = new IntegerTestingUnit();
        long start, stop, time;
        int size = itu.getNumberOfTests();
        for (int i = 0; i < size; i++)
        {
            start = System.nanoTime();
            itu.run(i);
            stop = System.nanoTime();
            time =stop-start;
            //System.out.println("Time for " + itu.getTestName(i) +"="+ time);
            System.out.format("Time for %30s = %10d\n",itu.getTestName(i), time);
        }
    }

}
