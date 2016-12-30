# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Program_Files\adt-bundle-windows-x86_64-20140321\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-printmapping
-keeppackagenames

-keep public class * extends android.support.v4.content.LocalBroadcastManager

-keep public class * extends android.app.Activity

-keep public class * extends android.app.Application

-keep public class * extends android.app.Service

-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.app.backup.BackupAgentHelper

-keep public class * extends android.preference.Preference

-keep public class * extends android.widget.Button

-keep public class * extends android.view.View

-keep public class * extends android.view.SurfaceView

-keep public class com.android.vending.licensing.ILicensingService



-keep class com.tool.mytool.lib.util.MyUtil {
    <fields>;
    <methods>;
}

-keepclasseswithmembers public class  com.tool.mytool.lib.util.MyUtil {
     <fields>;
     <methods>;
 }


# Keep - Applications. Keep all application classes, along with their 'main'
# methods.
-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}

# Also keep - Enumerations. Keep the special static methods that are required in
# enumeration classes.
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}