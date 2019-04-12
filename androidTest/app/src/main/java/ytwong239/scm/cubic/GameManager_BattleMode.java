package ytwong239.scm.cubic;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by DebbieWong on 12/4/2019.
 */

public class GameManager_BattleMode {

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

    private static final int MAXQUESTNUM = 6;
    private int currQuestNum = 0;

    private static final int QUESTSTAGE = 0;
    private static final int REDANSWERSTAGE = 1;
    private static final int BLUEANSWERSTAGE = 2;
    private static final int GREENANSWERSTAGE = 3;
    private static final int PURPLEANSWERSTAGE = 4;
    private static final int RESULTSTAGE = 5;
    private static int currStage = QUESTSTAGE;

    private QuestionBank_2D3D questionBank2D3D = new QuestionBank_2D3D();
    private QuestionBank_SPType3_Ans questionBank_spType3_ans = new QuestionBank_SPType3_Ans();
    private QuestionBank_SPType4_Ans questionBank_spType4_ans = new QuestionBank_SPType4_Ans();

    private ArrayList<Integer> ran2D3DQuest = new ArrayList<Integer>();
    private ArrayList<Integer> ran3Views = new ArrayList<Integer>();
    private ArrayList<Integer> ranSpType3 = new ArrayList<Integer>();
    private ArrayList<Integer> ranSpType4 = new ArrayList<Integer>();
    private ArrayList<Integer> ranSpType4Choice = new ArrayList<Integer>();
    private ArrayList<Integer> ranSpType4Rotate = new ArrayList<Integer>();

    private Integer[] playerFrontView = new Integer[MAXGRIDSNUM];
    private Integer[] playerSideView = new Integer[MAXGRIDSNUM];
    private Integer[] playerTopView = new Integer[MAXGRIDSNUM];

    private float totalTime30s = 30000;
    private float timeLeft30s = 0;
    private boolean resetTimer30s = false;

    private float totalTime10s = 10000;
    private float timeLeft10s = 0;
    private boolean resetTimer10s = false;

    private int spType4Ans = 0;
    private static int spType4PlayerAns = 0;

    private static boolean restart = false;

    private static boolean[][] questIsCubePresent = new boolean[MAXGRIDSNUM][MAXHEIGHTNUM];
    private static boolean[][] ardIsCubePresent = new boolean[MAXGRIDSNUM][MAXHEIGHTNUM];

    private int redScore = 0;
    private int blueScore = 0;
    private int greenScore = 0;
    private int purpleScore = 0;

