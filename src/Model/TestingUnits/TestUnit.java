/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.TestingUnits;

import java.time.Duration;

/**
 *
 * @author XtraSonic
 */
public interface TestUnit {
    public int getNumberOfTests();
    public String getTestName(int testNumber); 
    public void run(int testNumber);
    public long getRefferenceTime(int testNumber);
}