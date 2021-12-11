<?xml version="1.0" encoding="UTF-8"?>
<configuration>

	<properties>  
        <property name="LOG_HOME">../../logs/finance</property>
    </properties> 
    
	<appenders>
	    <Console name="Console" target="SYSTEM_OUT" follow="true">  
            <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />  
        </Console> 
        		
		<RollingFile name="LogFileAppender" fileName="${r"${LOG_HOME}"}/admin.log" filePattern="${r"${LOG_HOME}"}/admin-%d{yyyy-MM-dd}-%i.log.gz">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss z} %-5level %class{36} %L %M - %msg%xEx%n"/>
            <SizeBasedTriggeringPolicy size="10M"/>
            <Filters>
                <ThresholdFilter level="error" onMatch="DENY" onMismatch="NEUTRAL"/>
                <ThresholdFilter level="trace" onMatch="ACCEPT" onMismatch="DENY"/>
              </Filters>
        </RollingFile>
        
         <RollingFile name="ErrorLogFileAppender" fileName="${r"${LOG_HOME}"}/admin-error.log" filePattern="${r"${LOG_HOME}"}/admin-error-%d{yyyy-MM-dd}-%i.log.gz">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss z} %-5level %class{36} %L %M - %msg%xEx%n"/>
            <SizeBasedTriggeringPolicy size="10M"/>
            <Filters>
                <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
            </Filters>
        </RollingFile> 
        
	</appenders>

	<loggers>
		<logger name="com.yqjp" level="TRACE"></logger>
		<logger name="org.apache.kafka" level="DEBUG"></logger>
		<logger name="com.ibatis" level="DEBUG"></logger>
		<logger name="com.yqjp.assetsCenter.finance.dao.mapper" level="DEBUG"></logger>
		<logger name="kafka" level="DEBUG"></logger>
		<root level="info">
		    <appender-ref ref="Console" />
			 <appender-ref ref="LogFileAppender" />
			<appender-ref ref="ErrorLogFileAppender" /> 
		</root>
	</loggers>

</configuration>