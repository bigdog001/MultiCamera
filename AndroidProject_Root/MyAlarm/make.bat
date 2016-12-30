@echo off
doskey ls=dir $*
set jarname=TGuardSDKLib_1.0.30_RELEASE.jar
set MYHOME=C:\Users\jw362j\a\work\code\p\multicamera\MultiCamera\AndroidProject_Root\MyAlarm


if  "%1" == "" goto mymake
if  %1 == clean goto myclean
if  %1 == make goto mymake
if  %1 == install goto myinstall



rem clean the project and take the build preparation
:myclean
echo myclean
gradle clean
goto end

rem make and build the jar for sdk lib project
:mymake
echo mymake
gradle build
goto end


rem install the new builded jar into the destination directory
:myinstall
echo "uninstall com.example.myapp"
adb uninstall com.example.myapp
echo "install %MYHOME%\TGuardSDKLibTestSampleApp\build\outputs\apk\TGuardSDKLibTestSampleApp-debug.apk"
adb install   %MYHOME%\TGuardSDKLibTestSampleApp\build\outputs\apk\TGuardSDKLibTestSampleApp-debug.apk
goto end

:myrun
echo "running: com.example.myapp"
adb shell am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n com.example.myapp/com.example.myapp.MyActivity
goto end


rem end of the build script
:end
echo script finished....