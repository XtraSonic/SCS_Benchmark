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
public class IntegerTestingUnit implements TestUnit{

    private static final List<String> testNames= new ArrayList(Arrays.asList(
                                                        "Addition Test",
                                                        "Subtraction Test",
                                                        "Multiplication Test",
                                                         "Division Test",
                                                         "Left Shift Test",
                                                         "Right Shift Test",
                                                         "AND Test",
                                                         "OR Test",
                                                         "XOR Test",
                                                         "NOT Test",
                                                         "Arithmetic Test"));
    public static final int ADDITION_TEST = testNames.indexOf("Addition Test");
    public static final int SUBTRACTION_TEST  = testNames.indexOf("Subtraction Test");
    public static final int MULTIPLICATION_TEST  = testNames.indexOf("Multiplication Test");
    public static final int DIVISION_TEST = testNames.indexOf("Division Test");
    public static final int LEFT_SHIFT_TEST = testNames.indexOf("Left Shift Test");
    public static final int RIGHT_SHIFT_TEST = testNames.indexOf("Right Shift Test");
    public static final int AND_TEST = testNames.indexOf("AND Test");
    public static final int OR_TEST = testNames.indexOf("OR Test");
    public static final int XOR_TEST = testNames.indexOf("XOR Test");
    public static final int NOT_TEST = testNames.indexOf("NOT Test");
    public static final int ARITHMETIC_TEST = testNames.indexOf("Arithmetic Test");
    
    private int dataSize = 500000000;
    private int data[];
    
    
    public IntegerTestingUnit()
    {
        this(new Random());
    }
    
    public IntegerTestingUnit(long seed)
    {
        this(new Random(seed));
    }
    
    private IntegerTestingUnit(Random generator)
    {
        int a;
        data = new int[dataSize];
        for(int i =0;i<dataSize;i++)
        {
            do
            {a=generator.nextInt();            
            }while(a == 0);
            data[i]=a;
        }
    }
    
    
    
    @Override
    public void run(int testNumber)
    {
        switch(testNumber)
        {
            case 0:
                stressAddition();
                break;
                
            case 1:
                stressSubtraction();
                break;
            case 2:
                stressMultiplication();
                break;
            case 3:
                stressDivision();
                break;
            case 4:
                stressLeftShift();
                break;
            case 5:
                stressRightShift();
                break;
            case 6:
                stressAND();
                break;
            case 7:
                stressOR();
                break;
            case 8:
                stressXOR();
                break;
            case 9:
                stressNOT();
                break;
            case 10:
                stressArithmetic();
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
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

    private void stressAddition()
    {
        int acc=0;
        for(int i =0; i<dataSize;i++)
        {
            acc+=data[i];
        }
    }
    
    private void stressSubtraction()
    {
        int acc=0;
        for(int i =0; i<dataSize;i++)
        {
            acc-=data[i];
        }
    }
    
    private void stressMultiplication()
    {
        int acc=1;
        for(int i =0; i<dataSize;i++)
        {
            acc*=data[i];
        }
    }
    
    private void stressDivision()
    {
        int acc=Integer.MAX_VALUE;
        for(int i =0; i<dataSize;i++)
        {
            acc/=data[i];
        }
    }
    
    private void stressRightShift()
    {
        int acc=0;
        for(int i =0; i<dataSize;i++)
        {
            acc+=data[i]>>1;
        }
    }
    private void stressLeftShift()
    {
        int acc=0;
        for(int i =0; i<dataSize;i++)
        {
            acc+=data[i]<<1;
        }
    }
    private void stressAND()
    {
        int acc=0;
        for(int i =1; i<dataSize;i++)
        {
            acc+=data[i-1]&data[i];
        }
    }
    private void stressOR()
    {
        int acc=0;
        for(int i =1; i<dataSize;i++)
        {
            acc+=data[i-1]|data[i];
        }
    }
    private void stressXOR()
    {
        int acc=0;
        for(int i =1; i<dataSize;i++)
        {
            acc+=data[i-1]^data[i];
        }
    }
    
    private void stressNOT()
    {
        int acc=0;
        for(int i =0; i<dataSize;i++)
        {
            acc+=~data[i];
        }
    }

    private void stressArithmetic()
    {
        int acc=0;
        for(int i =0; i<dataSize-3;i++)
        {
            acc+=data[i]*data[i+1]-data[i+2]/data[i+3];
        }
    }
    
    
    
}
