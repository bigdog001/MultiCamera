@echo off
doskey ls=dir $*
set MYHOME=C:\Users\jw362j\a\work\code\p\gcm


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
echo "uninstall com.tool.bluetoothlauncher"
adb uninstall com.tool.bluetoothlauncher
echo "install %MYHOME%\BluetoothLauncher\build\outputs\apk\BluetoothLauncher-debug.apk"
adb install   %MYHOME%\BluetoothLauncher\build\outputs\apk\BluetoothLauncher-debug.apk
goto end

:myrun
echo "running: com.tool.bluetoothlauncher"
adb shell am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n com.tool.bluetoothlauncher/com.tool.bluetoothlauncher.MyActivity
goto end

:mylog
adb pull /sdcard/tguard/log_sdk_lib.txt  ./log_sdk_lib.txt
goto end

rem end of the build script
:end
echo script finished....