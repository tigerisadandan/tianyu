@echo off
echo [INFO] ɾ��[\src\main\webapp\WEB-INF\lib]���������jar��.

cd %~dp0
cd ..
:: Send the length of the variable %MyVar%
:: to the variable %length%
@set MyVar=%cd%\src\main\webapp\WEB-INF\lib

dir %MyVar%

del /q /s %MyVar%\*.*

echo [INFO] ɾ���ɹ���

pause
