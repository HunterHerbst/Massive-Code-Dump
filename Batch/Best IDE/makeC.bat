@echo off
( echo #include ^<stdio.h^> && echo.&& echo int main^(void^)&& echo {&& echo  puts^(^"Hello world^"^)^;&& echo  return 0^;&& echo }) > helloworld.c
gcc helloworld.c -o "Hello World"
"Hello World.exe"