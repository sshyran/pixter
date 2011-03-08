package pixter.apk;
// replace with your package name;
// new test

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import pixter.apk.beagleTest;

public class StartupBroadcastRecevie extends BroadcastReceiver {

@Override
public void onReceive(Context context, Intent intent) {
Intent startupIntent = new Intent(context, beagleTest.class); // substitute with your launcher class
startupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
context.startActivity(startupIntent);
}

}
