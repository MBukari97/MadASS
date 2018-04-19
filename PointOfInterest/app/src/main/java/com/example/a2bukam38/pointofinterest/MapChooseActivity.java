package com.example.a2bukam38.pointofinterest;

/**
 * Created by Victorda on 19/05/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;

public class MapChooseActivity extends Activity implements View.OnClickListener {


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mca);

        Button regular = (Button)findViewById(R.id.btnRegular);
        regular.setOnClickListener((View.OnClickListener) this);
        Button cyclemap = (Button)findViewById(R.id.btnCyclemap);
        cyclemap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean cyclemap=false;
        if (v.getId()==R.id.btnCyclemap)
        {
            cyclemap=true;
        }
        bundle.putBoolean("com.example.cyclemap",cyclemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();


    }
}
