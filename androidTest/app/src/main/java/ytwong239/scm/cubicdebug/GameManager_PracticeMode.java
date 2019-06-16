package ytwong239.scm.cubicdebug;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by DebbieWong on 7/3/2019.
 */

public class GameManager_PracticeMode {

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

    private static final int MAXQUESTNUM = 6;

    private int currQuestMode = -1;
    private int currQuestNum = 0;

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

    private CountDownTimer countDownTimer30s;
    private static final long TOTALTIME30S = 30000;
    private long timeLeft30s = TOTALTIME30S;
    private boolean resetTimer = false;
    private boolean timer30sIsRunning;

    private int spType4Ans = 0;
    private static int spType4PlayerAns = 0;

    private static boolean restart = false;

    private static boolean[][] questIsCubePresent = new boolean[MAXGRIDSNUM][MAXHEIGHTNUM];
    private static boolean[][] ardIsCubePresent = new boolean[MAXGRIDSNUM][MAXHEIGHTNUM];

    private static boolean canGetPuzzle = false;

    private static boolean isResultPage = false;

    private static int[] timeUsed = new int[MAXQUESTNUM];
    private static int[] tipUsed = new int[MAXQUESTNUM];
    private int[] wrongAns = new int[MAXQUESTNUM];

    private int abilityScore0 = 0;
    private int abilityScore1 = 0;
    private int abilityScore2 = 0;
    private int abilityScore3 = 0;

