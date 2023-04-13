package com.example.duan1nhom2_sp23.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1nhom2_sp23.Action.CapNhatGiaDienNuocActivity;
import com.example.duan1nhom2_sp23.Adapter.AdapterHoaDon;
import com.example.duan1nhom2_sp23.Database.Database;
import com.example.duan1nhom2_sp23.Model.ChiTietHoaDon;
import com.example.duan1nhom2_sp23.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class DanhSachHoaDonActivity extends AppCompatActivity {

    final String DATABASE_NAME = "QuanLyNhaTroNew.sqlite";
    SQLiteDatabase database;
    ListView lsvDanhSachHoaDon;
    ArrayList<ChiTietHoaDon> list;
    AdapterHoaDon adapter;
    Spinner spnThangHoaDon, spnNamHoaDon;
    int maphong = -1;
    int position = 0;
    Calendar cal = Calendar.getInstance();

    int day = cal.get(Calendar.DAY_OF_MONTH);
    int month = cal.get(Calendar.MONTH) + 1;
    int year = cal.get(Calendar.YEAR);

    FloatingActionButton ftbTrangChu, ftbHoaDon, ftbPhong, ftbBangGia;
    boolean aBoolean = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hoa_don);
        addControl();
        AnhXa();
        readData();
        thaotac();
    }

    private void AnhXa()
    {
        ftbTrangChu = findViewById(R.id.ftbTrangChu);
        ftbHoaDon = findViewById(R.id.ftbHoaDon);
        ftbPhong = findViewById(R.id.ftbPhong);
        ftbBangGia = findViewById(R.id.ftbBangGia);


    }


    private void readData()
    {
        String dk = "01/"+spnThangHoaDon.getSelectedItem() + "/" + spnNamHoaDon.getSelectedItem();
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("select * from ChiTietHoaDon where NgayLapHoaDon = ?", new String[]{dk+""});
        list.clear();

        for (int i = 0; i < cursor.getCount(); i++) {
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
            list.add(new ChiTietHoaDon(maCTHD, tienPhong, tienDien, tineNuoc, tienInternet,
                    tienDVKhac, lyDoThu, ngayLapHoaDOn, trangThai, maPhong, tongTien, soDien, soNuoc));
        }
        adapter.notifyDataSetChanged();
    }

    private void addControl() {
        lsvDanhSachHoaDon = (ListView) findViewById(R.id.lsvDanhSachHoaDon);
        list = new ArrayList<>();
        adapter = new AdapterHoaDon(DanhSachHoaDonActivity.this, list);
        lsvDanhSachHoaDon.setAdapter(adapter);

        spnThangHoaDon = findViewById(R.id.spnThangHoaDon);
        ArrayList<Integer> arrThang = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            arrThang.add(i);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrThang);
        spnThangHoaDon.setAdapter(arrayAdapter);
        spnThangHoaDon.setSelection(arrayAdapter.getPosition(month));

        spnNamHoaDon = findViewById(R.id.spnNamHoaDon);
        ArrayList<Integer> arrNam = new ArrayList<>();
        for (int i = year; i > 2022; i--) {
            arrNam.add(i);
        }
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrNam);
        spnNamHoaDon.setAdapter(arrayAdapter1);
        spnNamHoaDon.setSelection(arrayAdapter1.getPosition(year));

        Button btnTim = (Button) findViewById(R.id.btnTim);
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XemPhong();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hoadon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_hoadon:
                final AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachHoaDonActivity.this);
                final String[] list1 = {"Tất cả", "Đã thu", "Chưa thu"};
                builder.setTitle("Tìm kiếm");
                builder.setSingleChoiceItems(list1, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        position = i;
                        if (position == 0) {
                            readData();

                        } else if (position == 1)
                        {
                            String dk = "01/"+spnThangHoaDon.getSelectedItem() + "/" + spnNamHoaDon.getSelectedItem();
                            database = Database.initDatabase(DanhSachHoaDonActivity.this, DATABASE_NAME);
                            Cursor cursor =
                                    database.rawQuery("select * from ChiTietHoaDon where TrangThai = ? and NgayLapHoaDon = ?",
                                            new String[]{1 + "",dk + ""});
                            list.clear();

                            for (int m = 0; m < cursor.getCount(); m++) {
                                cursor.moveToPosition(m);
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
                                list.add(new ChiTietHoaDon(maCTHD, tienPhong, tienDien, tineNuoc, tienInternet,
                                        tienDVKhac, lyDoThu, ngayLapHoaDOn, trangThai, maPhong, tongTien, soDien, soNuoc));
                            }
                            adapter.notifyDataSetChanged();

                        } else
                            {
                                String dk = "01/"+spnThangHoaDon.getSelectedItem() + "/" + spnNamHoaDon.getSelectedItem();
                            database = Database.initDatabase(DanhSachHoaDonActivity.this, DATABASE_NAME);
                            Cursor cursor = database.rawQuery("select * from ChiTietHoaDon where TrangThai = ? and NgayLapHoaDon = ?", new String[]{0 + "",dk+""});
                            list.clear();

                            for (int m = 0; m < cursor.getCount(); m++) {
                                cursor.moveToPosition(m);
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
                                list.add(new ChiTietHoaDon(maCTHD, tienPhong, tienDien, tineNuoc, tienInternet,
                                        tienDVKhac, lyDoThu, ngayLapHoaDOn, trangThai, maPhong, tongTien, soDien, soNuoc));
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
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            case R.id.action_Themhoadon:
                Intent intent = new Intent(DanhSachHoaDonActivity.this, DanhSachPhong_HoaDonActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void XemPhong()
    {
        String dk = "01/"+spnThangHoaDon.getSelectedItem() + "/" + spnNamHoaDon.getSelectedItem();
        database = Database.initDatabase(DanhSachHoaDonActivity.this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("select * from ChiTietHoaDon where NgayLapHoaDon = ?",new String[]{dk+""});
        list.clear();
        for (int m = 0; m < cursor.getCount(); m++) {
            cursor.moveToPosition(m);
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
            list.add(new ChiTietHoaDon(maCTHD, tienPhong, tienDien, tineNuoc, tienInternet,
                    tienDVKhac, lyDoThu, ngayLapHoaDOn, trangThai, maPhong, tongTien, soDien, soNuoc));
        }
        adapter.notifyDataSetChanged();

    }




    private void thaotac() {
        ftbTrangChu = findViewById(R.id.ftbTrangChu);
        ftbHoaDon = findViewById(R.id.ftbHoaDon);
        ftbPhong = findViewById(R.id.ftbPhong);
        ftbBangGia = findViewById(R.id.ftbBangGia);

        ftbPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachHoaDonActivity.this, DanhSachPhongActivity.class);
                startActivity(intent);

            }
        });
        ftbBangGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachHoaDonActivity.this, CapNhatGiaDienNuocActivity.class);
                startActivity(intent);

            }
        });
        ftbHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachHoaDonActivity.this, DanhSachHoaDonActivity.class);
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

