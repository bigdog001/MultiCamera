@echo off
doskey ls=dir $*
set MYHOME=C:\Users\jw362j\a\work\code\p\multicamera\MultiCamera\AndroidProject_Root\
if  "%1" == "" goto mymake
if  %1 == clean goto myclean
if  %1 == run goto myrun



rem clean the project and take the build preparation
:myclean
gradle clean
goto end


:mymake
gradle build
goto end

:myrun
gradle -q run
goto end

rem end of the build script
:end
echo finished....