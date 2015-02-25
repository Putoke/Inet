restartc:
	sh tomcat/bin/shutdown.sh
	sh tomcat/bin/compile.sh
	sh tomcat/bin/startup.sh
restart:
	sh tomcat/bin/shutdown.sh
	sh tomcat/bin/startup.sh
start:
	sh tomcat/bin/startup.sh
stop:
	sh tomcat/bin/shutdown.sh
resetDatabase:
	mysql --host=mysql-vt2015.csc.kth.se --user=jedluadmin --password=mel4Q8mc < database/createtables.sql