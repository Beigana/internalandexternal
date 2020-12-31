package com.example.myapp;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button play;
    Button alrt;
    Button Start, Stop;


    private BroadcastReceiver MyReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            //get baterry
            int level = intent.getIntExtra("level", 0);
            //find the progress bar crtd in the main.xml
            ProgressBar pb = findViewById(R.id.progressBar);
            pb.setProgress(level);

            //accessing text view controll crtd in main.xml
            TextView textView = findViewById(R.id.power);
            textView.setText("Baterylevel is at:" + level + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(MyReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        Start = findViewById(R.id.buttonStart);
        Stop = findViewById(R.id.buttonStop);
        Start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MyService.class);
                startActivity(intent);
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });


        play = findViewById(R.id.hit);
        play.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,music.class);
                startActivity(intent);
            }


        });
    }


    //dfng a mtd to send alert
    public void alarm(View view) {
        EditText text = findViewById(R.id.alarm);
        int i = Integer.parseInt(text.getText().toString());
        alrt = findViewById(R.id.btn_alarm);

        //creating the intent and call your receiver
        Intent intent = new Intent(getApplicationContext(), MyReceiver.class);

        //creating a pending intent to be fired when alarm is ready
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, 0);

        //use the alarm manager class to generate an alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //Real time Clock to be used
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), pendingIntent);

        Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();


    }

    public void sendMessage(View view) {
        EditText message = findViewById(R.id.message);
        Toast.makeText(this, "Sending message " + message.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("MESSAGE", message.getText().toString());
        startActivity(intent);

        message.setText("");


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.me, menu);
        return true;
    }
    public boolean onCreateOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.bac:
                ;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(menuItem)));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            case R.id.internal:
                startActivity(new Intent(this, internal.class));
                return true;
            case R.id.external:
                startActivity(new Intent(this, external.class));
                return true;


            case R.id.lst:
                startActivity(new Intent(this, list_view1.class));
                return true;
            case R.id.gmail:
                Intent m = new Intent(Intent.ACTION_SEND);
                m.setData(Uri.parse("mailto"));
                String[] to = {"beinganaham5@gmail.com", "kimrich@gmail.com", "nduhuura@gmail.com"};
                m.putExtra(Intent.EXTRA_EMAIL, to);
                m.putExtra(Intent.EXTRA_SUBJECT, "GREETINGS");
                m.putExtra(Intent.EXTRA_TEXT, "hello its Abraham");
                m.setType("message/rfc822");
                startActivity(m);
                return true;
            case R.id.call:

                try {
                    // check for call permissions
                    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

                    // Here, thisActivity is the current activity
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {

                            // Show an explanation to the user *asynchronously*
                            Toast.makeText(this, "This permission is required to call a phone number", Toast.LENGTH_LONG).show();

                        } else {

                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }
                    Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0780639092"));
                    startActivity(intent2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;


        }
        return super.onOptionsItemSelected(menuItem);

    }


    public BroadcastReceiver getMyReceiver() {
        return MyReceiver;
    }

    public void setMyReceiver(BroadcastReceiver myReceiver) {
        MyReceiver = myReceiver;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onClick(View v) {

    }
}