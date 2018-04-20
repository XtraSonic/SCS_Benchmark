/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.TestingUnits;

import static java.lang.Math.sqrt;

/**
 *
 * @author XtraSonic
 */
public class PrimeNumberTestUnit implements TestUnit {

    public static final String TEST_NAME="Prime Numbers Test";
    
    private int numberOfPrimesToFind = 20000;
    private boolean log=true;
    
    @Override
    public int getNumberOfTests()
    {
        return 1;
    }

    @Override
    public String getTestName(int testNumber)
    {
        return TEST_NAME;
    }

    @Override
    public int run(int testNumber)
    {
        int count = 1;
        int i=3;
        while(count<numberOfPrimesToFind)
        {
            if(isPrime(i))
            {
                count++;
                //System.out.println(count);
            }
            i+=2;
        }
        return 0;
    }

    @Override
    public long getRefferenceTime(int testNumber)
    {
        return 0;//TODO set time after Benchmark is built and my PC can get the refference time
    }

    private boolean isPrime(int number)
    {
        int limit = (int) sqrt(number);
        for(int i=2;i<limit;i ++)
            if(number % i==0)
                return false;
        return true;
    }

}
