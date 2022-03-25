package id.hikki.macapatjawa.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import id.hikki.macapatjawa.Adapter.AdapterRecycler;
import id.hikki.macapatjawa.Model.DataItem;
import id.hikki.macapatjawa.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    AdapterRecycler adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler);
        loadData();
    }

    private void loadData() {
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("macapat.json")));
            StringBuilder sb = new StringBuilder();
            String g;
            while((g=br.readLine()) != null){
                sb.append(g);
            }
            DataItem[] data = gson.fromJson(sb.toString(),DataItem[].class);

            String[] audio = {"maskumambang","mijil","sinom","kinanthi","asmarandana","gambuh","dhandanggula",
                           "durma","pangkur","megatruh","pocung"};
            adapter = new AdapterRecycler(data);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(adapter);
            adapter.setClick(new AdapterRecycler.OnClickItem() {
                @Override
                public void click(View v, int posisi) {
                    String paugeran = String.format("- Guru Gatra         : %s\n- Guru Wilangan : %s\n- Guru lagu           : %s",data[posisi].getGatra(),data[posisi].getWilangan(),data[posisi].getLagu());
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("judul", data[posisi].getJeneng());
                    intent.putExtra("makna", data[posisi].getArti());
                    intent.putExtra("watak", data[posisi].getWatak());
                    intent.putExtra("paugeran", paugeran);
                    intent.putExtra("lirikJawa", data[posisi].getTembang());
                    intent.putExtra("maknaLirik", data[posisi].getMakna());
                    intent.putExtra("audio",audio[posisi]);
                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}