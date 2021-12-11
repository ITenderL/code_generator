test=finance78437284
mybatis.type-aliases-package=${basepackage}.dao.entity
mybatis.mapper-locations=classpath:mapper/*Mapper.xml
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=5000

yqjp.mybatis.cacheEnabled=false

#mysql global config
db.group.size=1
db.shard.size=1000
db.driverClass=com.mysql.jdbc.Driver
db.initialSize=2
db.minIdle=1
db.maxActive=10
db.maxWait=60000
db.timeBetweenEvictionRunsMillis=60000
db.minEvictableIdleTimeMillis=300000
db.testOnBorrow=false
db.testOnReturn=false

#master
master.db.url=${jdbcurl}
master.db.username=${jdbcusername}
master.db.password=${jdbcpassword}
master.db.initialSize=2
master.db.minIdle=2
master.db.maxActive=20

