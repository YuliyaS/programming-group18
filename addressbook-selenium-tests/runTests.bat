set propertiesFile=firefox.properties
set configFile=testng-customsuite.xml

set lib1=c:\Tools\xstream-1.4.7\lib\xstream-1.4.7.jar
set lib2=c:\Tools\xstream-1.4.7\lib\xstream\xpp3_min-1.1.4c.jar
set lib3=c:\Tools\xstream-1.4.7\lib\xstream\xmlpull-1.1.3.1.jar
set libSelenium=c:\Tools\selenium-2.41.0\selenium-server-standalone-2.41.0.jar

java -cp bin;%lib1%;%lib2%;%lib3%;%libSelenium% -DconfigFile=%propertiesFile% org.testng.TestNG %configFile%