    public GameManager_PracticeMode(){

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

        for(int i = 0; i < MAXQUESTNUM; i++){
            timeUsed[i] = 0;
            tipUsed[i] = 0;
            wrongAns[i] = 0;
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
                        Log.d("PMdsfsff", String.valueOf(i));
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
                else{
                    wrongAns[currQuestNum]++;
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
                else{
                    wrongAns[currQuestNum]++;
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
                else{
                    wrongAns[currQuestNum]++;
                }
                break;
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
                else{
                    wrongAns[currQuestNum]++;
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
                else{
                    wrongAns[currQuestNum]++;
                }
                break;
            case SPTYPE4:
                if(spType4Ans == spType4PlayerAns){
                    canGetPuzzle = true;
                    isResultPage = true;
                    calAbility();
                }
                else{
                    wrongAns[currQuestNum]++;
                }
                break;
        }

    }

    public void nextQ(){

        countDownTimer30s.cancel();
        timer30sIsRunning = false;
        resetTimer = true;

        currQuestNum++;

        randQuest();

        for(int i = 0; i < MAXGRIDSNUM; i++){
            playerFrontView[i] = 0;
            playerSideView[i] = 0;
            playerTopView[i] = 0;
        }

        timeLeft30s = TOTALTIME30S;
        timer30sStart();

    }

    private static final int PERSECOND = 30;
    private static final int NOCUBEMINTIME = 5;
    private static final int NOCUBEMAXTIME = 60;
    private static final int HVCUBEMINTIME = 20;
    private static final int HVCUBEMAXTIME = 120;
    private static final int SCOREMIN = 0;
    private static final int SCOREMAX = 100;
    private static final int TIPMINUS = -10;
    private static final int WRONGANSMINUS = -10;

    private void calAbility(){
        float[] time = new float[MAXQUESTNUM];
        float[] timeScore = new float[MAXQUESTNUM];
        for(int i = 0; i < MAXQUESTNUM; i++){
            time[i] = timeUsed[i] / PERSECOND;
            if(i == 0 || i == 3 || i == 5){
                if(time[i] < NOCUBEMINTIME){
                    time[i] = NOCUBEMINTIME;
                }
                else if(time[i] > NOCUBEMAXTIME){
                    time[i] = NOCUBEMAXTIME;
                }
                timeScore[i] = remap(time[i], NOCUBEMINTIME, NOCUBEMAXTIME, SCOREMAX, SCOREMIN);
            }
            else if(i == 1 || i == 2 || i == 4){
                if(time[i] < HVCUBEMINTIME){
                    time[i] = HVCUBEMINTIME;
                }
                else if(time[i] > HVCUBEMAXTIME){
                    time[i] = HVCUBEMAXTIME;
                }
                timeScore[i] = remap(time[i], HVCUBEMINTIME, HVCUBEMAXTIME, SCOREMAX, SCOREMIN);
            }
        }

        int[] tipScore = new int[MAXQUESTNUM];
        int[] wrongAnsScore = new int[MAXQUESTNUM];
        for(int i = 0; i < MAXQUESTNUM; i++){
            tipScore[i] = tipUsed[i] * TIPMINUS;
            wrongAnsScore[i] = wrongAns[i] * WRONGANSMINUS;
        }

        abilityScore0 = (int)(timeScore[0] + timeScore[3]) / 2 + (tipScore[0] + tipScore[3]) / 2 + (wrongAnsScore[0] + wrongAnsScore[3]) / 2;
        abilityScore1 = (int)(timeScore[1] + timeScore[4]) / 2 + (tipScore[1] + tipScore[4]) / 2 + (wrongAnsScore[1] + wrongAnsScore[4]) / 2;
        abilityScore2 = (int)timeScore[2] + tipScore[2] + wrongAnsScore[2];
        abilityScore3 = (int)timeScore[5] + tipScore[5] + wrongAnsScore[5];

        if(abilityScore0 < 0){
            abilityScore0 = 0;
        }
        if(abilityScore1 < 0){
            abilityScore1 = 0;
        }
        if(abilityScore2 < 0){
            abilityScore2 = 0;
        }
        if(abilityScore3 < 0){
            abilityScore3 = 0;
        }

    }

    private float remap(float value, float from1, float to1, float from2, float to2){
        return (value - from1) / (to1 - from1) * (to2 - from2) + from2;
    }

    public void restart(){
        countDownTimer30s.cancel();
        timer30sIsRunning = false;
        timeLeft30s = TOTALTIME30S;

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

        for(int i = 0; i < MAXQUESTNUM; i++){
            timeUsed[i] = 0;
            tipUsed[i] = 0;
            wrongAns[i] = 0;
        }

        abilityScore0 = 0;
        abilityScore1 = 0;
        abilityScore2 = 0;
        abilityScore3 = 0;

        restart = true;

    }

    public int getCurrQuestNum(){
        return currQuestNum;
    }

    public int getCurrQuestMode(){
        return this.currQuestMode;
    }

    public void timer30sStart(){
        resetTimer = false;
        countDownTimer30s = new CountDownTimer(timeLeft30s, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft30s = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                timeLeft30s = -1;
                timer30sIsRunning = false;
            }
        }.start();

        timer30sIsRunning = true;
    }

    public void timer30sPause(){
        countDownTimer30s.cancel();
        timer30sIsRunning = false;
    }

    public long getTimeLeft30s(){
        return timeLeft30s;
    }
    public long getTotalTime30s(){
        return TOTALTIME30S;
    }
    public boolean getResetTimer(){
        return resetTimer;
    }
    public boolean getTimer30sIsRunning(){
        return timer30sIsRunning;
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

    public boolean isCanGetPuzzle(){
        return canGetPuzzle;
    }

    public static void setCanGetPuzzleFalse(){
        canGetPuzzle = false;
    }

    public static void setIsResultPage(boolean val){
        isResultPage = val;
    }

    public boolean getIsResultPage(){
        return isResultPage;
    }

    public static void setTimeUsed(int quest, int val){
        timeUsed[quest] = val;
    }

    public int getTimeUsed(int quest){
        return timeUsed[quest];
    }

    public static void setTipUsed(int quest, int val){
        tipUsed[quest] = val;
    }

    public int getTipUsed(int quest){
        return tipUsed[quest];
    }

    public int getWrongAns(int quest){
        return wrongAns[quest];
    }

    public int getAbilityScore0(){
        return abilityScore0;
    }

    public int getAbilityScore1(){
        return abilityScore1;
    }

    public int getAbilityScore2(){
        return abilityScore2;
    }

    public int getAbilityScore3(){
        return abilityScore3;
    }

}
