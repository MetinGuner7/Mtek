package com.mghg.mtek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mghg.mtek.models.Profil;


public class Veritabani extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "database2";

    private static final String TABLE_NAME = "profil_listesi";
    private static String KULLANICIADI = "kullanici_adi";
    private static String SIFRE = "sifre";
    private static String ISIM = "isim";
    private static String SOYISIM = "soyisim";
    private static String EMAIL = "email";

    public Veritabani(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Databesi oluşturuyoruz.Bu methodu biz çağırmıyoruz. Databese de obje oluşturduğumuzda otamatik çağırılıyor.
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KULLANICIADI + " TEXT,"
                + SIFRE + " TEXT,"
                + ISIM + " TEXT,"
                + SOYISIM + " TEXT,"
                + EMAIL + "TEXT" + ")";

        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+KULLANICIADI+" TEXT,"+ SIFRE +" TEXT,"+ ISIM+" TEXT,"+ SOYISIM+" TEXT,"+ EMAIL+" TEXT);");
        //db.execSQL(CREATE_TABLE);
    }


    public void profilKayitOl(String kullanici, String sifre) {
        //Database yeni veri eklemek
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues yeniProfil = new ContentValues();
        yeniProfil.put(KULLANICIADI, kullanici);
        yeniProfil.put(SIFRE, sifre);
        yeniProfil.put(ISIM, "bos");
        yeniProfil.put(SOYISIM, "bos");
        yeniProfil.put(EMAIL, "bos");

        db.insertOrThrow(TABLE_NAME, null, yeniProfil);
        db.close(); //Database Bağlantısını kapattık*/
    }

    public void profilBilgilerGir(String isim, String soyisim, String email) {
        //Database yeni veri eklemek
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues yeniProfil = new ContentValues();
        yeniProfil.put(KULLANICIADI, "kullanici");
        yeniProfil.put(SIFRE, "sifre");
        yeniProfil.put(ISIM, isim);
        yeniProfil.put(SOYISIM, soyisim);
        yeniProfil.put(EMAIL, email);

        db.insertOrThrow(TABLE_NAME, null, yeniProfil);
        db.close(); //Database Bağlantısını kapattık*/
    }

    public Profil kullaniciBilgileriniGetir(String gelenKullaniciAdi) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sorgu = "SELECT * FROM " + TABLE_NAME + " WHERE " + KULLANICIADI + "= ?";
        Cursor cursor = db.rawQuery(sorgu, new String[]{gelenKullaniciAdi});
        Profil yeniProfil= new Profil(gelenKullaniciAdi,"sifre","isim","soyisim","email");

        if (cursor.moveToFirst()) {
            //cursor içinde soru varsa içeri girip gelen üye adı daha önce kayıtlanmış mı diye bakıyor.
            do {
                yeniProfil.setKullaniciAdi(cursor.getString(0));
                yeniProfil.setSifre(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        db.close();

        return yeniProfil;

    }


    public Profil kullaniciKaydiVarMi(String gelenKullaniciAdi, String gelenSifre) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sorgu = "SELECT * FROM " + TABLE_NAME + " WHERE " + KULLANICIADI + "= ?";
        Cursor cursor = db.rawQuery(sorgu, new String[]{gelenKullaniciAdi});
        boolean kontrol = false;
        Profil kontrolEdilenUye = new Profil(gelenKullaniciAdi, gelenSifre, null, null, null);

        if (cursor.moveToFirst()) {
            //cursor içinde soru varsa içeri girip soru sınıfından türettiğimiz nesneyi dolduruyor.
            do {
                if (kontrolEdilenUye.getKullaniciAdi().equalsIgnoreCase(cursor.getString(0)) &
                        kontrolEdilenUye.getSifre().equalsIgnoreCase(cursor.getString(1))) {
                    kontrol = true;
                } else {
                    kontrol = false;
                }
            } while (cursor.moveToNext());
        }
        db.close();

        if (kontrol) {
            return kontrolEdilenUye;
        } else {
            return null;
        }

    }

    public boolean kullaniciAdiBosMu(String gelenKullaniciAdi) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sorgu = "SELECT * FROM " + TABLE_NAME + " WHERE " + KULLANICIADI + "= ?";
        Cursor cursor = db.rawQuery(sorgu, new String[]{gelenKullaniciAdi});
        boolean kontrol = false;

        if (cursor.moveToFirst()) {
            //cursor içinde soru varsa içeri girip gelen üye adı daha önce kayıtlanmış mı diye bakıyor.
            do {
                if (gelenKullaniciAdi.equals(cursor.getColumnName(1))){
                    kontrol = true;
                }
            } while (cursor.moveToNext());
        }
        db.close();

        return kontrol;

    }

    public int getRowCount() {
        // Bu method bu uygulamada kullanılmıyor ama her zaman lazım olabilir.Tablodaki row sayısını geri döner.
        //Login uygulamasında kullanacağız
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // return row count
        return rowCount;
    }

    public void resetTables() {
        //Bunuda uygulamada kullanmıyoruz. Tüm verileri siler. tabloyu resetler.
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        onCreate(db);
    }
}

