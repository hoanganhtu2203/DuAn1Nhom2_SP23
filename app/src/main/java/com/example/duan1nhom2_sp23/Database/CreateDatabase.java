package com.example.duan1nhom2_sp23.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static String TBL_ADMIN = "NHANVIEN";
    public static String TBL_QUYEN = "QUYEN";

    //Bảng nhân viên
    public static String TBL_ADMIN_MAADMIN = "MAADMIN";
    public static String TBL_ADMIN_HOTENADMIN = "HOTENADMIN";
    public static String TBL_ADMIN_TENDN = "TENDN";
    public static String TBL_ADMIN_MATKHAU = "MATKHAU";
    public static String TBL_ADMIN_EMAIL = "EMAIL";
    public static String TBL_ADMIN_SDT = "SDT";
    public static String TBL_ADMIN_GIOITINH = "GIOITINH";
    public static String TBL_ADMIN_NGAYSINH = "NGAYSINH";
    public static String TBL_ADMIN_MAQUYEN= "MAQUYEN";

    //Bảng quyền
    public static String TBL_QUYEN_MAQUYEN = "MAQUYEN";
    public static String TBL_QUYEN_TENQUYEN = "TENQUYEN";

    public CreateDatabase(Context context) {
        super(context, "DuAn1Nhom2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblADMIN = "CREATE TABLE " +TBL_ADMIN+ " ( " +TBL_ADMIN_MAADMIN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TBL_ADMIN_HOTENADMIN+ " TEXT, " +TBL_ADMIN_TENDN+ " TEXT, " +TBL_ADMIN_MATKHAU+ " TEXT, " +TBL_ADMIN_EMAIL+ " TEXT, "
                +TBL_ADMIN_SDT+ " TEXT, " +TBL_ADMIN_GIOITINH+ " TEXT, " +TBL_ADMIN_NGAYSINH+ " TEXT , "+TBL_ADMIN_MAQUYEN+" INTEGER)";

        String tblQUYEN = "CREATE TABLE " +TBL_QUYEN+ " ( " +TBL_QUYEN_MAQUYEN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TBL_QUYEN_TENQUYEN+ " TEXT)" ;
        db.execSQL(tblADMIN);
        db.execSQL(tblQUYEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
