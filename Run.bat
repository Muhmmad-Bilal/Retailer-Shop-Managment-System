@echo off

set classpath=.;build\classes\
set classpath=%classpath%;driver\AbsoluteLayout.jar
set classpath=%classpath%;driver\ucanaccess-4.0.4.jar;
set classpath=%classpath%;driver\commons-lang-2.6.jar;
set classpath=%classpath%;driver\commons-logging-1.1.3.jar;
set classpath=%classpath%;driver\hsqldb.jar;
set classpath=%classpath%;driver\jackcess-2.1.11.jar;
set classpath=%classpath%;driver\ConnectCodeBarcodeFontLibrary.jar;
set classpath=%classpath%;driver\jcalendar-1.4.jar;
set classpath=%classpath%;driver\JTattoo-1.6.11.jar;
set classpath=%classpath%;driver\qrgen.jar;
set classpath=%classpath%;driver\zxing-1.7-javase.jar;
set classpath=%classpath%;driver\zxing-2.1.jar;
set classpath=%classpath%;driver\zxing-core-2.0.jar;
set classpath=%classpath%;driver\swingx-all-1.6.4.jar;
set classpath=%classpath%;driver\webcam-capture-0.3.10.jar;
set classpath=%classpath%;driver\libs\bridj-0.6.2.jar;
set classpath=%classpath%;driver\libs\slf4j-api-1.7.2.jar;
set classpath=%classpath%;driver\libs\webcam-capture-0.3.10.jar;

java FronterFrame.RetailerShopManagmentMainJFrame

pause