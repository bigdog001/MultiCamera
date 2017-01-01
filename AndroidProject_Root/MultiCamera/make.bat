@echo off
doskey ls=dir $*
set jarname=TGuardSDKLib_1.0.30_RELEASE.jar
set MYHOME=C:\Users\jw362j\a\work\code\p\multicamera\MultiCamera\AndroidProject_Root\MultiCamera


if  "%1" == "" goto mymake
if  %1 == clean goto myclean
if  %1 == make goto mymake
if  %1 == install goto myinstall
if  %1 == run goto myrun
if  %1 == log goto mylog



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
echo "uninstall apps.bigdog.com.multicamera.phone"
adb uninstall apps.bigdog.com.multicamera.phone
echo "install %MYHOME%\build\outputs\apk\MultiCamera-phone-debug.apk"
adb install   %MYHOME%\build\outputs\apk\MultiCamera-phone-debug.apk
goto end

:myrun
echo "running: apps.bigdog.com.multicamera.phone"
adb shell am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n apps.bigdog.com.multicamera.phone/apps.bigdog.com.multicamera.activity.MainActivity
goto end

:mylog
adb pull /sdcard/mylog/my_log.txt ./
goto end


rem end of the build script
:end
echo script finished....