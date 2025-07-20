@echo off
setlocal enabledelayedexpansion

:: === Step 1: Compile with Maven ===
echo 🔧 Compiling with Maven...
call mvn clean compile
if errorlevel 1 (
    echo ❌ Maven compilation failed.
    exit /b 1
)

:: === Step 2: Build classpath ===
:: Update the path to your JSON JAR as needed
set "USERPROFILE=%USERPROFILE%"
set "JSON_JAR=%USERPROFILE%\.m2\repository\org\json\json\20240303\json-20240303.jar"

if not exist "%JSON_JAR%" (
    echo ❌ JSON JAR not found: %JSON_JAR%
    exit /b 1
)

set "CP=target\classes;%JSON_JAR%"

:: === Step 3: Run your main class ===
set "MAIN_CLASS=org.major.Main"

echo 🚀 Running %MAIN_CLASS%...
java -cp "%CP%" %MAIN_CLASS%

endlocal
