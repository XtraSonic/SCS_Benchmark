/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.TestingUnits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author XtraSonic
 */
public class FloatingPointTestingUnit implements TestUnit{
    
        private static final List<String> testNames= new ArrayList(Arrays.asList(
                                                        "Addition Test",
                                                        "Subtraction Test",
                                                        "Multiplication Test",
                                                        "Division Test",
                                                        "Arithmetic Test"));
    public static final int ADDITION_TEST = testNames.indexOf("Addition Test");
    public static final int SUBTRACTION_TEST  = testNames.indexOf("Subtraction Test");
    public static final int MULTIPLICATION_TEST  = testNames.indexOf("Multiplication Test");
    public static final int DIVISION_TEST = testNames.indexOf("Division Test");
    public static final int ARITHMETIC_TEST = testNames.indexOf("Arithmetic Test");
    
    private int dataSize = 5000000;
    private double data[];
    private int reductionFactor = 1;
    
    public FloatingPointTestingUnit()
    {
        this(new Random());
    }
    
    public FloatingPointTestingUnit(long seed)
    {
        this(new Random(seed));
    }
    
    private FloatingPointTestingUnit(Random generator)
    {
        double a;
        data = new double[dataSize];
        for(int i =0;i<dataSize;i++)
        {
            do
            {a=generator.nextDouble();            
            }while(a == 0);
            data[i]=a;
        }
    }
    
    
    
    @Override
    public int run(int testNumber)
    {
        double res;
        switch(testNumber)
        {
            case 0:
                res = stressAddition();
                break;
                
            case 1:
                res = stressSubtraction();
                break;
            case 2:
                res = stressMultiplication();
                break;
            case 3:
                res = stressDivision();
                break;
            case 4:
                res = stressArithmetic();
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        return (int) res;
    }

    @Override
    public long getRefferenceTime(int testNumber)
    {
        switch(testNumber)
        {
            default:
                return 0;//TODO set time after Benchmark is built and my PC can get the refference time
        }
    }

    @Override
    public int getNumberOfTests()
    {
        return testNames.size();
    }

    @Override
    public String getTestName(int testNumber)
    {
        return testNames.get(testNumber);
    }

    private double stressAddition()
    {
        double acc=0;
        for(int i =0; i<dataSize;i++)
        {
            acc+=data[i];
        }
        return acc;
    }
    
    private double stressSubtraction()
    {
        double acc=0;
        for(int i =0; i<dataSize;i++)
        {
            acc-=data[i];
        }
        return acc;
    }
    
    private double stressMultiplication()
    {
        double acc=1;
        for(int i =0; i<dataSize;i++)
        {
            acc*=data[i];
        }
        return acc;
    }
    
    private double stressDivision()
    {
        double acc=Integer.MAX_VALUE;
        double reducedDataSize = dataSize/reductionFactor;
        for(int i =0; i<reducedDataSize;i++)
        {
            acc/=data[i];
        }
        return acc;
    }
    
    private int stressArithmetic()
    {
        int acc=0;
        int reducedDataSize = dataSize/reductionFactor-3;
        for(int i =0; i<reducedDataSize;i++)
        {
            acc+=data[i]*data[i+1]-data[i+2]/data[i+3];
        }
        return acc;
    }
    
    
    
}
