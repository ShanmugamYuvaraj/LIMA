set drive=C
set projectLocation=%drive%:\LIMATestAutomationProject\LIMATestAutomtion
cd %projectLocation%
java -cp %projectLocation%\lib\*;%projectLocation%\bin org.testng.TestNG %projectLocation%\WFLTesting.xml
pause
