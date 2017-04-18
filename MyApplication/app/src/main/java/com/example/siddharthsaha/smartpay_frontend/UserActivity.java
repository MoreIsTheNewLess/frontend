package com.example.siddharthsaha.smartpay_frontend;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class UserActivity extends AppCompatActivity {

    private NotificationCompat.Builder notification;
    private static final int uniqueID = 30912;
    private String username, emailid, fullname;
    private DataBaseAssistant assist;
    private String testing;
    private String[] categories, product, deal;
    private String m_Text;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        assist = new DataBaseAssistant(this);
        m_Text = "";
        this.username = getIntent().getStringExtra("Username");
        emailid = assist.getEmail(username);
        fullname = assist.getFullName(username);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Have a question?(soon to come)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat ddformat = new SimpleDateFormat("dd");
        SimpleDateFormat mmformat = new SimpleDateFormat("M");
        String strDate = ddformat.format(calendar.getTime());
        String strMonth = mmformat.format(calendar.getTime());
        String url = new String("http://10.0.0.181:8000/deals");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.add("day", strDate);
        rp.add("month", strMonth);

        client.get(url, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                // Handle resulting parsed JSON response here
                JSONArray reminders = null;
                String fullnotif = "";
                String excitement = "";
                try {
                    reminders = response.getJSONArray("Reminders");
                    String[] notifs=new String[reminders.length()];
                    for(int i=0; i<notifs.length; i++) {
                        notifs[i]=reminders.optString(i);
                    }
                    for(int i = 0; i < notifs.length; i++) {

                        excitement = excitement + "!";
                        if(notifs.length == 1)
                            fullnotif = fullnotif + notifs[i];
                        else if(i == notifs.length - 1)
                            fullnotif = fullnotif + notifs[i];
                        else
                            fullnotif = fullnotif + notifs[i] + ",";
                    }
                    notification.setTicker("This is a ticker");
                    notification.setWhen(System.currentTimeMillis());
                    notification.setSmallIcon(R.drawable.clock);

                    notification.setContentTitle("Reminder" + excitement);
                    notification.setContentText(fullnotif);

                    Intent intent = new Intent(UserActivity.this, UserActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(UserActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    notification.setContentIntent(pendingIntent);

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID, notification.build());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast test = Toast.makeText(UserActivity.this, e.toString(), Toast.LENGTH_SHORT);
                    test.show();
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast error = Toast.makeText(UserActivity.this, "LOL WREKT: " + res, Toast.LENGTH_SHORT);
                error.show();
            }
        });





    }


    public void onPayClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter amount paying");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Toast test = Toast.makeText(UserActivity.this, m_Text, Toast.LENGTH_SHORT);
                test.show();
                String url = "upi://pay?pa=mnl@npci&pn=More%Less&am=" + m_Text + "&cu=INR";
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("in.org.npci.upiapp");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public void onFoodClick(View v) {
//        notification.setTicker("This is a ticker");
//        notification.setWhen(System.currentTimeMillis());
//        notification.setSmallIcon(R.drawable.clock);
//        notification.setContentTitle("Food");
//        notification.setContentText("Don't forget to pay your food bills!");
//
//        Intent intent = new Intent(this, UserActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setContentIntent(pendingIntent);
//
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(uniqueID, notification.build());
//        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);
//        String[] names = new String[]{"Chinmaya", "Kanika", "Rohit", "Siddharth", "Sireesh"};
//        String[] deals = new String[]{"Genius", "Genius", "Genius", "Loafer", "Genius"};
//        String[] address = new String[]{"DD", "DD", "DD", "DD", "DD"};
//        RecycleViewAdapter adapter = new RecycleViewAdapter(names, deals, address);
//        rv.setAdapter(adapter);
//
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        rv.setLayoutManager(llm);
        double lat = 12.9593;
        double longt = 77.6455;
        double ulat = 12.9590;
        double ulongt = 77.6417;
        EditText latFill = (EditText) findViewById(R.id.latFill);
        EditText longtFill = (EditText) findViewById(R.id.longTFill);
        String latstr = latFill.getText().toString();
        String longstr = longtFill.getText().toString();
        Location locationA = new Location("Diamond_District");
        ulat = Double.parseDouble(latstr);
        ulongt = Double.parseDouble(longstr);
        locationA.setLatitude(lat);
        locationA.setLongitude(longt);
        Location locationB = new Location("User_Location");
        locationB.setLatitude(ulat);
        locationB.setLongitude(ulongt);
        double distance = locationB.distanceTo(locationA);
        if(distance <= 1250) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat ddformat = new SimpleDateFormat("dd");
            SimpleDateFormat mmformat = new SimpleDateFormat("M");
            String strDate = ddformat.format(calendar.getTime());
            String strMonth = mmformat.format(calendar.getTime());

            String url = new String("http://10.0.0.181:8000/deals");
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams rp = new RequestParams();
            rp.add("day", strDate);
            rp.add("month", strMonth);
            client.get(url, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                    // Handle resulting parsed JSON response here
                    testing = "";
                    try {
                        JSONArray bestdeals = response.getJSONArray("Best Deals");
                        categories = new String[bestdeals.length()];
                        product = new String[bestdeals.length()];
                        deal = new String[bestdeals.length()];
                        for(int i=0; i<bestdeals.length(); i++) {
                            JSONObject productinfo = bestdeals.getJSONObject(i);
                            categories[i] = productinfo.getString("category");
                            product[i] = productinfo.getString("product");
                            deal[i] = productinfo.getString("offer");
                            testing = testing + categories[i] + " " + deal[i] + " " + product[i] + "\n";
                        }

                        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
                        String[] names = product;
                        String[] deals = deal;
                        String[] category = categories;
                        RecycleViewAdapter adapter = new RecycleViewAdapter(names, deals, category);
                        rv.setAdapter(adapter);

                        LinearLayoutManager llm = new LinearLayoutManager(UserActivity.this);
                        rv.setLayoutManager(llm);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast test = Toast.makeText(UserActivity.this, e.toString(), Toast.LENGTH_SHORT);
                        test.show();
                    }
//                    String[] notifs = temp.toArray(new String[0]);
//
//                    for (int i = 0; i < reminders.length(); i++) {
//                        testing = testing + notifs[i] + "\n";
//                    }
//                    Toast test = Toast.makeText(UserActivity.this, testing, Toast.LENGTH_SHORT);
//                    test.show();
                }


                    @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Toast error = Toast.makeText(UserActivity.this, "LOL WREKT: " + res, Toast.LENGTH_SHORT);
                    error.show();
                }
            });

        }
