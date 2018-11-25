package ytwong239.scm.bluetootharduino;

/**
 * Created by DebbieWong on 19/11/2018.
 */

public class Grid {
    static final int MAXCUBESNUM = 3;
    int ardValue;
    int cubeNum;
    boolean[] cubePresent = new boolean[MAXCUBESNUM];

    public Grid(){
        for(int i = 0; i < MAXCUBESNUM; i++){
            cubePresent[i] = false;
        }
    }

    public void setArdValue(int ardValue) {
        this.ardValue = ardValue;
    }

    public void update(){
        if(ardValue >= 765){
            cubeNum = 3;
        }
        else if(ardValue >= 680){
            cubeNum = 2;
        }
        else if(ardValue >= 490){
            cubeNum = 1;
        }
        else if(ardValue >= 0){
            cubeNum = 0;
        }

        for(int i = 0; i < MAXCUBESNUM; i++){
            if(i < cubeNum){
                cubePresent[i] = true;
            }
            else{
                cubePresent[i] = false;
            }
        }
    }

    public boolean isCubePresent(int height){
        return cubePresent[height];
    }

}
