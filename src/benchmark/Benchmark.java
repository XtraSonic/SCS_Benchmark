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
import java.util.Map.Entry;
import java.util.Observable;
import java.util.stream.Collectors;

/**
 *
 * @author XtraSonic
 */
public class Benchmark extends Observable implements Runnable {

    private static int NUMBER_OF_RUNS = 1000;
    private static int NUMBER_OF_OUTLIERS = 200;
    private TestUnit unit;
    private boolean log = false;
    private boolean detailedLog = false;
    private Double iterationProgress;
    private Double testNumberProgress;
    private List<Double> updateArgs = new ArrayList<>();

    private Map<String, Long> testTimes = new HashMap<>();

    public Benchmark(TestUnit units)
    {
        this.unit = units;
        testNumberProgress = 0.;
        iterationProgress = 0.;
        updateArgs.add(testNumberProgress);
        updateArgs.add(iterationProgress);
        updateArgs.add(0.);

    }

    public Map<String, Long> runTestUnit()
    {
        int size = unit.getNumberOfTests();
        Map<String, Long> results = new HashMap<>();
        testNumberProgress = 0.;
        for (int i = 0; i < size; i++)
        {
            if (log)
            {
                System.out.println("Running test " + unit.getTestName(i));
            }

            String name = unit.getTestName(i);
            long time = runTest(unit, i);
            results.put(name, time);
            testNumberProgress = (i + 1.) / size;
            updateArgs.set(0, testNumberProgress);
            setChanged();
            notifyObservers(updateArgs);
        }
        testTimes = results;
        return results;
    }

    public long runTest(TestUnit tu, int test_number)
    {
        long start, stop;
        int size;
        List<Long> times = new ArrayList<>();
        iterationProgress = 0.;
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
            iterationProgress = (i + 1.) / NUMBER_OF_RUNS;
            updateArgs.set(1, iterationProgress);
            setChanged();
            notifyObservers(updateArgs);
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

    @Override
    public void run()
    {
        updateArgs.set(0, 0.);
        updateArgs.set(1, 0.);
        updateArgs.set(2, 0.);
        setChanged();
        notifyObservers(updateArgs);
        runTestUnit();
        System.out.println(testTimes);
        System.out.println(getScore());
        updateArgs.set(2, 1.);
        setChanged();
        notifyObservers(updateArgs);

    }

    public Double getProgress()
    {
        return iterationProgress;
    }

    public Map<String, Integer> getScore()
    {

        return testTimes.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> unit.calculateScore(e.getKey(), e.getValue())
                //(int)(Math.round(10000*(double)e.getValue()/unit.getRefferenceTime(e.getKey()))/100)
                ));

    }

    public long getOverallScore()
    {
        double result = 1;
        double power = 1. / testTimes.size();
        Map<String, Integer> scores = getScore();
        System.out.println(scores);
        Integer value = null;
        for (Entry e : scores.entrySet())
        {
            value = (Integer) e.getValue();
            result *= Math.pow(value.doubleValue(), power);
            //System.out.println("res: " + result + ' ' + Math.pow(value.doubleValue(), power));
        }
        return (long) result;
    }

}
