cd deploy/

@echo off
REM Closes any running tests
REM Removes any previous installations
REM Installs the created APK
REM Clears the Android log buffer
REM Starts the log window in another console
REM    Tag 'TRACE' shows all logs
REM    Tag '*' only shows ERROR logs.
REM Starts the application on the device using the chosen 'Activity'
@echo on

adb shell am force-stop examples.glsurfaceview
adb uninstall examples.glsurfaceview
adb install bin/GLSurfaceViewExample-debug.apk
adb logcat -c
start adb logcat -b main -v raw TRACE:V *:E
adb shell am start -n examples.glsurfaceview/examples.glsurfaceview.Main					  
pause