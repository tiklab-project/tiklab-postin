
@echo off
REM 启动类文件
set APP_MAIN=io.thoughtware.postin.PostInApplication

set PID=0

for /f "usebackq tokens=1-2" %%a in (`jps -l ^| findstr %APP_MAIN%`) do (
set PID=%%a
)

echo ================================================================================================================
if %PID% == 0 (
     echo %APP_MAIN%  is not running
) else (
    taskkill /PID %PID% /F > NUL 2>&1
        if errorlevel 1 (
            echo [Failed] %PID%
        ) else (
            echo [success] %PID%
        )
)
echo ================================================================================================================