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
import com.example.duan1nhom2_sp23.Action.ThemPhongActivity;
import com.example.duan1nhom2_sp23.Activity.LoginActivity;
import com.example.duan1nhom2_sp23.Adapter.AdapterPhong;
import com.example.duan1nhom2_sp23.Database.Database;
import com.example.duan1nhom2_sp23.Model.Phong;
import com.example.duan1nhom2_sp23.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachPhongActivity extends AppCompatActivity {
    final String DATABASE_NAME = "QuanLyNhaTroNew.sqlite";
    SQLiteDatabase database;
    ListView lsvDanhSachPhong;
    ArrayList<Phong> list;
    AdapterPhong adapter;
    int maphong = -1;
    int position = 0;
    FloatingActionButton ftbTrangChu, ftbHoaDon, ftbPhong, ftbBangGia, ftbLogout;
    boolean aBoolean = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phong);
        addControl();
        readData();
        AnhXa();
        thaotac();
    }



    private void readData()
    {
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor =  database.rawQuery("select * from Phong",null);
        list.clear();

        for(int i = 0 ; i< cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            int maphong = cursor.getInt(0);
            String tenphong = cursor.getString(1);
            String lau = cursor.getString(2);
            String tiencoc = cursor.getString(3);
            int sodien = cursor.getInt(4);
            int sonuoc = cursor.getInt(5);
            String trangthai = cursor.getString(6);
            list.add(new Phong(maphong,tenphong,lau,tiencoc,sodien,sonuoc,trangthai));
        }
        adapter.notifyDataSetChanged();
    }

    private void addControl()
    {
        lsvDanhSachPhong = (ListView) findViewById(R.id.lsvDanhsachphong);
        list = new ArrayList<>();
        adapter = new AdapterPhong(this,list);
        lsvDanhSachPhong.setAdapter(adapter);

    }



    public interface  SingleChoiceListener{
        void onPositionButtonClick(String[] list1,int position);
        void onNagativeButtonClick();
    }
    SingleChoiceListener mlistener;



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                final AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachPhongActivity.this);
                final String[] list1 = {"Tất cả", "Còn Trống","Đã cho thuê"};
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
                            database = Database.initDatabase(DanhSachPhongActivity.this,DATABASE_NAME);
                            Cursor cursor =  database.rawQuery("select * from Phong where TrangThai = ?",new String[]{0+""});
                            list.clear();

                            for(int m = 0 ; m< cursor.getCount(); m++)
                            {
                                cursor.moveToPosition(m);
                                int maphong = cursor.getInt(0);
                                String tenphong = cursor.getString(1);
                                String lau = cursor.getString(2);
                                String tiencoc = cursor.getString(3);
                                int sodien = cursor.getInt(4);
                                int sonuoc = cursor.getInt(5);
                                String trangthai = cursor.getString(6);
                                list.add(new Phong(maphong,tenphong,lau,tiencoc,sodien,sonuoc,trangthai));
                            }
                            adapter.notifyDataSetChanged();

                        }else{
                            database = Database.initDatabase(DanhSachPhongActivity.this,DATABASE_NAME);
                            Cursor cursor =  database.rawQuery("select * from Phong where TrangThai = ?",new String[]{1+""});
                            list.clear();

                            for(int m = 0 ; m< cursor.getCount(); m++)
                            {
                                cursor.moveToPosition(m);
                                int maphong = cursor.getInt(0);
                                String tenphong = cursor.getString(1);
                                String lau = cursor.getString(2);
                                String tiencoc = cursor.getString(3);
                                int sodien = cursor.getInt(4);
                                int sonuoc = cursor.getInt(5);
                                String trangthai = cursor.getString(6);
                                list.add(new Phong(maphong,tenphong,lau,tiencoc,sodien,sonuoc,trangthai));
                            }
                            adapter.notifyDataSetChanged();

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
            case R.id.action_add:
                Intent intent1 = getIntent();
                maphong = intent1.getIntExtra("MaPhong",-1);
                Intent intent = new Intent(DanhSachPhongActivity.this, ThemPhongActivity.class);
                intent.putExtra("MaPhong1",maphong);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void AnhXa()
    {
        ftbLogout = findViewById(R.id.btnLogout);
        ftbTrangChu = findViewById(R.id.ftbTrangChu);
        ftbHoaDon = findViewById(R.id.ftbHoaDon);
        ftbPhong = findViewById(R.id.ftbPhong);
        ftbBangGia = findViewById(R.id.ftbBangGia);

    }


    private void thaotac() {


        ftbPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachPhongActivity.this, DanhSachPhongActivity.class);
                startActivity(intent);

            }
        });
        ftbBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachPhongActivity.this, CapNhatGiaDienNuocActivity.class);
                startActivity(intent);

            }
        });
        ftbLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachPhongActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        ftbHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachPhongActivity.this, DanhSachHoaDonActivity.class);
                startActivity(intent);

            }
        });

        ftbTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    ftbLogout.show();
                    ftbPhong.show();
                    ftbBangGia.show();
                    ftbHoaDon.show();
                    aBoolean = false;

                } else {
                    ftbLogout.hide();
                    ftbPhong.hide();
                    ftbBangGia.hide();
                    ftbHoaDon.hide();
                    aBoolean = true;
                }
            }
        });
    }}
