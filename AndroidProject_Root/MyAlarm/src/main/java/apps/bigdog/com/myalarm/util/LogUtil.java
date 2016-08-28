package apps.bigdog.com.myalarm.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import apps.bigdog.com.myalarm.app.LocalApplication;

/**
 * Created by jw362j on 6/1/2016.
 */
public class LogUtil {
    private static int log_flag = -100;
    private static boolean runtime_flag = false;
    private static final String log_tag_runtime = "d33600715c5e79c155e268fbf99556f4.txt";

    public static void log(String tag,String msg){

        if (log_flag == -100) {
            boolean isCardExist = JFileKit.sdcardIsReadyForWrite();// Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED) ? true : false;
            if (isCardExist) {
                String sdFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
                String fileName = sdFilePath + log_tag_runtime;
                File f = new File(fileName);
                if (f != null && f.exists()) {
                    runtime_flag = true;
                }else {
                    return;
                }
            } else {
                Log.d(tag,"you do not have sd card");
            }
            log_flag = 1;
        }
        if (runtime_flag && msg != null) {
            msg =new Date()  + ":" + msg;
            Log.d(tag, msg);
            logOnFile(tag,msg+"\n");
        }


    }
    public static void log(String logContent){
        log("mylog", logContent);
    }

    private static void logOnFile(String tag,String string) {


        boolean isCardExist = JFileKit.sdcardIsReadyForWrite();// Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED) ? true : false;

        if (isCardExist) {
            String sdFilePath = JFileKit.getDiskCacheDir(LocalApplication.getInstance()) + File.separator+"log"+File.separator;
//            String sdFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "mylog" + File.separator;
            try {
                String fileName = sdFilePath + "log_myalarm" + ".txt";
                File filePath = new File(sdFilePath);
                if (!filePath.exists()) {
                    filePath.mkdir();
                }
                File file = new File(fileName);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.length() > 200 * 1000) {
                    try {
                        file.delete();
                        file.createNewFile();
                    } catch (IOException e) {
                        Log.d(tag,e.getMessage());
                    }
                }

                FileOutputStream fos;
                fos = new FileOutputStream(file, true);
                fos.write(string.getBytes());
                fos.close();
            } catch (Exception e) {
                Log.d(tag,e.getMessage());
            }
        } else {
            Log.d(tag,"you do not have sd card");
        }


    }
}
