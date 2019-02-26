package ytwong239.scm.cubic;

import android.util.Log;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by DebbieWong on 24/2/2019.
 */

public class GameManager {

    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    private static final int MAXQUESTBANKSNUM = 11;
    private static final int MAXQUESTNUM = 6;

    private final static int MAXVIEWQUESTNUM = 6;
    private final static int MAXSPTYPE3NUM = 2;
    private final static int MAXSPTYPE4NUM = 2;

    private static final int BUILD3DMODEL = 0;
    private static final int BUILDFRONTVIEW = 1;
    private static final int BUILDSIDEVIEW = 2;
    private static final int BUILDTOPVIEW = 3;
    private static final int SPTYPE3 = 4;
    private static final int SPTYPE4 = 5;
    private int currQMode = -1;
    private int currQNum = 0;
    private int currQSpType3Num = -1;
    private int currQSpType4Num = -1;

    private int randomQBankNum = 0;
    private int randomQMode = 0;

    private QuestionBank questionBank = new QuestionBank();
    private Arduino arduino = new Arduino();

    //------------

    ArrayList<Integer> ranViewQuest = new ArrayList<Integer>();
    ArrayList<Integer> ran3Views = new ArrayList<Integer>();
    ArrayList<Integer> ranSpType3 = new ArrayList<Integer>();
    ArrayList<Integer> ranSpType4 = new ArrayList<Integer>();


    //-------------

    public int currQuestBankNum;
    public int currQuestBankNumNoSp;

    public GameManager() {

        for (int i = 0; i < MAXVIEWQUESTNUM; i++) {
            ranViewQuest.add(i);
        }
        Collections.shuffle(ranViewQuest);
        for (int i = 0; i < MAXVIEWQUESTNUM; i++) {
            Log.d("dfdfd", i + "  " + ranViewQuest.get(i));
        }

        for (int i = 1; i < 4; i++) {
            ran3Views.add(i);
        }
        Collections.shuffle(ran3Views);

        for (int i = 0; i < MAXSPTYPE3NUM; i++) {
            ranSpType3.add(i);
        }
        Collections.shuffle(ranSpType3);

        for (int i = 0; i < MAXSPTYPE4NUM; i++) {
            ranSpType4.add(i);
        }
        Collections.shuffle(ranSpType4);

        rand();

    }

    private void veryRandomButRepeat(){
        randomQBankNum = new Random().nextInt(MAXQUESTBANKSNUM);
        questionBank.setCurrQuestBankNum(randomQBankNum);

        if(randomQBankNum < MAXVIEWQUESTNUM){
            randomQMode = new Random().nextInt(4);
            switch (randomQMode){
                case 0:
                    currQMode = BUILD3DMODEL;
                    break;
                case 1:
                    currQMode = BUILDFRONTVIEW;
                    break;
                case 2:
                    currQMode = BUILDSIDEVIEW;
                    break;
                case 3:
                    currQMode = BUILDTOPVIEW;
                    break;

            }
        }
        else if(randomQBankNum < MAXVIEWQUESTNUM + MAXSPTYPE3NUM){
            currQMode = SPTYPE3;
            currQSpType3Num = randomQBankNum - MAXVIEWQUESTNUM;
        }
        else if(randomQBankNum < MAXVIEWQUESTNUM + MAXSPTYPE3NUM + MAXSPTYPE4NUM){
            currQMode = SPTYPE4;
            currQSpType4Num = randomQBankNum - (MAXVIEWQUESTNUM + MAXSPTYPE3NUM);
        }
    }

    private void error(){
        for(int i = 0; i < MAXVIEWQUESTNUM; i++){
            ranViewQuest.add(i);
        }
        Collections.shuffle(ranViewQuest);
        for(int i = 0; i < MAXVIEWQUESTNUM; i++){
            Log.d("dfdfd", i + "  " + ranViewQuest.get(i));
        }

        for(int i = 1; i < 4; i++){
            ran3Views.add(i);
        }
        Collections.shuffle(ran3Views);

        for(int i = 0; i < MAXSPTYPE3NUM; i++){
            ranSpType3.add(i);
        }
        Collections.shuffle(ranSpType3);

        for(int i = 0; i < MAXSPTYPE4NUM; i++){
            ranSpType4.add(i);
        }
        Collections.shuffle(ranSpType4);


        switch (currQNum){
            case 0:
                currQMode = ran3Views.get(0);
                questionBank.setCurrQuestBankNum(ranViewQuest.get(0));
                break;
            case 1:
                currQMode = BUILD3DMODEL;
                questionBank.setCurrQuestBankNum(ranViewQuest.get(1));
                break;
            case 2:
                currQMode = SPTYPE3;
                currQSpType3Num = ranSpType3.get(0);
                questionBank.setCurrQuestBankNum(ranSpType3.get(0) + MAXVIEWQUESTNUM);
                break;
            case 3:
                currQMode = ran3Views.get(1);
                questionBank.setCurrQuestBankNum(ranViewQuest.get(3));
                break;
            case 4:
                currQMode = BUILD3DMODEL;
                questionBank.setCurrQuestBankNum(ranViewQuest.get(4));
                break;
            case 5:
                currQMode = SPTYPE4;
                currQSpType4Num = ranSpType4.get(1);
                questionBank.setCurrQuestBankNum(ranSpType4.get(1) + MAXVIEWQUESTNUM + MAXSPTYPE3NUM);
                break;

        }
    }

