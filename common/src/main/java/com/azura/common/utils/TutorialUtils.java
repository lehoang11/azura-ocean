package com.azura.common.utils;

public class TutorialUtils {

    public  static Long generalCode(Long eduId){

        String tutorialCodeStr;
        Long tutorialCode;
        long timeSecond = System.currentTimeMillis()/1000;
        tutorialCodeStr = String.valueOf(eduId) + timeSecond;
        tutorialCode = Long.valueOf(tutorialCodeStr);
        return tutorialCode;

    }
}
