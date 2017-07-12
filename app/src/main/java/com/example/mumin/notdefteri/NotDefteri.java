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
    Button btnSil;
    Button btnEkle;
    Button btnGuncelle;
    int index;
    int x;



    SharedPreferences preferences;
    SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_defteri);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        editKonu = (EditText) findViewById(R.id.editKonu);
        editIcerik = (EditText) findViewById(R.id.editIcerik);
        btnSil = (Button) findViewById(R.id.btnSil);
        btnEkle = (Button) findViewById(R.id.btnEkle);
        btnGuncelle = (Button) findViewById(R.id.btnGuncelle);

        Bundle bd = getIntent().getExtras();
        if(bd != null)
        {
            index = getIntent().getIntExtra("indis",0);
            btnGuncelle.setVisibility(View.VISIBLE);
            btnEkle.setVisibility(View.INVISIBLE);
            tiklandigindaGetir(index);
        }
        else
        {
            Ekran_Goster();
            btnGuncelle.setVisibility(View.INVISIBLE);
            btnEkle.setVisibility(View.VISIBLE);
        }


        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    editor.putString("konu" + index, editKonu.getText().toString());
                    editor.putString("not" + index, editIcerik.getText().toString());
                    editor.commit();
                    finish();
                }
        });

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = preferences.getInt("index", 0);
                String konu = editKonu.getText().toString().trim();
                String icerik = editIcerik.getText().toString().trim();
                    if (konu.length() == 0 && icerik.length() > 0) {
                        Ekleme(icerik,icerik);
                    } else if (icerik.length() == 0) {
                        Toast toast=Toast.makeText(getApplicationContext(),"Kayıt oluşturulamadı",Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    } else {
                        Ekleme(konu,icerik);
                    }
            }
        });

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("konu"+index);
                editor.remove("not"+index);
                editor.commit();


                finish();
            }
        });
    }
        void tiklandigindaGetir(int indis)
        {
            editKonu.setText(preferences.getString("konu"+indis,"Konu girilmedi"));
            editIcerik.setText(preferences.getString("not"+indis,"İcerik girilmedi"));

        }

    void Ekleme(String konu,String icerik)
    {
        editor.putString("konu"+x, konu);
        editor.putString("not"+x, icerik);
        x++;
        editor.putInt("index",x);
        editor.commit();
       finish();
    }
    void Ekran_Goster()
    {
        editKonu.setVisibility(View.VISIBLE);
        editIcerik.setVisibility(View.VISIBLE);
    }


}
