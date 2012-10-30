@ECHO OFF
IF NOT "%~f0" == "~f0" GOTO :WinNT
@"jruby.exe" "E:/Documents/Post Uni Work/Websites/StaticWeb/target/compass/bin/compass" %1 %2 %3 %4 %5 %6 %7 %8 %9
GOTO :EOF
:WinNT
@"jruby.exe" "%~dpn0" %*