    public GameManager_BattleMode(){

        for(int i = 0; i < MAXGRIDSNUM; i++){
            playerFrontView[i] = 0;
            playerSideView[i] = 0;
            playerTopView[i] = 0;
        }

        for(int i = 0; i < MAXGRIDSNUM; i++){
            for(int j = 0; j < MAXHEIGHTNUM; j++){
                questIsCubePresent[i][j] = false;
                ardIsCubePresent[i][j] = false;
            }
        }

        for(int i = 0; i < questionBank2D3D.getQuestion2D3DsLength(); i++){
            ran2D3DQuest.add(i);
        }
        Collections.shuffle(ran2D3DQuest);

        for (int i = 0; i < 3; i++) {
            ran3Views.add(i);
        }
        Collections.shuffle(ran3Views);

        for (int i = 0; i < questionBank_spType3_ans.getQuestionSPType3sLength(); i++) {
            ranSpType3.add(i);
        }
        Collections.shuffle(ranSpType3);

        for (int i = 0; i < questionBank_spType4_ans.getQuestionSPType4sLength(); i++) {
            ranSpType4.add(i);
        }
        Collections.shuffle(ranSpType4);

        for (int i = 0; i < 4; i++) {
            ranSpType4Choice.add(i);
        }
        Collections.shuffle(ranSpType4Choice);

        for (int i = 40; i < 300; i+=30) {
            ranSpType4Rotate.add(i);
        }
        Collections.shuffle(ranSpType4Rotate);

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
                OpenGLRenderer_Tips.setCurrQuestMode(ran3Views.get(0));
                QuestionBank_2D3D.setCurrQuestBank2D3DNum(ran2D3DQuest.get(0));
                Log.d("ran2D3DQuest", "q0  " +  ran2D3DQuest.get(0));
                break;
            case 1:
                currQuestMode = BUILD3DMODEL;
                OpenGLRenderer_Tips.setCurrQuestMode(BUILD3DMODEL);
                QuestionBank_2D3D.setCurrQuestBank2D3DNum(ran2D3DQuest.get(1));
                Log.d("ran2D3DQuest", "q1  " +  ran2D3DQuest.get(1));
                break;
            case 2:
                currQuestMode = SPTYPE3;
                OpenGLRenderer_Tips.setCurrQuestMode(SPTYPE3);
                QuestionBank_SPType3_Ans.setCurrQuestBankSPType3Num(ranSpType3.get(0));
                QuestionBank_SPType3_Base0.setCurrQuestBankSPType3Num(ranSpType3.get(0));
                QuestionBank_SPType3_Base1.setCurrQuestBankSPType3Num(ranSpType3.get(0));
                QuestionBank_SPType3_Quest.setCurrQuestBankSPType3Num(ranSpType3.get(0));
                Tips_SPType3.setCurrQuestNum(ranSpType3.get(0));
                Log.d("ranSpType3", "q2  " +  ranSpType3.get(0));
                break;
            case 3:
                //random front/side/top
                currQuestMode = ran3Views.get(1);
                OpenGLRenderer_Tips.setCurrQuestMode(ran3Views.get(1));
                QuestionBank_2D3D.setCurrQuestBank2D3DNum(ran2D3DQuest.get(3));
                Log.d("ran2D3DQuest", "q3  " +  ran2D3DQuest.get(3));
                break;
            case 4:
                currQuestMode = BUILD3DMODEL;
                OpenGLRenderer_Tips.setCurrQuestMode(BUILD3DMODEL);
                QuestionBank_2D3D.setCurrQuestBank2D3DNum(ran2D3DQuest.get(4));
                Log.d("ran2D3DQuest", "q4  " +  ran2D3DQuest.get(4));
                break;
            case 5:
                currQuestMode = SPTYPE4;
                OpenGLRenderer_Tips.setCurrQuestMode(SPTYPE4);
                QuestionBank_SPType4_Ans.setCurrQuestBankSPType4Num(ranSpType4.get(1));
                QuestionBank_SPType4_Base.setCurrQuestBankSPType4Num(ranSpType4.get(1));
                OpenGLRenderer_SPType4_Choice0.setChoice(ranSpType4Choice.get(0));
                OpenGLRenderer_SPType4_Choice0.setRotateY(ranSpType4Rotate.get(0));
                OpenGLRenderer_SPType4_Choice1.setChoice(ranSpType4Choice.get(1));
                OpenGLRenderer_SPType4_Choice1.setRotateY(ranSpType4Rotate.get(1));
                OpenGLRenderer_SPType4_Choice2.setChoice(ranSpType4Choice.get(2));
                OpenGLRenderer_SPType4_Choice2.setRotateY(ranSpType4Rotate.get(2));
                OpenGLRenderer_SPType4_Choice3.setChoice(ranSpType4Choice.get(3));
                OpenGLRenderer_SPType4_Choice3.setRotateY(ranSpType4Rotate.get(3));
                for(int i = 0; i < 4; i++){
                    if(ranSpType4Choice.get(i) == 0){
                        spType4Ans = i;
                        Log.d("dsfsff", String.valueOf(i));
                        break;
                    }
                }
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
            //-----here------
            case BUILD3DMODEL:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questIsCubePresent[i][j] != ardIsCubePresent[i][j]){
                            break;
                        }
                        else{
                            match++;
                        }

