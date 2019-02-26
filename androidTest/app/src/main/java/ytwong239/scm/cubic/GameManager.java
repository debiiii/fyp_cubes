package ytwong239.scm.cubic;

import android.util.Log;

import java.lang.ref.PhantomReference;
import java.util.Random;

/**
 * Created by DebbieWong on 24/2/2019.
 */

public class GameManager {

    private static final int MAXBASESNUM = 3;
    private static final int MAXGRIDSNUM = 9;
    private static final int MAXCUBESNUM = MAXBASESNUM * MAXGRIDSNUM;
    private static final int MAXHEIGHTNUM = 3;

    private static final int MAXQUESTBANKSNUM = 10;
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

    public GameManager(){

        randomQBankNum = new Random().nextInt(MAXQUESTBANKSNUM);
        Log.d("ddfds", String.valueOf(randomQBankNum));
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

    public void nextQ(){

        randomQBankNum = new Random().nextInt(MAXQUESTBANKSNUM);
        Log.d("ddfdsssss", String.valueOf(randomQBankNum));
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

        currQNum++;
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
