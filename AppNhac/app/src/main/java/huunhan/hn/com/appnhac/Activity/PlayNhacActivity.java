package huunhan.hn.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import huunhan.hn.com.appnhac.Adapter.PlayNhacAdapter;
import huunhan.hn.com.appnhac.Adapter.ViewpagerPlaylistNhac;
import huunhan.hn.com.appnhac.Fragment.Fragment_Dia_Nhac;
import huunhan.hn.com.appnhac.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import huunhan.hn.com.appnhac.Model.Baihat;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.MyService;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarPlaynhac;
    TextView tvTimeSong, tvTotalTimesong;
    SeekBar skTime;
    ImageButton imbPlay, imbRepeat, imbNext, imbPre, imbRandom;
    ViewPager viewPagerPlaynhac;
    public static ArrayList<Baihat> mangBaihat = new ArrayList<>();
    public static ViewpagerPlaylistNhac viewpagerPlaylistNhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Bai_Hat fragmentPlayDanhSachBaiHat;
    public static MediaPlayer mediaPlayer;

    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    int action = 0;

    //get action
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            action = intent.getIntExtra("action_music", 0);
            Log.e("number", action + "play");
            handlerLayoutMusic(action);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);

        IntentFilter filter = new IntentFilter("send_data_to_activity");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        anhXa();
        getDataFromIntent();
        eventClick();


        fragment_dia_nhac = (Fragment_Dia_Nhac) viewpagerPlaylistNhac.getItem(1);
        if (mangBaihat.size() > 0) {
            getSupportActionBar().setTitle(mangBaihat.get(0).getTenbaihat());
            Baihat baihat = mangBaihat.get(0);
            PlayMp3 playMp3 = new PlayMp3();
            playMp3.execute(mangBaihat.get(0).getLinkbaihat());
            imbPlay.setImageResource(R.drawable.iconpause);

//gửi bài hát tới Service
            sendBaihattoService(baihat);
        }
    }

    //gửi bài hát tới Service
    private void sendBaihattoService(Baihat baihat) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("baihatService", baihat);
        startService(intent);
    }

    private void handlerLayoutMusic(int action) {
        switch (action) {
            case MyService.ACTION_PAUSE:
                if (mediaPlayer.isPlaying()) {
                    imbPlay.setImageResource(R.drawable.iconplay);
                    mediaPlayer.pause();
                }
                break;
            case MyService.ACTION_RESUME:
                if (!mediaPlayer.isPlaying()) {
                    imbPlay.setImageResource(R.drawable.iconpause);
                    mediaPlayer.start();
                }
                break;
            case MyService.ACTION_NEXT:
                nextSong();
                break;
            case MyService.ACTION_PRE:
                preSong();
                break;
            case MyService.ACTION_CLEAR:
                mediaPlayer.stop();
                mangBaihat.clear();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void sendActionToService(int action) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("action_service", action);
        startService(intent);
    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewpagerPlaylistNhac.getItem(0) != null) {
                    if (mangBaihat.size()>0) {
                        fragment_dia_nhac.Playnhac(mangBaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        },500);
        imbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imbPlay.setImageResource(R.drawable.iconplay);
                    sendActionToService(MyService.ACTION_PAUSE);
                } else {
                    mediaPlayer.start();
                    imbPlay.setImageResource(R.drawable.iconpause);
                    sendActionToService(MyService.ACTION_RESUME);
                }
            }
        });
        imbRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imbRepeat.setImageResource(R.drawable.iconsyned);
                        imbRandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imbRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imbRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imbRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imbRandom.setImageResource(R.drawable.iconshuffled);
                        imbRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imbRandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    imbRandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        skTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextSong();
            }
        });
        imbPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preSong();
            }
        });
    }
    private void preSong() {
        if (mangBaihat.size() > 0) {
            if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            if (position < mangBaihat.size()) {
                imbPlay.setImageResource(R.drawable.iconpause);
                position--;
                if (position < 0) {
                    position = mangBaihat.size() -1 ;
                }
                if (repeat == true) {
                    position += 1;
                }
                if (checkrandom == true) {
                    Random random = new Random();
                    int index = random.nextInt(mangBaihat.size());
                    if (index == position) {
                        position = index - 1;
                    }
                    position = index;
                }
                sendBaihattoService(mangBaihat.get(position));
                new PlayMp3().execute(mangBaihat.get(position).getLinkbaihat());
                fragment_dia_nhac.Playnhac(mangBaihat.get(position).getHinhbaihat());
                toolbarPlaynhac.setTitle(mangBaihat.get(position).getTenbaihat());
                UpdateTime();
            }
        }
        imbPre.setClickable(false);
        imbNext.setClickable(false);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                imbPre.setClickable(true);
                imbNext.setClickable(true);
            }
        }, 500);
    }
    private void nextSong() {
        if (mangBaihat.size() > 0) {
            if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            if (position < mangBaihat.size()) {
                imbPlay.setImageResource(R.drawable.iconpause);
                position++;
                if (repeat == true) {
                    if (position == 0) {
                        position = mangBaihat.size();
                    }
                    position -= 1;
                }
                if (checkrandom == true) {
                    Random random = new Random();
                    int index = random.nextInt(mangBaihat.size());
                    if (index == position) {
                        position = index - 1;
                    }
                    position = index;
                }
                if (position > mangBaihat.size() -1) {
                    position = 0;
                }
                sendBaihattoService(mangBaihat.get(position));
                new PlayMp3().execute(mangBaihat.get(position).getLinkbaihat());
                fragment_dia_nhac.Playnhac(mangBaihat.get(position).getHinhbaihat());
                getSupportActionBar().setTitle(mangBaihat.get(position).getTenbaihat());
                UpdateTime();
            }
        }
        imbPre.setClickable(false);
        imbNext.setClickable(false);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                imbPre.setClickable(true);
                imbNext.setClickable(true);
            }
        }, 500);
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        mangBaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangBaihat.add(baihat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangBaihat = baihatArrayList;
            }
        }
    }

    private void anhXa() {
        toolbarPlaynhac = findViewById(R.id.toolbar_playnhac);
        tvTimeSong = findViewById(R.id.tv_timesong);
        tvTotalTimesong = findViewById(R.id.tv_total_timesong);
        skTime = findViewById(R.id.seekbar_song);
        imbNext = findViewById(R.id.imb_next);
        imbPlay = findViewById(R.id.imb_play);
        imbPre = findViewById(R.id.imb_preview);
        imbRandom = findViewById(R.id.imb_suffle);
        imbRepeat = findViewById(R.id.imb_repeat);
        viewPagerPlaynhac = findViewById(R.id.viewPager_playnhac);
        setSupportActionBar(toolbarPlaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mangBaihat.clear();
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                stopService(intent);
                finish();
            }
        });

        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragmentPlayDanhSachBaiHat = new Fragment_Play_Danh_Sach_Bai_Hat();
        viewpagerPlaylistNhac = new ViewpagerPlaylistNhac(getSupportFragmentManager());
        viewpagerPlaylistNhac.addFragment(fragmentPlayDanhSachBaiHat);
        viewpagerPlaylistNhac.addFragment(fragment_dia_nhac);
        viewPagerPlaynhac.setAdapter(viewpagerPlaylistNhac);
    }




    class PlayMp3 extends AsyncTask<String,Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            UpdateTime();
        }
    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTotalTimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        skTime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    skTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 150);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 150);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (mangBaihat.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < mangBaihat.size()) {
                            imbPlay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = mangBaihat.size();
                                }
                                position -= 1;
                            }
                            if (checkrandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(mangBaihat.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > mangBaihat.size() -1) {
                                position = 0;
                            }
                            new PlayMp3().execute(mangBaihat.get(position).getLinkbaihat());
                            fragment_dia_nhac.Playnhac(mangBaihat.get(position).getHinhbaihat());
                            getSupportActionBar().setTitle(mangBaihat.get(position).getTenbaihat());
                        }
                    }
                    imbPre.setClickable(false);
                    imbNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imbPre.setClickable(true);
                            imbNext.setClickable(true);
                        }
                    }, 1500);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 500);
                }
            }
        }, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        mangBaihat.clear();
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        stopService(intent);
    }
}