package com.example.duan1nhom2_sp23.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1nhom2_sp23.Action.CapNhatGiaDienNuocActivity;
import com.example.duan1nhom2_sp23.Activity.LoginActivity;
import com.example.duan1nhom2_sp23.Adapter.AdapterChiTietHoaDon;
import com.example.duan1nhom2_sp23.Database.Database;
import com.example.duan1nhom2_sp23.Model.Phong;
import com.example.duan1nhom2_sp23.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachPhong_HoaDonActivity extends AppCompatActivity {

    final String DATABASE_NAME = "QuanLyNhaTroNew.sqlite";
    SQLiteDatabase database;
    ListView lsvDanhSachPhong;
    ArrayList<Phong> list;
    AdapterChiTietHoaDon adapter;

    int position = 0 ;
    FloatingActionButton ftbTrangChu, ftbHoaDon, ftbPhong, ftbBangGia, fbtLogout;
    boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phong__hoa_don);
        addControl();
        readData();
        AnhXa();
        thaotac();

    }

    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("select * from Phong", null);
        list.clear();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int maphong = cursor.getInt(0);
            String tenphong = cursor.getString(1);
            String lau = cursor.getString(2);
            String tiencoc = cursor.getString(3);
            int sodien = cursor.getInt(4);
            int sonuoc = cursor.getInt(5);
            String trangthai = cursor.getString(6);
            list.add(new Phong(maphong, tenphong, lau, tiencoc, sodien, sonuoc, trangthai));
        }
        adapter.notifyDataSetChanged();
    }

    private void addControl() {
        lsvDanhSachPhong = (ListView) findViewById(R.id.lsvdsphonghoadon);
        list = new ArrayList<>();
        adapter = new AdapterChiTietHoaDon(this, list);
        lsvDanhSachPhong.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.allhoadon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_searchallhd:

                final AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachPhong_HoaDonActivity.this);
                final String[] list1 = {"Tất cả", "Chưa thanh toán","Đã thanh toán"};
                builder.setTitle("Tìm kiếm");
                builder.setSingleChoiceItems(list1, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        position = i;
                        if(position==0)
                        {
                            readData();

                        }
                        else if(position == 1)
                        {
                            

                        }else{


                        }
                    }
                });

                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog =builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void AnhXa()
    {
        ftbTrangChu = findViewById(R.id.ftbTrangChu);
        ftbHoaDon = findViewById(R.id.ftbHoaDon);
        ftbPhong = findViewById(R.id.ftbPhong);
        ftbBangGia = findViewById(R.id.ftbBangGia);


    }

    private void thaotac() {
        ftbTrangChu = findViewById(R.id.ftbTrangChu);
        ftbHoaDon = findViewById(R.id.ftbHoaDon);
        ftbPhong = findViewById(R.id.ftbPhong);
        ftbBangGia = findViewById(R.id.ftbBangGia);
        fbtLogout = findViewById(R.id.btnLogout);
        fbtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachPhong_HoaDonActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ftbPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachPhong_HoaDonActivity.this, DanhSachPhongActivity.class);
                startActivity(intent);

            }
        });
        ftbBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachPhong_HoaDonActivity.this, CapNhatGiaDienNuocActivity.class);
                startActivity(intent);

            }
        });
        ftbHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachPhong_HoaDonActivity.this, DanhSachHoaDonActivity.class);
                startActivity(intent);

            }
        });

        ftbTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    ftbPhong.show();
                    fbtLogout.show();
                    ftbBangGia.show();
                    ftbHoaDon.show();
                    aBoolean = false;

                } else {
                    fbtLogout.hide();
                    ftbPhong.hide();
                    ftbBangGia.hide();
                    ftbHoaDon.hide();
                    aBoolean = true;
                }
            }
        });
    }}

