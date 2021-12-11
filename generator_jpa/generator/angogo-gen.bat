@echo 1. yqjp  generator.xml,project  database config
@echo 2. template generator fire

@set classpath=%classpath%;.;.\lib\*;.\lib\yqjp-generator.jar;.\lib\freemarker.jar;.\lib\h2-1.2.137.jar;.\lib\log4j-1.2.15.jar;.\lib\mysql-connector-java-5.0.5-bin.jar;.\lib\ojdbc14.jar;.\lib\postgresql-8.4-701.jdbc3.jar;.\lib\sqljdbc.jar

@set PATH=%JAVA_HOME%\bin;%PATH%;
@java -server -Xms128m -Xmx384m com.yqjp.framework.generator.ext.CommandLine -DtemplateRootDir=template
@if errorlevel 1 (
@echo ----------------------------------------------
@echo   *************please config java home env********************classpath
@pause
)

:end