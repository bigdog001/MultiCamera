@echo off
doskey ls=dir $*
set MYHOME=C:\Users\jw362j\a\z_temp\MultiCamera\MultiCamera\AndroidProject_Root\HealthTV
set MYURL=http://www.duduhanju.net/

if  "%1" == "" goto mymake
if  %1 == clean goto myclean
if  %1 == make goto mymake
if  %1 == install goto myinstall
if  %1 == i goto myinstall
if  %1 == run goto myrun
if  %1 == r goto myrun
if  %1 == log goto mylog
if  %1 == l goto mylog
if  %1 == rm goto myremove
if  %1 == br goto mybrowser
if  %1 == tv goto myTv




:myTv
echo "adb connect 192.168.0.100"
adb connect 192.168.0.100
goto end


:mybrowser
echo "adb shell am start -a android.intent.action.VIEW -d  %MYURL%"
adb shell am start -a android.intent.action.VIEW -d  %MYURL%
goto end


rem clean the project and take the build preparation
:myremove
echo "adb uninstall com.android.sony.tv"
adb uninstall com.android.sony.tv
goto end


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
echo "install -r %MYHOME%\build\outputs\apk\debug\HealthTV-debug.apk"
adb install -r  %MYHOME%\build\outputs\apk\debug\HealthTV-debug.apk
goto end

:myrun
echo "running: com.android.sony.tv"
adb shell am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n com.android.sony.tv/com.android.sony.tv.activity.MainActivity
goto end

:mylog
adb pull /sdcard/mylog/ .
goto end


rem end of the build script
:end
echo script finished....