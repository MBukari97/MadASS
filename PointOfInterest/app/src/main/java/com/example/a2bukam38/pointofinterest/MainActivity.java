package com.example.a2bukam38.pointofinterest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;



    /** Called when the activity is first created. */
    @Override
    public void PreferenceManager.setDefaultValues(this, R.xml.preference, false);
    {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        setContentView(R.layout.activity_main);

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>()
        {
            public boolean onItemLongPress(int i, OverlayItem item)
            {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item)
            {
                Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
        OverlayItem fernhurst = new OverlayItem("Fernhurst", "the village of Fernhurst", new GeoPoint(51.05, -0.72));

        // NOTE is just this.getDrawable() if supporting API 21+ only
        fernhurst.setMarker(getResources().getDrawable(R.drawable.marker));
        items.addItem(fernhurst);
        items.addItem(new OverlayItem("Blackdown", "highest point in West Sussex", new GeoPoint(51.0581, -0.6897)));
        mv.getOverlays().add(items);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)

        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;
        }

        if(item.getItemId() == R.id.addpoi)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,AddPOI.class);
            startActivityForResult(intent,1);
            return true;
        }

        if(item.getItemId() == R.id.prefs)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,Preferences.class);
            startActivityForResult(intent,2);
            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }

        if(requestCode==1)
        {
            Bundle extras=intent.getExtras();
            String name = extras.getString("com.example.name");
            String address = extras.getString("com.example.address");
            String cuisine = extras.getString("com.example.cuisine");
            String ratings = extras.getString("com.example.ratings");

            double lat = mv.getMapCenter().getLatitude();
            double lon = mv.getMapCenter().getLongitude();

            Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();

            markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>()
            {
                public boolean onItemLongPress(int i, OverlayItem item)
                {
                    Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                    return true;
                }

                public boolean onItemSingleTapUp(int i, OverlayItem item)
                {
                    Toast.makeText(MainActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            };

            items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), null);
            OverlayItem fernhurst = new OverlayItem(name, description, new GeoPoint(lat, lon));
            items.addItem(fernhurst);
            mv.getOverlays().add(items);

            try
            {


                    PrintWriter pw =
                            new PrintWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/marks.txt");
                  //  BufferedReader reader = new BufferedReader(fr);
                for(int i=0; i<items.size(); i++) {
                    OverlayItem item = items.getItem(i);
                    pw.println(item.getSnippet()+","+item.getTitle()+","+type+","+item.getPoint().getLatitude()+","+item.getPoint().getLongitude());
                    // close the file to ensure data is flushed to file
                }
                pw.close();
            }
            catch(IOException e)
            {
                new AlertDialog.Builder(this).setMessage("ERROR: " + e).setPositiveButton("OK", null).show();

            }

        }

        if(requestCode==2)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean autoload = extras.getBoolean("com.example.autoload");
            }
        }
    }
    public void onStart()
    {
        super.OnStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
        booleen autodownload = prefs.getBoolean("autodownload", true);
        String restaurantCode = prefs.getRestaurant("restaurant", "None");

        // do something with the preference data...
    }
}
