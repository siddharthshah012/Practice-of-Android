package examples.csci567.eventreceiver;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {

    private IntentFilter filter =new IntentFilter(Intent.ACTION_TIME_TICK);

    int notification_id= 1; // This is the notification ID
    int messages_count=1; // This count is used to keep a check on the minutes passed.

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            openNotification(); // On receiving the intent this will call the function to show notification.
            messages_count++; // counter for minutes.

            Toast.makeText(getBaseContext(),"One Minute over ",
                    Toast.LENGTH_LONG)
            .show();
            // for display of time


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void openNotification()
    {
        this.notificationOperation(notification_id, messages_count );
        // this function calls the notification function.

    }



    public void notificationOperation( int id, int count){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("New Notification.....");
        builder.setContentText("It has been " + count + " minutes from the time app to started." );
        NotificationManager nm = (NotificationManager)getSystemService(getBaseContext().NOTIFICATION_SERVICE);
        nm.notify(id,builder.build());
        // This is the notification function.

    }

    @Override
    protected void onResume(){

        this.registerReceiver(receiver , filter);
        super.onResume();

    }

    @Override
    protected void onPause(){

        this.unregisterReceiver(receiver);
        super.onPause();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
