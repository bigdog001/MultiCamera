@echo off
doskey ls=dir $*
set jarname=MyToolLib_1.0.0_RELEASE.jar
set MYHOME=C:\Users\jw362j\a\work\code\p\multicamera\MultiCamera\AndroidProject_Root


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
gradle makeJar
goto end

rem install the new builded jar into the destination directory
:myinstall
echo myinstall
echo "deleteing... %MYHOME%\MultiCamera\libs\%jarname%"
del %MYHOME%\MultiCamera\libs\%jarname%
echo "deleteing... %MYHOME%\MyAlarm\libs\%jarname%"
del %MYHOME%\MyAlarm\libs\%jarname%
echo "copy %MYHOME%\MyToolLib\build\outputs\%jarname% into %MYHOME%\MultiCamera\libs\%jarname%"
copy %MYHOME%\MyToolLib\build\outputs\%jarname% %MYHOME%\MultiCamera\libs\%jarname%
echo "copy %MYHOME%\MyToolLib\build\outputs\%jarname% into %MYHOME%\MyAlarm\libs\%jarname%"
copy %MYHOME%\MyToolLib\build\outputs\%jarname% %MYHOME%\MyAlarm\libs\%jarname%

goto end


rem end of the build script
:end
echo script finished....