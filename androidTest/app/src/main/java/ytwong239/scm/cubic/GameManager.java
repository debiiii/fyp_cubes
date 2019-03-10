package ytwong239.scm.cubic;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by DebbieWong on 7/3/2019.
 */

public class GameManager {

    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    private static final int DRAWFRONTVIEW = 0;
    private static final int DRAWSIDEVIEW = 1;
    private static final int DRAWTOPVIEW = 2;
    private static final int BUILD3DMODEL = 3;
    private static final int SPTYPE3 = 4;
    private static final int SPTYPE4 = 5;

    private int currQuestMode = -1;
    private int currQuestNum = 0;

    private QuestionBank2D3D questionBank2D3D = new QuestionBank2D3D();
    private QuestionBankSPType3 questionBankSPType3 = new QuestionBankSPType3();
    private QuestionBankSPType4 questionBankSPType4 = new QuestionBankSPType4();
    private Arduino arduino = new Arduino();

    private ArrayList<Integer> ran2D3DQuest = new ArrayList<Integer>();
    private ArrayList<Integer> ran3Views = new ArrayList<Integer>();
    private ArrayList<Integer> ranSpType3 = new ArrayList<Integer>();
    private ArrayList<Integer> ranSpType4 = new ArrayList<Integer>();

    private Integer[] playerFrontView = new Integer[MAXGRIDSNUM];
    private Integer[] playerSideView = new Integer[MAXGRIDSNUM];
    private Integer[] playerTopView = new Integer[MAXGRIDSNUM];

    private float totalTime30s = 30000;
    private float timeLeft30s = 0;
    private boolean resetTimer = false;

    public GameManager(){

        for(int i = 0; i < MAXGRIDSNUM; i++){
            playerFrontView[i] = 0;
            playerSideView[i] = 0;
            playerTopView[i] = 0;
        }

        for(int i = 0; i < questionBank2D3D.getQuestion2D3DSLength(); i++){
            ran2D3DQuest.add(i);
        }
        Collections.shuffle(ran2D3DQuest);

        for (int i = 0; i < 3; i++) {
            ran3Views.add(i);
        }
        Collections.shuffle(ran3Views);

        for (int i = 0; i < questionBankSPType3.getQuestionSPType3sLength(); i++) {
            ranSpType3.add(i);
        }
        Collections.shuffle(ranSpType3);

        for (int i = 0; i < questionBankSPType4.getQuestionSPType4sLength(); i++) {
            ranSpType4.add(i);
        }
        Collections.shuffle(ranSpType4);

        randQuest();
    }

    public void setPlayerFrontView(int grid){
        this.playerFrontView[grid] = 1;
    }

    public void setPlayerSideView(int grid){
        this.playerSideView[grid] = 1;
    }

    public void setPlayerTopView(int grid){
        this.playerTopView[grid] = 1;
    }

    public void clearPlayerFrontView(int grid){
        this.playerFrontView[grid] = 0;
    }

    public void clearPlayerSideView(int grid){
        this.playerSideView[grid] = 0;
    }

    public void clearPlayerTopView(int grid){
        this.playerTopView[grid] = 0;
    }

    private void randQuest(){

        switch (currQuestNum){
            case 0:
                //random front/side/top
                currQuestMode = ran3Views.get(0);
                QuestionBank2D3D.setCurrQuestBank2D3DNum(ran2D3DQuest.get(0));
                Log.d("ran2D3DQuest", "q0  " +  ran2D3DQuest.get(0));
                break;
            case 1:
                currQuestMode = BUILD3DMODEL;
                QuestionBank2D3D.setCurrQuestBank2D3DNum(ran2D3DQuest.get(1));
                Log.d("ran2D3DQuest", "q1  " +  ran2D3DQuest.get(1));
                break;
            case 2:
                currQuestMode = SPTYPE3;
                QuestionBankSPType3.setCurrQuestBankSPType3Num(ranSpType3.get(0));
                Log.d("ranSpType3", "q2  " +  ranSpType3.get(0));
                break;
            case 3:
                //random front/side/top
                currQuestMode = ran3Views.get(1);
                QuestionBank2D3D.setCurrQuestBank2D3DNum(ran2D3DQuest.get(3));
                Log.d("ran2D3DQuest", "q3  " +  ran2D3DQuest.get(3));
                break;
            case 4:
                currQuestMode = BUILD3DMODEL;
                QuestionBank2D3D.setCurrQuestBank2D3DNum(ran2D3DQuest.get(4));
                Log.d("ran2D3DQuest", "q4  " +  ran2D3DQuest.get(4));
                break;
            case 5:
                currQuestMode = SPTYPE4;
                QuestionBankSPType4.setCurrQuestBankSPType4Num(ranSpType4.get(1));
                Log.d("ranSpType4", "q5  " +  ranSpType4.get(1));
                break;
        }


    }

