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
public class IntegerTestingUnit implements TestUnit {

    private static final List<String> testNames = new ArrayList(Arrays.asList(
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
    public static final int SUBTRACTION_TEST = testNames.indexOf("Subtraction Test");
    public static final int MULTIPLICATION_TEST = testNames.indexOf("Multiplication Test");
    public static final int DIVISION_TEST = testNames.indexOf("Division Test");
    public static final int LEFT_SHIFT_TEST = testNames.indexOf("Left Shift Test");
    public static final int RIGHT_SHIFT_TEST = testNames.indexOf("Right Shift Test");
    public static final int AND_TEST = testNames.indexOf("AND Test");
    public static final int OR_TEST = testNames.indexOf("OR Test");
    public static final int XOR_TEST = testNames.indexOf("XOR Test");
    public static final int NOT_TEST = testNames.indexOf("NOT Test");
    public static final int ARITHMETIC_TEST = testNames.indexOf("Arithmetic Test");

    private int dataSize = 5000000;
    private int data[];
    private int reductionFactor = 1;

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
        for (int i = 0; i < dataSize; i++)
        {
            do
            {
                a = generator.nextInt();
            } while (a == 0);
            data[i] = a;
        }
    }

    @Override
    public int run(int testNumber)
    {
        int res;
        switch (testNumber)
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
                res = stressLeftShift();
                break;
            case 5:
                res = stressRightShift();
                break;
            case 6:
                res = stressAND();
                break;
            case 7:
                res = stressOR();
                break;
            case 8:
                res = stressXOR();
                break;
            case 9:
                res = stressNOT();
                break;
            case 10:
                res = stressArithmetic();
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        return res;
    }

    @Override
    public long getRefferenceTime(String testName)
    {
        int testNumber = testNames.indexOf(testName);
        switch (testNumber)
        {

            case 0:
                return 4499835;
            case 1:
                return 4456779;
            case 2:
                return 6617342;
            case 3:
                return 35173178;
            case 4:
                return 4240571;
            case 5:
                return 4230207;
            case 6:
                return 4770522;
            case 7:
                return 4774407;
            case 8:
                return 5150759;
            case 9:
                return 5060338;
            case 10:
                return 61297273;
            default:
                System.err.println("No such test");
                return -1;
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

    private int stressAddition()
    {
        int acc = 0;
        for (int i = 0; i < dataSize; i++)
        {
            acc += data[i];
        }
        return acc;
    }

    private int stressSubtraction()
    {
        int acc = 0;
        for (int i = 0; i < dataSize; i++)
        {
            acc -= data[i];
        }
        return acc;
    }

    private int stressMultiplication()
    {
        int acc = 1;
        for (int i = 0; i < dataSize; i++)
        {
            acc *= data[i];
        }
        return acc;
    }

    private int stressDivision()
    {
        int acc = Integer.MAX_VALUE;
        int reducedDataSize = dataSize / reductionFactor;
        for (int i = 0; i < reducedDataSize; i++)
        {
            acc /= data[i];
        }
        return acc;
    }

    private int stressRightShift()
    {
        int acc = 0;
        for (int i = 0; i < dataSize; i++)
        {
            acc += data[i] >> 1;
        }
        return acc;
    }

    private int stressLeftShift()
    {
        int acc = 0;
        for (int i = 0; i < dataSize; i++)
        {
            acc += data[i] << 1;
        }
        return acc;
    }

    private int stressAND()
    {
        int acc = 0;
        for (int i = 1; i < dataSize; i++)
        {
            acc += data[i - 1] & data[i];
        }
        return acc;
    }

    private int stressOR()
    {
        int acc = 0;
        for (int i = 1; i < dataSize; i++)
        {
            acc += data[i - 1] | data[i];
        }
        return acc;
    }

    private int stressXOR()
    {
        int acc = 0;
        for (int i = 1; i < dataSize; i++)
        {
            acc += data[i - 1] ^ data[i];
        }
        return acc;
    }

    private int stressNOT()
    {
        int acc = 0;
        for (int i = 0; i < dataSize; i++)
        {
            acc += ~data[i];
        }
        return acc;
    }

    private int stressArithmetic()
    {
        int acc = 0;
        int reducedDataSize = dataSize / reductionFactor - 3;
        for (int i = 0; i < reducedDataSize; i++)
        {
            acc += data[i] * data[i + 1] - data[i + 2] / data[i + 3];
        }
        return acc;
    }

}
