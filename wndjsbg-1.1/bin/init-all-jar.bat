@echo off
echo [INFO] ��maven�ֿ⸴��jar��������ĿĿ¼.

cd %~dp0
dir
cd ..

call mvn  dependency:copy-dependencies -DoutputDirectory=src\main\webapp\WEB-INF\lib -DincludeScope=runtime
echo -Pexamples  -Dsilent=true

pause
