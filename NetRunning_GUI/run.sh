#!/bin/bash
set -e

# === Step 1: Compile with Maven ===
echo "🔧 Compiling with Maven..."
mvn clean compile

# === Step 2: Build classpath ===
# Adjust this if you have more dependencies
JSON_JAR="$HOME/.m2/repository/org/json/json/20240303/json-20240303.jar"
if [ ! -f "$JSON_JAR" ]; then
  echo "❌ JSON JAR not found: $JSON_JAR"
  exit 1
fi

CP="target/classes:$JSON_JAR"

# === Step 3: Run your main class ===
# Change this to your actual main class (with package)
MAIN_CLASS="org.example.Main"

echo "🚀 Running $MAIN_CLASS..."
java -cp "$CP" "$MAIN_CLASS"
