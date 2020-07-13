@echo off
set TAB=    
set FILE_NAME=HowToJava.java
set EXEC_NAME=HowToJava
echo public class HowToJava {> %FILE_NAME%
echo %TAB%public static void main^(String^[^] args^) {>> %FILE_NAME%
echo %TAB%%TAB%System.out.println^("Don't use CMD"^)^;>> %FILE_NAME%
echo %TAB%}>> %FILE_NAME%
echo } >> %FILE_NAME%

javac %FILE_NAME%
java %EXEC_NAME%