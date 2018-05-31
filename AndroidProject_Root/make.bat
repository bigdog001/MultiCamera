@echo off
doskey ls=dir $*
set MYHOME=C:\Users\jw362j\a\z_temp\MultiCamera\MultiCamera\AndroidProject_Root\
if  "%1" == "" goto mymake
if  %1 == l goto logs



rem clean the project and take the build preparation
:mymake
echo "gradle build"
gradle build
goto end

:logs
del log_sdk_lib.txt
adb pull /sdcard/mylog/  .
goto end


rem end of the build script
:end
echo finished....