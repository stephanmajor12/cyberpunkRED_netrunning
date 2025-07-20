setlocal

REM === Step 1: Compile the project ===
echo Compiling project with Maven...
mvn clean compile
if errorlevel 1 (
    echo Maven compile failed. Aborting.
    exit /b 1
)

REM === Step 2: Set up classpath ===
REM Replace YOUR_USERNAME with your actual Windows username
set JSON_JAR=%USERPROFILE%\.m2\repository\org\json\json\20240303\json-20240303.jar
set CP=target\classes;%JSON_JAR%

REM === Step 3: Run the Main class ===
REM Replace 'your.package.Main' with the full package + class name
java -cp "%CP%" your.package.Main

endlocal
pause
