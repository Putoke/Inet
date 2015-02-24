#!/bin/csh -f
dir=$( cd "$( dirname "$0" )" && pwd )
tomcatpath=$dir/../lib
webapppath=$dir/../webapps/ROOT/WEB-INF/classes
find $webapppath -name "*.java" > sources.txt
javac -cp $tomcatpath/servlet-api.jar @sources.txt