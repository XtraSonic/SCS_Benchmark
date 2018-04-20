/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benchmark;

import Model.TestingUnits.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author XtraSonic
 */
public class Benchmark {

    private static int NUMBER_OF_RUNS = 1000;
    private static int NUMBER_OF_OUTLIERS = 200;
    private List<TestUnit> units;
    private boolean log = true;
    private boolean detailedLog = false;

    public Benchmark(List<TestUnit> units)
    {
        this.units = units;

    }

    public void runAllTestsUnits()
    {
        units.stream()
                .forEach(testUnit ->
                {
                    System.out.println(runTestUnit(testUnit));
                    System.out.println();
                });

    }

    public Map<String, Long> runTestUnit(TestUnit tu)
    {
        int size = tu.getNumberOfTests();
        Map<String, Long> results = new HashMap<>();
        for (int i = 0; i < size; i++)
        {
            if (log)
            {
                System.out.println("Running test " + tu.getTestName(i));
            }

            String name = tu.getTestName(i);
            long time = runTest(tu, i);
            results.put(name, time);
        }

        return results;
    }

    public long runTest(TestUnit tu, int test_number)
    {
        long start, stop;
        int size;
        List<Long> times = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_RUNS; i++)
        {

            if (log && detailedLog)
            {
                System.out.println("\titeration " + i);
            }
            start = System.nanoTime();
            tu.run(test_number);
            stop = System.nanoTime();
            times.add(stop - start);
        }
        if (log)
        {
            System.out.println();
            System.out.println(times);
        }
        times = reduceOutliers(times);
        if (log)
        {
            System.out.println(times);
            System.out.println();
            System.out.println();
        }

        double avg_time = 0;
        int numberOfTimes = times.size();
        for (Long time : times)
        {
            avg_time += (double) time / numberOfTimes;
        }
        if (log)
        {
            System.out.println(avg_time);
            System.out.println();
            System.out.println();
        }
        return (long) avg_time;
    }

    private List<Long> reduceOutliers(List<Long> times)
    {
        //times = times.stream().filter(time->{return time!=0;}).collect(Collectors.toList());
        times.sort((Long o1, Long o2) -> o2.compareTo(o1));
        for (int i = 0; i < NUMBER_OF_OUTLIERS; i++)
        {
            times.remove(times.size() - 1);
            times.remove(0);
        }
        return times;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        List<TestUnit> list = new ArrayList<>();
       // list.add(new IntegerTestingUnit(1));
       //list.add(new FloatingPointTestingUnit());
       list.add(new PrimeNumberTestUnit());
       
        Benchmark b = new Benchmark(list);
        b.runAllTestsUnits();

    }
}
