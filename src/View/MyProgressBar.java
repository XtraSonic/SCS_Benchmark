/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author XtraSonic
 */
public class MyProgressBar implements Observer{
    
    private ProgressBar pbIterations;
    private ProgressBar pbTests;

    public MyProgressBar(ProgressBar pbTests,ProgressBar pbIterations)
    {
        this.pbTests = pbTests;
        this.pbIterations = pbIterations;
    }

    
    
    @Override
    public void update(Observable o, Object o1)
    {
        List<Double> updates=(List<Double> )o1;
        pbTests.setProgress(updates.get(0));
        pbIterations.setProgress(updates.get(1));
    }
    
    
}
