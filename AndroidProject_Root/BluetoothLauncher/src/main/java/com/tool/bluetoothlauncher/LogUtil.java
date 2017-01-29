package com.tool.bluetoothlauncher;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by jw362j on 12/22/2016.
 */
public class LogUtil {

    private static int log_flag = -100;
    private static boolean runtime_flag = false;
    private static String log_tag_runtime = "d33600715c5e79c155e268fbf99556f4.txt";
    private static String logtag = "thelog";
    public static boolean isFlagExist(){
        boolean result = false;
        boolean isCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED) ? true : false;
        if (isCardExist) {
            String sdFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
            String fileName = sdFilePath + log_tag_runtime;
            File f = new File(fileName);
            if (f != null && f.exists()) {
                result = true;
            }else {
                return result;
            }
        }
        return result;
    }
    public static void log(String msg) {
        Log.d(logtag, msg);
        if (log_flag == -100) {
            log_flag = 1;
            if (isFlagExist()) {
                runtime_flag = true;
            }else {
                return;
            }
        }
        if (runtime_flag && msg != null) {
            msg = new Date()+":"  + msg;
            Log.d(logtag, msg);
            logOnFile(msg+"\n");
        }
    }

    private static void logOnFile(String string) {


        boolean isCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED) ? true : false;

        if (isCardExist) {

           /* File mydir = VariableKeeper.mActivity.getDir("tguard", Context.MODE_PRIVATE); //Creating an internal dir;
            File fileWithinMyDir = new File(mydir, "log_sdk_lib.txt");*/

            String sdFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "tguard" + File.separator;
            try {
                String fileName = sdFilePath + "log_sdk_lib" + ".txt";
                File filePath = new File(sdFilePath);
                if (!filePath.exists()) {
                    filePath.mkdir();
                }
                File file = new File(fileName);
//                String data_root_path = VariableKeeper.mActivity.getCacheDir().getAbsolutePath();
//                File file = new File(data_root_path + File.separator+"log_sdk_lib.txt");
//                File file  = new File(VariableKeeper.mActivity.getDir("tguard", Context.MODE_PRIVATE), "log_sdk_lib.txt");
//                Log.d(logtag,"the abstract path of the file is:"+data_root_path);
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
                        e.printStackTrace();
                    }
                }

                FileOutputStream fos;
                fos = new FileOutputStream(file, true);
                fos.write(string.getBytes());
                fos.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d(logtag,e.getMessage());
            }
        } else {
            Log.d(logtag,"you do not have sd card");
        }


    }
}
