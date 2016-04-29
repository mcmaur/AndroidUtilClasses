# AndroidUtilClasses

*GoogleAPIs
*LastLocation

To receive the last location of the device

INSTRUCTION

1. permission on the manifest file
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>

2. Add the play services library in the manifest like this
<meta-data android:name="com.google.android.gms.version" android:value="@integer/google_play_services_version" />

3. Add the lib to build gradle 
compile 'com.google.android.gms:play-services:8.4.0'

4. Include the two java file in your app

5. Modify them as you need

DONE!