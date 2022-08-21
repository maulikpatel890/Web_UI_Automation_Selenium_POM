package util;

import org.apache.log4j.Logger;

public class Log {

    //Initialize Log4j instance
    private static Logger Log = Logger.getLogger(Log.class.getName());

    //We can use it when starting tests
    public static void startLog(String classname){
        Log.info("----------------------------------------------------------------");
        Log.info("           Testcase Execution Started For "+ classname           );
        Log.info("----------------------------------------------------------------");
    }

    //We can use it when ending tests
    public static void endLog(){

        Log.info("----------------------------------------------------------------");
        Log.info("                  TestCase Execution Completed                  ");
        Log.info("----------------------------------------------------------------");
    }

    //Info Level Logs
    public static void info (String message) {
        Log.info("-------------------------START INFO---------------------------");
        Log.info(message);
        Log.info("-------------------------END INFO-----------------------------");
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}
