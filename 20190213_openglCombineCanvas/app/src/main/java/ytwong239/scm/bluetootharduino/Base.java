// SM4602 Pre-production & Prototype
// Assignment 6 - Phase I Final Demonstration, Presentation, Critique and Final Report
// Group 3
// Wong Yan Ting 54388100, Fu Hiu Mei 54402448

package ytwong239.scm.bluetootharduino;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DebbieWong on 19/11/2018.
 */

public class Base {

    static final int MAXGRIDSNUM = 9;
    Grid grids[] = new Grid[MAXGRIDSNUM];
    int[] ardValInt = new int[MAXGRIDSNUM];

    public Base() {
        for(int i = 0; i < MAXGRIDSNUM; i++){
            grids[i] = new Grid();
            ardValInt[i] = 0;
        }
    }

    public void update(String[] value){
        for(int i = 0; i < MAXGRIDSNUM; i++){
            if(value[i] != null){
                //convert arduino value from string to int
                ardValInt[i] = Integer.valueOf(value[i]);
                //update the grid
                grids[i].setArdValue(ardValInt[i]);
                grids[i].update();
            }
        }
    }

    public void clear(){
        for(int i = 0; i < MAXGRIDSNUM; i++){
            grids[i].clear();
            ardValInt[i] = 0;
        }
    }


}
