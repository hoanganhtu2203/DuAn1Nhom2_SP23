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
import com.example.duan1nhom2_sp23.Action.LapHoaDonActivity;
import com.example.duan1nhom2_sp23.Activity.LoginActivity;
import com.example.duan1nhom2_sp23.Adapter.AdapterHoaDonCuaTungPhong;
import com.example.duan1nhom2_sp23.Database.Database;
import com.example.duan1nhom2_sp23.Model.ChiTietHoaDon;
import com.example.duan1nhom2_sp23.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HoaDonCuaTungPhongActivity extends AppCompatActivity
{
    final String DATABASE_NAME = "QuanLyNhaTroNew.sqlite";

    SQLiteDatabase database;
    ListView lsvHoaDonCuaTungPhong;
    ArrayList<ChiTietHoaDon> list;
    AdapterHoaDonCuaTungPhong adapter;
    int maphong =-1;
    FloatingActionButton ftbTrangChu, ftbHoaDon, ftbPhong, ftbBangGia, ftblogout;

    boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_cua_tung_phong);
        ReadData();
        thaotac();
    }




    private void ReadData()
    {
        Intent intent1 = getIntent();
        maphong = intent1.getIntExtra("MaPhong",-1);
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor =  database.rawQuery("select * from ChiTietHoaDon where MaPhong = ?",new String[]{maphong+""});
        list.clear();
        for(int i = 0 ; i< cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            int maCTHD = cursor.getInt(0);
            String tienPhong = cursor.getString(1);
            String tienDien = cursor.getString(2);
            String tineNuoc = cursor.getString(3);
            String tienInternet = cursor.getString(4);
            String tienDVKhac = cursor.getString(5);
            String lyDoThu = cursor.getString(6);
            String ngayLapHoaDOn = cursor.getString(7);
            String trangThai = cursor.getString(8);
            int maPhong = cursor.getInt(9);
            String tongTien = cursor.getString(10);
            String soDien = cursor.getString(11);
            String soNuoc = cursor.getString(12);
            list.add(new ChiTietHoaDon(maCTHD,tienPhong,tienDien,tineNuoc,tienInternet,
                    tienDVKhac,lyDoThu,ngayLapHoaDOn,trangThai,maPhong,tongTien,soDien,soNuoc));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_hoadon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_addHoaDon:
                Intent intent1 = getIntent();
                maphong = intent1.getIntExtra("MaPhong",-1);
                Cursor cursor =  database.rawQuery("select * from Phong where MaPhong = ?",new String[]{maphong+""});
                cursor.moveToFirst();
                String TT = cursor.getString(6);
                if(TT.equals("1"))
                {
                    Intent intent = new Intent(HoaDonCuaTungPhongActivity.this, LapHoaDonActivity.class);
                    intent.putExtra("MaPhong",maphong);
                    startActivity(intent);
                    finish();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(HoaDonCuaTungPhongActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Phòng còn trống nên không thể lập hóa đơn");

                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog =builder.create();
                    dialog.show();
                }
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
        ftblogout = findViewById(R.id.btnLogout);
        ftblogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoaDonCuaTungPhongActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ftbPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoaDonCuaTungPhongActivity.this, DanhSachPhongActivity.class);
                startActivity(intent);

            }
        });
        ftbBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoaDonCuaTungPhongActivity.this, CapNhatGiaDienNuocActivity.class);
                startActivity(intent);

            }
        });
        ftbHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoaDonCuaTungPhongActivity.this, DanhSachHoaDonActivity.class);
                startActivity(intent);

            }
        });

        ftbTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    ftbPhong.show();
                    ftblogout.show();
                    ftbBangGia.show();
                    ftbHoaDon.show();
                    aBoolean = false;

                } else {
                    ftblogout.hide();
                    ftbPhong.hide();
                    ftbBangGia.hide();
                    ftbHoaDon.hide();
                    aBoolean = true;
                }
            }
        });
    }}


