![](http://i.imgur.com/hvGvNK4.png)
# Haxe Android Project

### Installation


* Eclipse isn't needed.
* Install **[FlashDevelop](http://www.flashdevelop.org/community/viewforum.php?f=11) (not obligatory)** 
* Download and install the latest **[Apache ANT](http://ant.apache.org/bindownload.cgi)**
* Download and install the latest **[Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html)**
* Follow the instructions to install **[Android SDK](https://developer.android.com/sdk/installing/index.html)**
    * It will install libraries for different android versions **(these downloads and installations will take some time)**
* Make sure your `JAVA_HOME` environment variable points to your JDK programs like [java,javac,...] because ANT searches in it.
* Run `haxelib install androidhx` if you want to use android externs classes
    * Also using `-java-lib PATH_TO_ANDROID/platform/android-X/android.jar` in the `Project Properties` works too.

![prop.jpg](https://bitbucket.org/repo/Eekr6L/images/140961547-prop.jpg) 

### Development

* The installations should make all necessary tools available.
* Make sure that android tools like `android` and `adb` are visible from the command line.
* Run `CreateProject.bat` to create the project base and APK packaging data like `AndroidManifest.xml` **(it was already done in this template)**
* Read the **[Android Documentation](http://developer.android.com/tools/projects/index.html)** about packaging and what Resources and Manifests are.
* Check `AndroidManifest.xml` to change where your Main class will point.
 
### Build
 
* With the project base created just press **F8** or **Project Build* in **FlashDevelop**.
    * `Build.bat` runs `ant debug` in the backstage.
* Check `deploy/bin` folder and see the `.apk` there.
    * Check information about `ant release` for an "official build".
 
### Run 
 
* The build process **just creates the APK**.
* To test it in your device make sure to **connect it on your USB port and setup it to USB Debugging**
* Instructions about it here [http://developer.android.com/sdk/win-usb.html] and here [http://developer.android.com/tools/device.html]
* Press **F5** or **Test Project** on FlashDevelop.
 
### Caveats

* Check the BAT files to see what happens during `Build` and `Run`.
* Check the `Project Properties` for extra flags and commands used in this template.
* If you change class names and other resources stuff. Double check `AndroidManifest.xml` and the BAT files.
* The **R** resources class will follow the `package` attribute in the `<manifest>` tag in the `AndroidManifest.xml` file.
