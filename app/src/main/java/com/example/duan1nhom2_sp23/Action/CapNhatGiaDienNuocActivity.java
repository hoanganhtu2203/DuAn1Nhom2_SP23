package com.example.duan1nhom2_sp23.Action;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1nhom2_sp23.Activity.LoginActivity;
import com.example.duan1nhom2_sp23.Database.Database;
import com.example.duan1nhom2_sp23.R;
import com.example.duan1nhom2_sp23.View.DanhSachHoaDonActivity;
import com.example.duan1nhom2_sp23.View.DanhSachPhongActivity;
import com.example.duan1nhom2_sp23.View.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CapNhatGiaDienNuocActivity extends AppCompatActivity {
    EditText edtGiaDien,edtGiaNuoc;
    Button btnCapNhat;
    SQLiteDatabase database;
    final String DATABASE_NAME = "QuanLyNhaTroNew.sqlite";
    FloatingActionButton ftbTrangChu, ftbHoaDon, ftbPhong, ftbBangGia, fbtLogout;
    boolean aBoolean = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_gia_dien_nuoc);
        AnhXa();
        setUI();
        ThaoTac();

    }



    private void setUI()
    {
        int ma = 1;
        database= Database.initDatabase(CapNhatGiaDienNuocActivity.this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("select * from BangGia where Ma = ?",new String[]{ma+""});
        cursor.moveToFirst();
        String giadien = cursor.getString(1);
        String gianuoc = cursor.getString(2);
        edtGiaDien.setText(giadien);
        edtGiaNuoc.setText(gianuoc);
    }
    private void SuaGia()
    {
        int ma =1;
        String giadien = edtGiaDien.getText().toString();
        String gianuoc = edtGiaNuoc.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("GiaDien",giadien);
        contentValues.put("GiaNuoc",gianuoc);

        SQLiteDatabase database = Database.initDatabase(CapNhatGiaDienNuocActivity.this,DATABASE_NAME);
        database.update("BangGia",contentValues,"Ma = ?",new String[]{ma+""});
        Intent intent = new Intent(CapNhatGiaDienNuocActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(CapNhatGiaDienNuocActivity.this,"Cập nhật giá thành công",Toast.LENGTH_LONG).show();
        finish();
    }
    private void ThaoTac()
    {
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaGia();
            }
        });
        ftbPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapNhatGiaDienNuocActivity.this, DanhSachPhongActivity.class);
                startActivity(intent);

            }
        });
        ftbBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapNhatGiaDienNuocActivity.this, CapNhatGiaDienNuocActivity.class);
                startActivity(intent);

            }
        });
        fbtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CapNhatGiaDienNuocActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        ftbHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapNhatGiaDienNuocActivity.this, DanhSachHoaDonActivity.class);
                startActivity(intent);

            }
        });

        ftbTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aBoolean) {
                    fbtLogout.show();
                    ftbPhong.show();
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
    }

    private void AnhXa() {
        edtGiaDien = (EditText) findViewById(R.id.edtgiadien);
        edtGiaNuoc = (EditText) findViewById(R.id.edtgianuoc);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhatDienNuoc);
       fbtLogout= (FloatingActionButton) findViewById(R.id.btnLogout);
        ftbTrangChu = findViewById(R.id.ftbTrangChu);
        ftbHoaDon = findViewById(R.id.ftbHoaDon);
        ftbPhong = findViewById(R.id.ftbPhong);
        ftbBangGia = findViewById(R.id.ftbBangGia);

    }}