package com.example.mumin.notdefteri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NotDefteri extends AppCompatActivity {


    EditText editKonu;
    EditText editIcerik;
    TextView txtkonu;
    TextView txtnot;
    Button btnBiter;
    Button btnGuncelle;
    int x;

    SharedPreferences preferences;
    SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_defteri);


 preferences=  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    editor= preferences.edit();
        editKonu=(EditText) findViewById(R.id.editKonu);
        editIcerik=(EditText) findViewById(R.id.editIcerik);
        txtkonu=(TextView) findViewById(R.id.txtKonu);
        txtnot=(TextView) findViewById(R.id.txtIcerik);
        btnBiter= (Button) findViewById(R.id.btnBitti);
        btnGuncelle=(Button)findViewById(R.id.btnGuncelle);


       // editor.putString("konu",editKonu.getText().toString());
        //editor.putString("icerik",editIcerik.getText().toString());

      //  editor.commit();


        btnBiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = preferences.getInt("index",0);
                editor.putString("konu"+x, editKonu.getText().toString());
                editor.putString("not"+x, editIcerik.getText().toString());
                x++;
                editor.putInt("index",x);
                editor.commit();
                Intent gecis = new Intent(NotDefteri.this, Ana_Sayfa.class);
                startActivity(gecis);


            }
        });




    }

}