                        Log.d("questIsCubePresent", String.valueOf(i) + " " + String.valueOf(j) + ": "+ String.valueOf(questIsCubePresent[i][j]));
                        Log.d("arduino", String.valueOf(i) + " " + String.valueOf(j) + ": " + String.valueOf(ardIsCubePresent[i][j]));
                    }
                }
                Log.d("match", String.valueOf(match));
                if(match == MAXGRIDSNUM * MAXHEIGHTNUM){
                    nextQ();
                }
                break;
            case SPTYPE3:
                for(int i = 0; i < MAXGRIDSNUM; i++){
                    for(int j = 0; j < MAXHEIGHTNUM; j++){
                        if(questIsCubePresent[i][j] != ardIsCubePresent[i][j]){
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
            //-----here-----
            case SPTYPE4:
                if(spType4Ans == spType4PlayerAns){
                    restart();
                }
                break;
        }

    }

    public void nextQ(){

        countDownTimer10s.cancel();
        resetTimer10s = true;

        currQuestNum++;

        randQuest();

        for(int i = 0; i < MAXGRIDSNUM; i++){
            playerFrontView[i] = 0;
            playerSideView[i] = 0;
            playerTopView[i] = 0;
        }

        countDownTimer10s.start();

    }

    public void restart(){
        countDownTimer30s.cancel();

        Collections.shuffle(ran2D3DQuest);
        Collections.shuffle(ran3Views);
        Collections.shuffle(ranSpType3);
        Collections.shuffle(ranSpType4);
        Collections.shuffle(ranSpType4Choice);
        Collections.shuffle(ranSpType4Rotate);

        currQuestNum = 0;

        randQuest();

        for(int i = 0; i < MAXGRIDSNUM; i++){
            playerFrontView[i] = 0;
            playerSideView[i] = 0;
            playerTopView[i] = 0;
        }

        restart = true;

    }

    public int getCurrQuestNum(){
        return currQuestNum;
    }

    public int getCurrQuestMode(){
        return this.currQuestMode;
    }

    CountDownTimer countDownTimer30s = new CountDownTimer((long)totalTime30s, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeLeft30s = millisUntilFinished;
            resetTimer30s = false;
        }

        @Override
        public void onFinish() {
            timeLeft30s = -1;
            countDownTimer30s.cancel();
            if(currStage >= REDANSWERSTAGE && currStage <= PURPLEANSWERSTAGE){
                if(currQuestNum < MAXQUESTNUM - 1){
                    switch (currStage){
                        case REDANSWERSTAGE:
                            redScore -= 1;
                            break;
                        case BLUEANSWERSTAGE:
                            blueScore -= 1;
                            break;
                        case GREENANSWERSTAGE:
                            greenScore -= 1;
                            break;
                        case PURPLEANSWERSTAGE:
                            purpleScore -= 1;
                            break;
                    }
                    nextQ();
                    currStage = QUESTSTAGE;
                }
                else{
                    switch (currStage){
                        case REDANSWERSTAGE:
                            redScore -= 1;
                            break;
                        case BLUEANSWERSTAGE:
                            blueScore -= 1;
                            break;
                        case GREENANSWERSTAGE:
                            greenScore -= 1;
                            break;
                        case PURPLEANSWERSTAGE:
                            purpleScore -= 1;
                            break;
                    }
                    currStage = RESULTSTAGE;
                    currQuestMode = -1;
                }
            }
        }
    };

    public float getTimeLeft30s(){
        return timeLeft30s;
    }

    public float getTotalTime30s(){
        return totalTime30s;
    }

    public boolean getResetTimer30s(){
        return resetTimer30s;
    }

    CountDownTimer countDownTimer10s = new CountDownTimer((long)totalTime10s, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeLeft10s = millisUntilFinished;
            resetTimer10s = false;
        }

        @Override
        public void onFinish() {
            timeLeft10s = -1;
            countDownTimer10s.cancel();
            if(currStage == QUESTSTAGE){
                if(currQuestNum < MAXQUESTNUM - 1){
                    nextQ();
                }
                else{
                    currStage = RESULTSTAGE;
                    currQuestMode = -1;
                }
            }
        }
    };

    public float getTimeLeft10s(){
        return timeLeft10s;
    }

    public float getTotalTime10s(){
        return totalTime10s;
    }

    public boolean getResetTimer10s(){
        return resetTimer10s;
    }

    public static void setSpType4PlayerAns(int val){
        spType4PlayerAns = val;
    }

    public boolean getRestart(){
        return restart;
    }

    public void setRestartFalse(){
        restart = false;
    }

    public int getSpType4Ans(){
        return spType4Ans;
    }

    public static void setQuestIsCubePresentTrue(int grid, int height){
        questIsCubePresent[grid][height] = true;
    }

    public static void setQuestIsCubePresentFalse(int grid, int height){
        questIsCubePresent[grid][height] = false;
    }

    public static void setArdIsCubePresentTrue(int grid, int height){
        ardIsCubePresent[grid][height] = true;
    }

    public static void setArdIsCubePresentFalse(int grid, int height){
        ardIsCubePresent[grid][height] = false;
    }

    public static void setCurrStage(int val){
        currStage = val;
    }

    public int getCurrStage(){
        return currStage;
    }

    public int getRedScore(){
        return redScore;
    }

    public int getBlueScore(){
        return blueScore;
    }

    public int getGreenScore(){
        return greenScore;
    }

    public int getPurpleScore(){
        return purpleScore;
    }
}
