package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 22/2/2019.
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
        ardValue = -1;
        cubeNum = -1;
    }

    //get the arduino value from base
    public void setArdValue(int ardValue) {
        this.ardValue = ardValue;
    }


    public void update(){
        //determine the cubeNum from arduino value
        if(ardValue >= 750){
            cubeNum = 3;
        }
        else if(ardValue >= 670){
            cubeNum = 2;
        }
        else if(ardValue >= 490){
            cubeNum = 1;
        }
        else if(ardValue >= 0){
            cubeNum = 0;
        }

        //determine the cubePresent from cubeNum
        for(int i = 0; i < MAXCUBESNUM; i++){
            if(i < cubeNum){
                cubePresent[i] = true;
            }
            else{
                cubePresent[i] = false;
            }
        }
    }

    //***testing***
    public void updateRan(int _cubeNum){

        cubeNum = _cubeNum;

        //determine the cubePresent from cubeNum
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

    public void clear(){
        ardValue = -1;
        cubeNum = -1;
        for(int i = 0; i < MAXCUBESNUM; i++){
            cubePresent[i] = false;
        }
    }

}
