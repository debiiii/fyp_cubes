package ytwong239.scm.cubicdebug;

/**
 * Created by DebbieWong on 22/2/2019.
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
