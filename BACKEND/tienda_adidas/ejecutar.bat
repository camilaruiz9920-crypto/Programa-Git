@echo off
TITLE Sistema Tienda Adidas - Backend
REM Verifica si la carpeta bin existe, si no la crea
if not exist bin mkdir bin

echo Compilando el proyecto...
javac -d bin -sourcepath src src/com/adidas/tienda/Main.java

if %errorlevel% neq 0 (
    echo Error en la compilacion. Verifique su JDK.
    pause
    exit /b %errorlevel%
)

echo Ejecutando el sistema...
java -cp "bin;lib/mysql-connector.jar" com.adidas.tienda.Main
pause