    private void rand(){
        switch (currQNum){
            case 0:
                currQMode = ran3Views.get(0);
                //questionBank.setCurrQuestBankNum(ranViewQuest.get(0));
                currQuestBankNum = ranViewQuest.get(0);
                currQuestBankNumNoSp = ranViewQuest.get(0);
                break;
            case 1:
                currQMode = BUILD3DMODEL;
                //questionBank.setCurrQuestBankNum(ranViewQuest.get(1));
                currQuestBankNum = ranViewQuest.get(1);
                currQuestBankNumNoSp = ranViewQuest.get(0);
                break;
            case 2:
                currQMode = SPTYPE3;
                currQSpType3Num = ranSpType3.get(0);
                //questionBank.setCurrQuestBankNum(ranSpType3.get(0) + MAXVIEWQUESTNUM);
                currQuestBankNum = ranSpType3.get(0) + MAXVIEWQUESTNUM;
                break;
            case 3:
                currQMode = ran3Views.get(1);
                //questionBank.setCurrQuestBankNum(ranViewQuest.get(3));
                currQuestBankNum = ranViewQuest.get(3);
                currQuestBankNumNoSp = ranViewQuest.get(3);
                break;
            case 4:
                currQMode = BUILD3DMODEL;
                //questionBank.setCurrQuestBankNum(ranViewQuest.get(4));
                currQuestBankNum = ranViewQuest.get(4);
                currQuestBankNumNoSp = ranViewQuest.get(4);
                break;
            case 5:
                currQMode = SPTYPE4;
                currQSpType4Num = ranSpType4.get(1);
                //questionBank.setCurrQuestBankNum(ranSpType4.get(1) + MAXVIEWQUESTNUM + MAXSPTYPE3NUM);
                currQuestBankNum = ranSpType4.get(1) + MAXVIEWQUESTNUM + MAXSPTYPE3NUM;
                break;

        }

    }

    public void compare(){
        int match = 0;

        switch (currQMode){
            case BUILD3DMODEL:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBank.getIsCubePresent(i, j) != arduino.getIsCubePresent(i,j)){
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

            case BUILDFRONTVIEW:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    if(questionBank.getIsCubePresentFront(i) == arduino.getIsCubePresent(i,0)){
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

            case BUILDSIDEVIEW:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    if(questionBank.getIsCubePresentSide(i) == arduino.getIsCubePresent(i,0)){
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

            case BUILDTOPVIEW:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    if(questionBank.getIsCubePresentTop(i) == arduino.getIsCubePresent(i,0)){
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

            case SPTYPE3:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBank.getIsCubePresent(i, j) == arduino.getIsCubePresent(i,j)){
                            break;
                        }
                        else{
                            nextQ();
                        }
                    }
                }
                break;

            case SPTYPE4:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questionBank.getIsCubePresent(i, j) == arduino.getIsCubePresent(i,j)){
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

        currQNum++;

        rand();

    }

    public void restart(){
        for(int i = 0; i < MAXVIEWQUESTNUM; i++){
            ranViewQuest.add(i);
        }
        Collections.shuffle(ranViewQuest);
        for(int i = 0; i < MAXVIEWQUESTNUM; i++){
            Log.d("dfdfd", i + "  " + ranViewQuest.get(i));
        }

        for(int i = 1; i < 4; i++){
            ran3Views.add(i);
        }
        Collections.shuffle(ran3Views);

        for(int i = 0; i < MAXSPTYPE3NUM; i++){
            ranSpType3.add(i);
        }
        Collections.shuffle(ranSpType3);

        for(int i = 0; i < MAXSPTYPE4NUM; i++){
            ranSpType4.add(i);
        }
        Collections.shuffle(ranSpType4);

        currQNum = 0;

        rand();



    }

    public int getCurrQNum(){
        return currQNum;
    }

    public int getCurrQMode(){
        return currQMode;
    }

    public int getCurrQSpType3Num(){
        return currQSpType3Num;
    }

    public int getCurrQSpType4Num(){
        return currQSpType4Num;
    }


    






}
