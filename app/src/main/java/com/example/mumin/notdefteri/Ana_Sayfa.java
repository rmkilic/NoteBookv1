package com.example.mumin.notdefteri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Ana_Sayfa extends AppCompatActivity {


    Button btnEkler;
    ListView listView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ArrayList<String> ekran = new ArrayList<String>(100);
   // String []ekran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana__sayfa);

        btnEkler = (Button) findViewById(R.id.btnEkle1);
        listView = (ListView) findViewById(R.id.listView1);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        //Shared dan veri cekme siknti.
        goster();

        btnEkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gecis = new Intent(Ana_Sayfa.this, NotDefteri.class);
                startActivity(gecis);

            }
        });


    }

  public void goster() {
      int index=preferences.getInt("index", 0);
      if(index!=0){
          for(int i= 1;i<index;i++){
              String konumuz = preferences.getString("konu"+i, "Konu girilmedi");
              ekran.add( konumuz );
          }
      }


      ArrayAdapter<String> veriAdaptoru = new ArrayAdapter<String>
              (this, android.R.layout.simple_list_item_1, android.R.id.text1, ekran);
      listView.setAdapter(veriAdaptoru);

    }
}

