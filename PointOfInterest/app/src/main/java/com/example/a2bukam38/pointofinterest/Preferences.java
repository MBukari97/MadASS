package com.example.a2bukam38.pointofinterest;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;

public class Preferences extends PreferenceActivity {

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean autoload=false;

        bundle.putBoolean("com.example.autoload",autoload);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();


    }
}
