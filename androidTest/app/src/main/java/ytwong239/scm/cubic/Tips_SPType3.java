package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 27/3/2019.
 */

public class Tips_SPType3 {
    private static final int MAXQUESTBANKSPTYPE3SUM = 2;
    private String tipsSPType3[] = new String[MAXQUESTBANKSPTYPE3SUM];
    private static int currQuestNum = 0;

    public Tips_SPType3(){
        tipsSPType3[0] = "Rotate 90° clockwise";
        tipsSPType3[1] = "Rotate 180° anticlockwise";
    }

    public static void setCurrQuestNum(int val){
        currQuestNum = val;
    }

    public String getString(){
        return tipsSPType3[currQuestNum];
    }

}
