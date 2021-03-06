// SM4602 Pre-production & Prototype
// Assignment 6 - Phase I Final Demonstration, Presentation, Critique and Final Report
// Group 3
// Wong Yan Ting 54388100, Fu Hiu Mei 54402448

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
        ardValue = -1;
        cubeNum = -1;
    }

    //get the arduino value from base
    public void setArdValue(int ardValue) {
        this.ardValue = ardValue;
    }


    public void update(){
        //determine the cubeNum from arduino value
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
