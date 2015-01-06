package com.codavthesis.datacollector;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.CallLog;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import java.io.File;
import java.io.PrintWriter;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String DATABASE_NAME = "UsageData.db";
    public static final int DATABASE_VERSION = 1;
    //app variable, assign on installation, initialize here
    public static final int USER_ID = 0;
    private String callslog = "";
    private String msgslog = "";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        try{
            getCalls();
            getMsgs();
        } catch (Exception e){
            System.out.println("ERROR: " + e);
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section_batterylevel);
                break;
            case 2:
                mTitle = getString(R.string.title_section_powerconnection);
                break;
            case 3:
                mTitle = getString(R.string.title_section_connectivitystatus);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    public void getCalls() throws Exception {

        File callsFile = new File( Environment.getExternalStorageDirectory().getPath().toString() + "/callsLog.txt");
        callsFile.createNewFile();

        System.out.println("PATH: " + callsFile.getPath());

        PrintWriter callsPrinter = new PrintWriter(callsFile);

        StringBuilder sb = new StringBuilder();

        Cursor cursor = getContentResolver().query( CallLog.Calls.CONTENT_URI,null, null,null, null);
        cursor.moveToFirst();

        int number = cursor.getColumnIndex( CallLog.Calls.NUMBER );
        int type = cursor.getColumnIndex( CallLog.Calls.TYPE );
        int date = cursor.getColumnIndex( CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex( CallLog.Calls.DURATION);

        // comma-separated column labels
        sb.append("phNumber,dircode,callDayTime,duration" + "\n");

        while ( cursor.moveToNext() ) {
            String phNumber = cursor.getString( number );
            String callType = cursor.getString( type );
            String callDate = cursor.getString( date );
            Time t = new Time();
            t.set(Long.valueOf(callDate));
            String callDayTime = t.format("%Y:%m:%d %H:%M:%S");
            String callDuration = cursor.getString( duration );
            int dircode = Integer.parseInt( callType );

            sb.append(phNumber + ',' + dircode + ',' + callDayTime + ',' + callDuration + '\n');

        }
        cursor.close();
        callsPrinter.write(sb.toString());
        System.out.println(sb);

        callslog = sb.toString();
    }

    public void getMsgs() throws Exception{
        File msgsFile = new File( Environment.getExternalStorageDirectory().getPath().toString() + "/msgsLog.txt");
        msgsFile.createNewFile();

        System.out.println("PATH: " + msgsFile.getPath());

        PrintWriter msgsPrinter = new PrintWriter(msgsFile);

        StringBuilder sb = new StringBuilder();

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);

        sb.append("number,type,date,body"+"\n");

        int bodyInt = cursor.getColumnIndex("body");
        int addressInt = cursor.getColumnIndex("address");
        int dateInt = cursor.getColumnIndex("date");
        int typeInt = cursor.getColumnIndex("type");

        while ( cursor.moveToNext() ) {
            String body = cursor.getString(bodyInt);
            String number = cursor.getString(addressInt);
            String date = cursor.getString(dateInt);
            Time t = new Time();
            t.set(Long.valueOf(date));
            String smsDayTime = t.format("%Y:%m:%d %H:%M:%S");
            String type = cursor.getString(typeInt);
            //1 inbox; 2 sent; 3 draft

            sb.append(number + "," + type + "," + smsDayTime + "," + body + "\n");

        }
        cursor.close();
        msgsPrinter.write(sb.toString());
        System.out.println(sb);

        msgslog = sb.toString();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
