/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benchmark;

import Model.TestingUnits.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author XtraSonic
 */
public class Benchmark {
    
    private static int NUMBER_OF_RUNS = 1000;
    private static int NUMBER_OF_OUTLIERS = 3;
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
        int size = tu.getNumberOfTests();
        Map<String,Long> results = new HashMap<>();
        for (int i = 0; i < size; i++)
        {
            String name = tu.getTestName(i);
            long time = runTest(tu, i);
            results.put(name, time);
        }
        
        return null;
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
        reduceOutliers(times);
        
        double avg_time=0;
        for(Long time: times)
        {
            avg_time+= (double)time/NUMBER_OF_RUNS;
        }
        return (long)avg_time;
    }
    
    

    private void reduceOutliers(List<Long> times)
    {
        times.sort((Long o1, Long o2) -> o1.compareTo(o2));
        for(int i =0;i<NUMBER_OF_OUTLIERS;i++)
        {
            times.remove(times.size()-1);
            times.remove(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList<Long> dirNo = new ArrayList<>();
        long a = 1;
    dirNo.add(a);
    a=12;
    dirNo.add(a);
    a=22;
    dirNo.add(a);
    a=28;
    dirNo.add(a);
    a=26;
    dirNo.add(a);
    a=24;
    dirNo.add(a);
    a=22;
    dirNo.add(a);
    a=112;
    dirNo.add(a);
    a=12;
    dirNo.add(a);
    a=22;
    dirNo.add(a);
    a=20;
    dirNo.add(a);
    a=10;

        Benchmark b =new Benchmark(null);
        b.reduceOutliers(dirNo);
        System.out.println(dirNo.toString());
        //IntegerTestingUnit itu = new IntegerTestingUnit();

    }
}
