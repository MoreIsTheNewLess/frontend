package com.example.siddharthsaha.smartpay_frontend;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class DealerActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 21903;
    String username, emailid, fullname, products, deals;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter1;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer);

        DataBaseAssistant assist = new DataBaseAssistant(this);

        this.username = getIntent().getStringExtra("Username");
        emailid = assist.getEmail(username);
        fullname = assist.getFullName(username);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter1 = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager1 = (ViewPager) findViewById(R.id.container1);
        mViewPager1.setAdapter(mSectionsPagerAdapter1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(mViewPager1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void onProductsClick(View v) {

        AlertDialog alertDialog = new AlertDialog.Builder(DealerActivity.this).create();
        alertDialog.setTitle("Warning: ");
        alertDialog.setMessage("The uploaded file should be of .csv format. The file should be kept in the SD card. It should have the following columns in the following order:\n" +
                "1. Product Category\n" +
                "2. Date Bought(MM-DD-YEAR(If the month is between January to September just type 1-9 and not 01-09))\n" +
                "3. Product name\n");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("file/*");
                        startActivityForResult(intent, 1);
                    }
                });
        alertDialog.show();


    }
    public void  onActivityResult(int requestCode, int resultCode, Intent data){

        switch (requestCode) {
            case 1: {
                products = new String("");
                if(data == null)
                    return;
                String FilePath = data.getData().getPath();
                File file = new File(FilePath);
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(file));
                    String currentLine;
                    while((currentLine = br.readLine()) != null) {
                        products = products + currentLine;
                    }
                    Toast test = Toast.makeText(DealerActivity.this, "File uploaded", Toast.LENGTH_SHORT);
                    test.show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast test = Toast.makeText(DealerActivity.this, e.toString(), Toast.LENGTH_SHORT);
                    test.show();
                } catch (IOException ea) {
                    ea.printStackTrace();
                    Toast test = Toast.makeText(DealerActivity.this, ea.toString(), Toast.LENGTH_SHORT);
                    test.show();
                } finally {
                    try {
                        if(br != null)
                            br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        Toast test = Toast.makeText(DealerActivity.this, ex.toString(), Toast.LENGTH_SHORT);
                        test.show();
                    }
                }


            }
            case 2: {
                deals = new String("");
                if(data == null)
                    return;
                String FilePath = data.getData().getPath();
                File file = new File(FilePath);
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(file));
                    String currentLine;
                    while((currentLine = br.readLine()) != null) {
                        deals = deals + currentLine;
                    }
                    Toast test = Toast.makeText(DealerActivity.this, "File uploaded", Toast.LENGTH_SHORT);
                    test.show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast test = Toast.makeText(DealerActivity.this, e.toString(), Toast.LENGTH_SHORT);
                    test.show();
                } catch (IOException ea) {
                    ea.printStackTrace();
                    Toast test = Toast.makeText(DealerActivity.this, ea.toString(), Toast.LENGTH_SHORT);
                    test.show();
                } finally {
                    try {
                        if(br != null)
                            br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        Toast test = Toast.makeText(DealerActivity.this, ex.toString(), Toast.LENGTH_SHORT);
                        test.show();
                    }
                }
            }
        }
    }


    public void onDealsClick(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(DealerActivity.this).create();
        alertDialog.setTitle("Warning: ");
        alertDialog.setMessage("The uploaded file should be of .csv format. The file should be kept in the SD card. It should have the following columns in the following order:\n" +
                "1. Product Category\n" +
                "2. Deal\n" +
                "3. Product name\n");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("file/*");
                        startActivityForResult(intent, 2);
                    }
                });
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dealer, menu);
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

//    /**
//     * A placeholder fragment containing a simple view.
//     */
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
//            View rootView = inflater.inflate(R.layout.dealertab1accinfo, container, false);
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
                    DealerInfoTab tab1 = new DealerInfoTab();
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME_KEY", username);
                    bundle.putString("EMAIL_KEY", emailid);
                    bundle.putString("FULLNAME_KEY", fullname);
                    tab1.setArguments(bundle);
                    return tab1;
                case 1:
                    ProductsTab tab2 = new ProductsTab();
                    return tab2;
                case 2:
                    DealsTab tab3 = new DealsTab();
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
                    return "PRODUCTS";
                case 2:
                    return "DEALS";
            }
            return null;
        }
    }
}
