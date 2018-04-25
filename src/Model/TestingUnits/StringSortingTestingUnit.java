/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.TestingUnits;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author XtraSonic
 */
public class StringSortingTestingUnit implements TestUnit {

    private int dataSize = 500000;
    private int stringSize = 1000;
    private String data[];

    public StringSortingTestingUnit()
    {
        this(new Random());
    }

    public StringSortingTestingUnit(long seed)
    {
        this(new Random(seed));
    }

    private StringSortingTestingUnit(Random generator)
    {
        int a;
        data = new String[dataSize];
        byte[] array = new byte[stringSize];
        for (int i = 0; i < dataSize; i++)
        {
            generator.nextBytes(array);
            data[i] = new String(array, Charset.forName("UTF-8"));
        }
    }

    @Override
    public int getNumberOfTests()
    {
        return 1;
    }

    @Override
    public String getTestName(int testNumber)
    {
        return "String sorting test";
    }

    @Override
    public int run(int testNumber)
    {
        Arrays.sort(data);
        return 0;
    }

    @Override
    public long getRefferenceTime(String testName)
    {
        return 0;//TODO set time after Benchmark is built and my PC can get the refference time
    }

}
