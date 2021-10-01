call runcrud.bat
if "%ERRORLEVEL%" == "0" goto starttomcat
echo.
echo runcrud.bat has errors – breaking work
goto fail

:startomcat
start www.google.pl
if "%ERRORLEVEL%" == "0" goto runtomcat
echo Cannot copy file
goto fail

:runtomcat
start http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished