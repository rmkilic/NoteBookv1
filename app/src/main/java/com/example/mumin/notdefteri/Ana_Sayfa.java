package com.example.mumin.notdefteri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ana_Sayfa extends AppCompatActivity {

    public int kontrol;
    Button btnEkler;
    ListView listView;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ArrayList<String> konuList = new ArrayList<>();
    ArrayList<String> icerikList = new ArrayList<>();
    ArrayList<Integer> indexList = new ArrayList<>();
    ArrayAdapter<String> veriAdaptoru;


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Geri döndü");

        goster();
        veriAdaptoru.notifyDataSetChanged();
    }

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
        veriAdaptoru = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, konuList);
        //veriAdaptoru.notifyDataSetChanged();

        listView.setAdapter(veriAdaptoru);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent gecis = new Intent(Ana_Sayfa.this, NotDefteri.class);
                gecis.putExtra("indis", indexList.get(i));
                System.out.println("-----"+indexList.get(i));
                startActivity(gecis);
            }
        });
        btnEkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gecis = new Intent(Ana_Sayfa.this, NotDefteri.class);
                startActivity(gecis);
            }
        });
    }


    public void goster() {
        indexList.clear();
        konuList.clear();
        icerikList.clear();
        int index = preferences.getInt("index", -1);
        if (index != -1) {
            for (int i = 0; i < index; i++) {
                String konumuz = preferences.getString("konu" + i, null);
                String icerigi = preferences.getString("icerik" + i, null);
                if (konumuz != null) {
                    icerikList.add(icerigi);
                    konuList.add(konumuz);
                    indexList.add(i);
                }
                System.out.println("indexList = " + indexList);
            }
        }




    }

}

