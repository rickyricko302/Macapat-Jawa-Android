package id.hikki.macapatjawa.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.google.android.material.snackbar.Snackbar;
import id.hikki.macapatjawa.R;

public class BarVisualizerPermisson extends AppCompatActivity {

    Button permission;
    TextView lewati;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_visualizer_permisson);
        permission = findViewById(R.id.buttonPermission);
        lewati = findViewById(R.id.lewati);
        permission.setOnClickListener(v->{
            ActivityCompat.requestPermissions(BarVisualizerPermisson.this, new String[]{Manifest.permission.RECORD_AUDIO},3);
        });
        lewati.setOnClickListener(v->{
            startActivity(new Intent(BarVisualizerPermisson.this, MainActivity.class));
            Toast.makeText(this, "Audio visual tidak bisa ditampilkan", Toast.LENGTH_SHORT).show();
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Audio visual akan ditampilkan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BarVisualizerPermisson.this,MainActivity.class));
        }
        else{
            Toast.makeText(this, "Audio visual tidak bisa ditampilkan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BarVisualizerPermisson.this,MainActivity.class));
        }
    }
}