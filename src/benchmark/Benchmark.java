/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benchmark;

import Model.TestingUnits.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author XtraSonic
 */
public class Benchmark {
    
    private int NUMBER_OF_RUNS = 1000;
    private int NUMBER_OF_FILTERED_ELEMENTS = 3;
    private List<TestUnit> units;

    public Benchmark(List<TestUnit> units)
    {
        this.units = units;

    }

    public void runAllTestsUnits()
    {
        units.stream()
                .forEach(testUnit ->
                {
                    runTestUnit(testUnit);
                });

    }

    public List<Long> runTestUnit(TestUnit tu)
    {
        size = tu.getNumberOfTests();
        for (int i = 0; i < size; i++)
        {

            runTest(tu, i);
        }
        
        return times;
    }

    public long runTest(TestUnit tu, int test_number)
    {
        long start, stop;
        int size;
        List<Long> times = new ArrayList<>();
        for(int i = 0; i<NUMBER_OF_RUNS; i++)
        {
            start = System.nanoTime();
            tu.run(i);
            stop = System.nanoTime();
            times.add(stop - start);
        }
        Collection
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        IntegerTestingUnit itu = new IntegerTestingUnit();

    }

}
