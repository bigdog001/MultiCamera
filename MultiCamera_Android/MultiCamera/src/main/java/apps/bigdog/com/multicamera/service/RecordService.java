package apps.bigdog.com.multicamera.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.graphics.PixelFormat;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.IBinder;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.IOException;

import apps.bigdog.com.multicamera.util.LogUtil;
public class RecordService extends Service {
    private IBinder mBinder = new LocalBinder();

    private MediaRecorder mediarecorder;// 录制视频的类
    private long recordTime;
    private Camera mCamera;
    private SurfaceHolder surfaceHolder;
    WindowManager wm;
    LayoutParams params;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return mBinder;
    }

    CameraPreview cameraPreview;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
                /*
                 * 如果设置为params.type = WindowManager.LayoutParams.TYPE_PHONE; 那么优先级会降低一些,
                 * 即拉下通知栏不可见
                 */
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明

                /*
                 * 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
                 */
        // params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        // | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        // params.gravity = Gravity.LEFT | Gravity.TOP;
        // // 以屏幕左上角为原点，设置x、y初始值
        // params.x = 0;
        // params.y = 0;
        params.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                                  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        ;

        // 设置悬浮窗的长得宽
        params.width = 1;
        params.height = 1;
        cameraPreview = new CameraPreview(this);
        mFrameLayout = new FrameLayout(this);
        Button button = new Button(this);
        mFrameLayout.addView(cameraPreview);
        mFrameLayout.addView(button);
        mFrameLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(RecordService.this, "你们好啊", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        wm.addView(mFrameLayout, params);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.vegetables_source.alarm");
        registerReceiver(alarmReceiver, filter);
    }

    FrameLayout mFrameLayout;

    SurfaceView surfaceview;

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
    }

    // /**
    // * Class for clients to access. Because we know this service always runs
    // in
    // * the same process as its clients, we don't need to deal with IPC.
    // */
    public class LocalBinder extends Binder {
        public RecordService getService() {
            return RecordService.this;
        }
    }

    public void startRecord() {
        mediarecorder = new MediaRecorder();// 创建mediarecorder对象
        mCamera = getCameraInstance();
        if (mCamera != null) {

            // 解锁camera
            mCamera.unlock();
            mediarecorder.setCamera(mCamera);

            // 设置录制视频源为Camera(相机)
            mediarecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
            mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

            // 设置录制文件质量，格式，分辨率之类，这个全部包括了
            mediarecorder.setProfile(CamcorderProfile
                    .get(CamcorderProfile.QUALITY_LOW));
            mediarecorder.setPreviewDisplay(surfaceHolder.getSurface());
            // 设置视频文件输出的路径
            mediarecorder.setOutputFile("/sdcard/sForm.3gp");
            try {
                // 准备录制
                mediarecorder.prepare();
                // 开始录制
                mediarecorder.start();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        stopRecord();
    }

    /**
     * 停止录制
     */
    public void stopRecord() {
        if (mediarecorder != null) {
            // 停止录制
            mediarecorder.stop();
            // 释放资源
            mediarecorder.release();
            mediarecorder = null;

            if (mCamera != null) {
                mCamera.release();
                mCamera = null;
            }
        }
    }

    public Camera getCameraInstance() {
        Camera c = null;
        try {
            c = openFacingBackCamera();
        } catch (Exception e) {
            // 打开摄像头错误
            LogUtil.log( "打开摄像头错误");
        }
        return c;
    }

    private Camera openFacingBackCamera() {
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        ;
        for (int camIdx = 0, cameraCount = Camera.getNumberOfCameras(); camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                try {
                    cam = Camera.open(camIdx);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }

        return cam;
    }

    // 预览界面CameraPreview
    @TargetApi(9)
    class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

        public CameraPreview(Context context) {
            super(context);
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // 4.0+
            // auto
        }

        public void surfaceCreated(SurfaceHolder holder) {
            surfaceHolder = holder;
            startRecord();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            surfaceHolder = holder;
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            surfaceHolder = holder;
        }
    }

    BroadcastReceiver alarmReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.vegetables_source.alarm".equals(intent.getAction())) {
                if (wm != null && null != params) {

                    boolean flag = intent.getBooleanExtra("flag", false);
                    if (flag) {
                        params.width = 500;
                        params.height = 500;
                    } else {
                        params.width = 1;
                        params.height = 1;
                    }
                    wm.updateViewLayout(mFrameLayout, params);
                }
            }

        }
    };
}
