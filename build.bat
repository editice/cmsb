@echo off

setlocal
	
	mvn package -Dmaven.test.skip=true

	echo Remove old war
	set war=web\target

	if exist %war%(
		rmdir /s /q %war%
	)

	echo Package war
	call mvn clean
	set MAVEN_OPTS=-Xmx2048m
	call mvn -U -Dmaven.test.skip=true package

	if errorlevel 1 exit /b
	
	exit /b

endlocal