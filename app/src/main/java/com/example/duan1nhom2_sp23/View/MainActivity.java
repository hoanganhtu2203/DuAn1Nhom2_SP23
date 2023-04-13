package com.example.duan1nhom2_sp23.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;


import com.example.duan1nhom2_sp23.Action.CapNhatGiaDienNuocActivity;
import com.example.duan1nhom2_sp23.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton ftbTrangChu, ftbHoaDon, ftbPhong, ftbBangGia;
    boolean aBoolean = true;
    private NotificationCompat.Builder notBuilder;
//
    private static final int MY_NOTIFICATION_ID = 12345;

    private static final int MY_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ftbTrangChu = findViewById(R.id.ftbTrangChu);
        ftbHoaDon = findViewById(R.id.ftbHoaDon);
        ftbPhong = findViewById(R.id.ftbPhong);
        ftbBangGia = findViewById(R.id.ftbBangGia);

        ftbPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DanhSachPhongActivity.class);
                startActivity(intent);

            }
        });
        ftbBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CapNhatGiaDienNuocActivity.class);
                startActivity(intent);

            }
        });
        ftbHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DanhSachHoaDonActivity.class);
                startActivity(intent);

            }
        });

        ftbTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean)
                {
                    ftbPhong.show();
                    ftbBangGia.show();
                    ftbHoaDon.show();
                    aBoolean= false;

                }
                else
                {
                    ftbPhong.hide();
                    ftbBangGia.hide();
                    ftbHoaDon.hide();
                    aBoolean= true;
                }
            }
        });
    }}