//        Address ads = list.get(0);
//        double lat = ads.getLatitude();
//        double longt = ads.getLongitude();
//        Toast test = Toast.makeText(UserActivity.this, "Lat: " + lat + "\n" + "Long: " + longt, Toast.LENGTH_SHORT);
//        test.show();
//        String accessname = "rohit.rk.rk1@gmail.com";
//        String accesspass = "3SE1R5P8C";
//        String url = new String("http://10.0.0.181:8000");
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams rp = new RequestParams();
//        rp.add("username", accessname);
//        rp.add("password", accesspass);
//        client.get(url, rp, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Toast test = Toast.makeText(UserActivity.this, "Failure: " + statusCode + "\n" + responseString, Toast.LENGTH_SHORT);
//                test.show();
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                Toast test = Toast.makeText(UserActivity.this, "Success: " + statusCode + "\n" + responseString, Toast.LENGTH_SHORT);
//                test.show();
//            }
//        });

//        JsonHttpResponseHandler handler = new JsonHttpResponseHandler();
//        RequestParams rp = new RequestParams();
//        rp.add("username", "rohit.rk.rk1@gmail.com");
//        rp.add("password", "3SER5P8C");
//        HTTPUtility.get("http://10.0.0.181:8000", rp, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statuscode, Header[] headers, JSONObject response) {
//                Log.d("Response", "-------this is response: " + response);
//                Toast test = Toast.makeText(UserActivity.this, "Success: " + response.toString(), Toast.LENGTH_SHORT);
//                test.show();
//                try {
//                    JSONObject serverResponse = new JSONObject(response.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast error = Toast.makeText(UserActivity.this, e.toString(), Toast.LENGTH_SHORT);
//                    error.show();
//                }
//            }
//
//            @Override
//            public void onSuccess(int statuscode, Header[] headers, JSONArray timeline) {
//                String resp = "";
//                for(int i = 0; i < timeline.length(); i++) {
//                    try {
//                        resp = resp + timeline.getString(i);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast error = Toast.makeText(UserActivity.this, e.toString(), Toast.LENGTH_SHORT);
//                        error.show();
//                    }
//                }
//                Log.d("Response", "-------this is all the responses: " + resp);
//                Toast test = Toast.makeText(UserActivity.this, "success array" + timeline.toString(), Toast.LENGTH_SHORT);
//                test.show();
//            }
//
//            @Override
//            public void onFailure(int statuscode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                Log.d("Response", "-------this is response: " + errorResponse);
//                Toast test = Toast.makeText(UserActivity.this, "Success: " + errorResponse.toString(), Toast.LENGTH_SHORT);
//                test.show();
//                try {
//                    JSONObject serverResponse = new JSONObject(errorResponse.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast error = Toast.makeText(UserActivity.this, e.toString(), Toast.LENGTH_SHORT);
//                    error.show();
//                }
//            }
//
//            @Override
//            public void onFailure(int statuscode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                String resp = "";
//                for(int i = 0; i < errorResponse.length(); i++) {
//                    try {
//                        resp = resp + errorResponse.getString(i);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast error = Toast.makeText(UserActivity.this, e.toString(), Toast.LENGTH_SHORT);
//                        error.show();
//                    }
//                }
//                Log.d("Response", "-------this is all the responses: " + resp);
//                Toast test = Toast.makeText(UserActivity.this, "success array" + errorResponse.toString(), Toast.LENGTH_SHORT);
//                test.show();
//            }
//        });

    }

    public void onSuggestClick(View v) {
//        notification.setTicker("This is a ticker");
//        notification.setWhen(System.currentTimeMillis());
//        notification.setSmallIcon(R.drawable.clock);
//        notification.setContentTitle("Life Skill");
//        notification.setContentText("Wash your hands before you eat!");
//        notification.setContentText("Wash your hands before you eat!");
//
//        Intent intent = new Intent(this, UserActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setContentIntent(pendingIntent);
//
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(uniqueID, notification.build());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
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
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.usertab1accinfo, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    UserInfoTab tab1 = new UserInfoTab();
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME_KEY", username);
                    bundle.putString("EMAIL_KEY", emailid);
                    bundle.putString("FULLNAME_KEY", fullname);
                    tab1.setArguments(bundle);
                    return tab1;
                case 1:
                    SuggestionsTab tab2 = new SuggestionsTab();
                    return tab2;
                case 2:
                    RemindersTab tab3 = new RemindersTab();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ACCOUNT";
                case 1:
                    return "SUGGESTIONS";
                case 2:
                    return "LOCATION";
            }
            return null;
        }

    }
}


