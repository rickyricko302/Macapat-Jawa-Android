package id.hikki.macapatjawa.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.gauravk.audiovisualizer.visualizer.BarVisualizer;
import id.hikki.macapatjawa.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    BarVisualizer bar;
    ImageView play, maju, mundur;
    MediaPlayer mAudioPlayer;
    MotionLayout parent;
    TextView mulai, akhir;
    ProgressBar progressBar;
    int pos;
    String tembang, makna, watak, paugeran, lirik, maknaLirik;
    int resID;
    TextView tTembang, tMakna, tWatak, tPaugeran, tLirik, tMaknaLirik;
    boolean resume;
    private Handler mSeekbarUpdateHandler = new Handler();
    private Runnable mUpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            progressBar.setProgress(mAudioPlayer.getCurrentPosition());
            pos = mAudioPlayer.getCurrentPosition();
            mulai.setText(getTimeString(mAudioPlayer.getCurrentPosition()));
            mSeekbarUpdateHandler.postDelayed(this, 1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tTembang = findViewById(R.id.textJudul);
        tMakna = findViewById(R.id.textMakna);
        tWatak = findViewById(R.id.textWatak);
        parent = findViewById(R.id.parentDetail);
        tPaugeran = findViewById(R.id.textPaugeran);
        tLirik = findViewById(R.id.textLirikJawa);
        tMaknaLirik = findViewById(R.id.textMaknaLirik);
        bar = findViewById(R.id.blast);
        play = findViewById(R.id.playButton);
        maju = findViewById(R.id.majuButton);
        mundur = findViewById(R.id.mundurButton);
        progressBar = findViewById(R.id.progressBar);
        mulai = findViewById(R.id.textMulai);
        akhir = findViewById(R.id.textAkhir);
        Intent intent = getIntent();
        tembang = intent.getStringExtra("judul");
        makna = intent.getStringExtra("makna");
        watak = intent.getStringExtra("watak");
        paugeran = intent.getStringExtra("paugeran");
        lirik = intent.getStringExtra("lirikJawa");
        maknaLirik = intent.getStringExtra("maknaLirik");
        tTembang.setText(tembang);
        tMakna.setText(makna);
        tWatak.setText(watak);
        tPaugeran.setText(paugeran);
        tLirik.setText(lirik);
        tMaknaLirik.setText(maknaLirik);
        resID = getResources().getIdentifier(intent.getStringExtra("audio"), "raw", getPackageName());
        mAudioPlayer = MediaPlayer.create(this, resID);
        int max = mAudioPlayer.getDuration();
        progressBar.setMax(max);

        barVisual();
        parent.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                if(currentId == R.id.end){
                    bar.setColor(Color.parseColor("#CFCFCF"));
                }
                else{
                    bar.setColor(Color.parseColor("#583510"));
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });

        akhir.setText(getTimeString(mAudioPlayer.getDuration()));
        play.setOnClickListener(v->{

            if(!mAudioPlayer.isPlaying()) {
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_black_36dp));
                if(resume){
                    mAudioPlayer.seekTo(pos);
                }
                mAudioPlayer.start();
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar,0);
            }
            else{
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_36dp));
                mAudioPlayer.pause();
                resume = true;
                mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
            }
        });
        mAudioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mAudioPlayer.seekTo(0);
                mAudioPlayer.pause();
                play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_36dp));
                mulai.setText("00:00");
                resume = false;
            }
        });
        mundur.setOnClickListener(v->{
            mundur.setScaleX((float) 1.2);
            mundur.setScaleY((float) 1.2);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mundur.setScaleX(1);
                    mundur.setScaleY(1);
                    if(mAudioPlayer.getCurrentPosition() >= 5000) {
                        mAudioPlayer.seekTo(mAudioPlayer.getCurrentPosition() - 5000);
                        pos = mAudioPlayer.getCurrentPosition();
                        mulai.setText(getTimeString(mAudioPlayer.getCurrentPosition()));
                    }
                    else{
                        mAudioPlayer.seekTo(0);
                    }
                }
            },300);
        });
        maju.setOnClickListener(v->{
            maju.setScaleX((float) 1.2);
            maju.setScaleY((float) 1.2);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    maju.setScaleX(1);
                    maju.setScaleY(1);
                    if(mAudioPlayer.getCurrentPosition() <= max) {
                        mAudioPlayer.seekTo(mAudioPlayer.getCurrentPosition() + 5000);
                        pos = mAudioPlayer.getCurrentPosition();
                        mulai.setText(getTimeString(mAudioPlayer.getCurrentPosition()));
                    }
                    else{
                        mAudioPlayer.seekTo(0);
                    }
                }
            },300);
        });


    }

    private String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();

      //  int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);
        buf.append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));
        return buf.toString();
    }

    private void barVisual(){
        try {
            InputStream inputStream = getResources().openRawResource(resID);
            byte[] myByteArray = getBytesFromInputStream(inputStream);
            int audioSessionId = mAudioPlayer.getAudioSessionId();
            if (audioSessionId != -1)
                bar.setAudioSessionId(audioSessionId);
            bar.setRawAudioBytes(myByteArray);
            bar.show();
        } catch(Exception e) {
            // Handle error...
            Log.e("Error",e.getMessage());
        }
    }
    public static byte[] getBytesFromInputStream(InputStream is)  {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        try {
            for (int len = is.read(buffer); len != -1; len = is.read(buffer)) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }

    @Override
    protected void onDestroy() {
        if(mAudioPlayer != null){
            mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
            mAudioPlayer.release();
            bar.release();
        }
        super.onDestroy();
    }
}