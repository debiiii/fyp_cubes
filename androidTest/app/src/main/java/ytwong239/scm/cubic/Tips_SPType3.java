package ytwong239.scm.cubic;

/**
 * Created by DebbieWong on 27/3/2019.
 */

public class Tips_SPType3 {

    private static final int MAXQUESTBANKSPTYPE3SUM = 6;
    private String tipsSPType3[] = new String[MAXQUESTBANKSPTYPE3SUM];
    private static int currQuestNum = 0;

    public Tips_SPType3(){
        tipsSPType3[0] = "Rotate 90° clockwise";
        tipsSPType3[1] = "Rotate 180° anticlockwise";
        tipsSPType3[2] = "Rotate 180° clockwise";
        tipsSPType3[3] = "Rotate 90° anticlockwise";
        tipsSPType3[4] = "Rotate 90° anticlockwise";
        tipsSPType3[5] = "Rotate 90° clockwise";
    }

    public static void setCurrQuestNum(int val){
        currQuestNum = val;
    }

    public String getString(){
        return tipsSPType3[currQuestNum];
    }

}
