@echo off
doskey ls=dir $*
set MYHOME=C:\Users\jw362j\a\work\code\p\multicamera\MultiCamera\AndroidProject_Root\
if  "%1" == "" goto end
if  %1 == 1 goto one
if  %1 == 2 goto two
if  %1 == 3 goto three
if  %1 == log goto logs



rem clean the project and take the build preparation
:one
cd MultiCamera
goto end

rem make and build the jar for sdk lib project
:two
cd MyAlarm
goto end

rem install the new builded jar into the destination directory
:three
cd MyToolLib
goto end


:logs
del log_sdk_lib.txt
adb pull /sdcard/tguard/log_sdk_lib.txt  ./log_sdk_lib.txt
goto end


rem end of the build script
:end
echo finished....