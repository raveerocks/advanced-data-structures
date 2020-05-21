package io.raveerocks.github.application.test;

public class Logger {

    public static void printSuccessLog(String successMessge){
        System.out.println(successMessge);
    }

    public static void printFailureLog(String failureMessge){
        System.out.println(failureMessge);
    }

    public static void printFailureLog(String messge, Object correctResults, Object actualResults){
        System.out.println(messge+". Expect value is"+correctResults+" but received "+actualResults);
    }

}
