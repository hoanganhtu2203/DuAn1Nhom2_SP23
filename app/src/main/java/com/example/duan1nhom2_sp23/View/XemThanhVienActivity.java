package com.example.duan1nhom2_sp23.View;

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
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1nhom2_sp23.Action.CapNhatGiaDienNuocActivity;
import com.example.duan1nhom2_sp23.Action.ThemNguoiThueActivity;
import com.example.duan1nhom2_sp23.Adapter.AdapterKhachThue;
import com.example.duan1nhom2_sp23.Database.Database;
import com.example.duan1nhom2_sp23.Model.KhachThue;
import com.example.duan1nhom2_sp23.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class XemThanhVienActivity extends AppCompatActivity
{
    final String DATABASE_NAME = "QuanLyNhaTroNew.sqlite";
    SQLiteDatabase database;
    ListView lsvDanhsachthanhvien;
    int maphong = -1;
    ArrayList<KhachThue> list;
    boolean aBoolean = true;

    AdapterKhachThue adapter;
    FloatingActionButton ftbTrangChu, ftbHoaDon, ftbPhong, ftbBangGia;
    boolean trove = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_thanh_vien);
        addControl();
        readData();
       thaotac();

    }
    private void readData()
    {
        Intent intent = getIntent();
        maphong = intent.getIntExtra("MaPhong",-1);
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("select * from KhachThue where MaPhong = ?",new String[]{maphong+""});
        list.clear();
        for(int i = 0 ; i< cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            int makhach = cursor.getInt(0);
            String tenkhach = cursor.getString(1);
            String gioitinh = cursor.getString(2);
            String ngaysinh = cursor.getString(3);
            String cmnd = cursor.getString(4);
            String sdt = cursor.getString(5);
            String ngaythue = cursor.getString(6);

            int maphong = cursor.getInt(7);
            list.add(new KhachThue(makhach,tenkhach,gioitinh,ngaysinh,cmnd,sdt,ngaythue,maphong));
        }
        adapter.notifyDataSetChanged();
    }

    private void addControl()
    {
        lsvDanhsachthanhvien = (ListView) findViewById(R.id.lsvDanhsachthanhvien);
        list = new ArrayList<>();
        adapter = new AdapterKhachThue(this,list);
        lsvDanhsachthanhvien.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themnguoithue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_addKhach:
                Intent intent1 = getIntent();
                maphong = intent1.getIntExtra("MaPhong",-1);
                Intent intent = new Intent(XemThanhVienActivity.this, ThemNguoiThueActivity.class);
                intent.putExtra("MaPhong1",maphong);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void thaotac() {
        ftbTrangChu = findViewById(R.id.ftbTrangChu);
        ftbHoaDon = findViewById(R.id.ftbHoaDon);
        ftbPhong = findViewById(R.id.ftbPhong);
        ftbBangGia = findViewById(R.id.ftbBangGia);

        ftbPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XemThanhVienActivity.this, DanhSachPhongActivity.class);
                startActivity(intent);

            }
        });
        ftbBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XemThanhVienActivity.this, CapNhatGiaDienNuocActivity.class);
                startActivity(intent);

            }
        });
        ftbHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XemThanhVienActivity.this, DanhSachHoaDonActivity.class);
                startActivity(intent);

            }
        });

        ftbTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    ftbPhong.show();
                    ftbBangGia.show();
                    ftbHoaDon.show();
                    aBoolean = false;

                } else {
                    ftbPhong.hide();
                    ftbBangGia.hide();
                    ftbHoaDon.hide();
                    aBoolean = true;
                }
            }
        });
    }}
