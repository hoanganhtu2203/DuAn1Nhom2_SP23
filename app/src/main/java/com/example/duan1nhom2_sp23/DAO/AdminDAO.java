package com.example.duan1nhom2_sp23.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan1nhom2_sp23.DTO.AdminDTO;
import com.example.duan1nhom2_sp23.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    SQLiteDatabase database;
    public AdminDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemNhanVien(AdminDTO adminDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_ADMIN_HOTENADMIN,adminDTO.getHOTENADMIN());
        contentValues.put(CreateDatabase.TBL_ADMIN_TENDN,adminDTO.getTENDN());
        contentValues.put(CreateDatabase.TBL_ADMIN_MATKHAU,adminDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TBL_ADMIN_EMAIL,adminDTO.getEMAIL());
        contentValues.put(CreateDatabase.TBL_ADMIN_SDT,adminDTO.getSDT());
        contentValues.put(CreateDatabase.TBL_ADMIN_GIOITINH,adminDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TBL_ADMIN_NGAYSINH,adminDTO.getNGAYSINH());
        contentValues.put(CreateDatabase.TBL_ADMIN_MAQUYEN,adminDTO.getMAQUYEN());

        long ktra = database.insert(CreateDatabase.TBL_ADMIN,null,contentValues);
        return ktra;
    }

    public long SuaNhanVien(AdminDTO adminDTO,int maadmin){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_ADMIN_HOTENADMIN,adminDTO.getHOTENADMIN());
        contentValues.put(CreateDatabase.TBL_ADMIN_TENDN,adminDTO.getTENDN());
        contentValues.put(CreateDatabase.TBL_ADMIN_MATKHAU,adminDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TBL_ADMIN_EMAIL,adminDTO.getEMAIL());
        contentValues.put(CreateDatabase.TBL_ADMIN_SDT,adminDTO.getSDT());
        contentValues.put(CreateDatabase.TBL_ADMIN_GIOITINH,adminDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TBL_ADMIN_NGAYSINH,adminDTO.getNGAYSINH());
        contentValues.put(CreateDatabase.TBL_ADMIN_MAQUYEN,adminDTO.getMAQUYEN());

        long ktra = database.update(CreateDatabase.TBL_ADMIN,contentValues,
                CreateDatabase.TBL_ADMIN_MAADMIN+" = "+maadmin,null);
        return ktra;
    }

    @SuppressLint("Range")
    public int KiemTraDN(String tenDN, String matKhau){
        String query = "SELECT * FROM " +CreateDatabase.TBL_ADMIN+ " WHERE "
                +CreateDatabase.TBL_ADMIN_TENDN +" = '"+ tenDN+"' AND "+CreateDatabase.TBL_ADMIN_MATKHAU +" = '" +matKhau +"'";
        int maadmin = 0;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maadmin = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAADMIN)) ;
            cursor.moveToNext();
        }
        return maadmin;
    }

    public boolean KtraTonTaiNV(){
        String query = "SELECT * FROM "+CreateDatabase.TBL_ADMIN;
        Cursor cursor =database.rawQuery(query,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }

    @SuppressLint("Range")
    public List<AdminDTO> LayDSNV(){
        List<AdminDTO> adminDTOS = new ArrayList<AdminDTO>();
        String query = "SELECT * FROM "+CreateDatabase.TBL_ADMIN;

        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setHOTENADMIN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_HOTENADMIN)));
            adminDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_EMAIL)));
            adminDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_GIOITINH)));
            adminDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_NGAYSINH)));
            adminDTO.setSDT(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_SDT)));
            adminDTO.setTENDN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_TENDN)));
            adminDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MATKHAU)));
            adminDTO.setMAADMIN(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAADMIN)));
            adminDTO.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAQUYEN)));

            adminDTOS.add(adminDTO);
            cursor.moveToNext();
        }
        return adminDTOS;
    }

    public boolean XoaNV(int maadmin){
        long ktra = database.delete(CreateDatabase.TBL_ADMIN,CreateDatabase.TBL_ADMIN_MAADMIN+ " = " +maadmin
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }

    @SuppressLint("Range")
    public AdminDTO LayNVTheoMa(int maadmin){
        AdminDTO adminDTO = new AdminDTO();
        String query = "SELECT * FROM "+CreateDatabase.TBL_ADMIN+" WHERE "+CreateDatabase.TBL_ADMIN_MAADMIN+" = "+maadmin;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            adminDTO.setHOTENADMIN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_HOTENADMIN)));
            adminDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_EMAIL)));
            adminDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_GIOITINH)));
            adminDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_NGAYSINH)));
            adminDTO.setSDT(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_SDT)));
            adminDTO.setTENDN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_TENDN)));
            adminDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MATKHAU)));
            adminDTO.setMAADMIN(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAADMIN)));
            adminDTO.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAQUYEN)));

            cursor.moveToNext();
        }
        return adminDTO;
    }

    @SuppressLint("Range")
    public int LayQuyenNV(int manv){
        int maquyen = 0;
        String query = "SELECT * FROM "+CreateDatabase.TBL_ADMIN+" WHERE "+CreateDatabase.TBL_ADMIN_MAADMIN+" = "+manv;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maquyen = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_ADMIN_MAQUYEN));

            cursor.moveToNext();
        }
        return maquyen;
    }
}
