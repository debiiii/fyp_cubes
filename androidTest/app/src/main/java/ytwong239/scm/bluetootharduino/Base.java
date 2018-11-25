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

    public void convertArdValue(String[] value){
        for(int i = 0; i < MAXGRIDSNUM; i++){
            ardValInt[i] = Integer.parseInt(value[i]);
            grids[i].setArdValue(ardValInt[i]);
        }
    }

}