    public void compare(){
        int match = 0;

        switch (currQuestMode){
            case DRAWFRONTVIEW:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    if(questionBank2D3D.getFrontView(i) != playerFrontView[i]){
                        break;
                    }
                    else{
                        match++;
                    }
                }
                if(match == MAXGRIDSNUM){
                    nextQ();
                }
                break;
            case DRAWSIDEVIEW:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    if(questionBank2D3D.getSideView(i) != playerSideView[i]){
                        break;
                    }
                    else{
                        match++;
                    }
                }
                if(match == MAXGRIDSNUM){
                    nextQ();
                }
                break;
            case DRAWTOPVIEW:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    if(questionBank2D3D.getTopView(i) != playerTopView[i]){
                        break;
                    }
                    else{
                        match++;
                    }
                }
                if(match == MAXGRIDSNUM){
                    nextQ();
                }
                break;
            case BUILD3DMODEL:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBank2D3D.getIsCubePresent(i, j) != arduino.getIsCubePresent(i,j)){
                            break;
                        }
                        else{
                            match++;
                        }
                    }
                }
                if(match == MAXGRIDSNUM * MAXHEIGHTNUM){
                    nextQ();
                }
                break;
            case SPTYPE3:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBankSPType3.getIsCubePresent(i, j) != arduino.getIsCubePresent(i,j)){
                            break;
                        }
                        else{
                            match++;
                        }
                    }
                }
                if(match == MAXGRIDSNUM * MAXHEIGHTNUM){
                    nextQ();
                }
                break;
            case SPTYPE4:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBankSPType4.getIsCubePresent(i, j) != arduino.getIsCubePresent(i,j)){
                            break;
                        }
                        else{
                            match++;
                        }
                    }
                }
                if(match == MAXGRIDSNUM * MAXHEIGHTNUM){
                    nextQ();
                }
                break;
        }
    }

    public void nextQ(){

        countDownTimer30s.cancel();
        resetTimer = true;

        currQuestNum++;
        randQuest();

        for(int i = 0; i < MAXGRIDSNUM; i++){
            playerFrontView[i] = 0;
            playerSideView[i] = 0;
            playerTopView[i] = 0;
        }

        countDownTimer30s.start();

    }

    public void restart(){
        countDownTimer30s.cancel();

        Collections.shuffle(ran2D3DQuest);
        Collections.shuffle(ran3Views);
        Collections.shuffle(ranSpType3);
        Collections.shuffle(ranSpType4);

        currQuestNum = 0;

        randQuest();

        for(int i = 0; i < MAXGRIDSNUM; i++){
            playerFrontView[i] = 0;
            playerSideView[i] = 0;
            playerTopView[i] = 0;
        }
    }

    public int getCurrQuestNum(){
        return currQuestNum;
    }

    public int getCurrQuestMode(){
        return currQuestMode;
    }

    CountDownTimer countDownTimer30s = new CountDownTimer((long)totalTime30s, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeLeft30s = millisUntilFinished;
            Log.d("dfd", String.valueOf(millisUntilFinished));
            resetTimer = false;
        }

        @Override
        public void onFinish() {
            timeLeft30s = 0;
            countDownTimer30s.cancel();
        }
    };

    public float getTimeLeft30s(){
        return timeLeft30s;
    }

    public float getTotalTime30s(){
        return totalTime30s;
    }

    public boolean getResetTimer(){
        return resetTimer;
    }
}
