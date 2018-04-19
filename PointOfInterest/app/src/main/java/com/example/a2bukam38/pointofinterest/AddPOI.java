package com.example.a2bukam38.pointofinterest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Victorda on 19/05/2017.
 */
public class AddPOI  extends Activity implements View.OnClickListener {


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apoi);

        Button regular = (Button)findViewById(R.id.button);
        regular.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View v)
    {
        EditText et = (EditText)findViewById(R.id.et1);
        String name = et.getText().toString();

        EditText et2 = (EditText)findViewById(R.id.et2);
        String address = et2.getText().toString();

        EditText et3 = (EditText)findViewById(R.id.et3);
        String cuisine = et3.getText().toString();

        EditText et4 = (EditText)findViewById(R.id.et4);
        String rating = (EditText) et4.getText().toString();

        Intent intent = new Intent();
        Bundle bundle=new Bundle();


        bundle.putString("com.example.name", name);
        bundle.putString("com.example.address",address);
        bundle.putString("com.example.cuisine", cuisine);
        bundle.putString("com.example.rating", rating);

        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();


    }
}

