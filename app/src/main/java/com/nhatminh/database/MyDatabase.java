package com.nhatminh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nhatminh.note.Times;
import com.nhatminh.playgame.Level;
import com.nhatminh.playgame.Player;
import com.nhatminh.playgame.WordsF;
import com.nhatminh.training.Lessons;
import com.nhatminh.training.Training;
import com.nhatminh.training.Words;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by caonh on 01/10/2016.
 */
public class MyDatabase extends SQLiteOpenHelper {

    // Ten co so du lieu cua minh
    public static final String databaseName = "Toeic";

    // duong dan co so du lieu
    // com.example.ten_project doi lai ten packet cua minh
    public static String DB_PATH = "/data/data/com.nhatminh.vocabulary/databases/";

    // ten file co so du lieu - viet lai cho giong voi minh
    private static String DB_NAME = "Toeic.sqlite";
    private SQLiteDatabase database;
    private final Context mContext;

    public MyDatabase(Context con) {
        super(con, DB_NAME, null, 1);
        this.mContext = con;
    }


    public boolean isCreatedDatabase() throws IOException {
        // Default là đã có DB
        boolean result = true;
        // Nếu chưa tồn tại DB thì copy từ Asses vào Data
        if (!checkExistDataBase()) {
            this.getReadableDatabase();
            try {
                copyDataBase();
                result = false;
            } catch (Exception e) {
                throw new Error("Error copying database");
            }
        }

        return result;
    }


    private boolean checkExistDataBase() {

        try {
            String myPath = DB_PATH + DB_NAME;
            File fileDB = new File(myPath);

            if (fileDB.exists()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }


    private void copyDataBase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    public boolean deleteDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        return file.delete();
    }


    public void openDataBase() throws SQLException {
        database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // do nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing
    }

    public int deleteData_From_Table(String tbName) {

        int result = 0;
        try {
            openDataBase();
            database.beginTransaction();
            result = database.delete(tbName, null, null);
            if (result >= 0) {
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            database.endTransaction();
            close();
        } finally {
            database.endTransaction();
            close();
        }

        return result;
    }
    public ArrayList<Lessons> getListLessons()
    {
        String s = "select * from tblLesson";
        ArrayList<Lessons> ls = new ArrayList<Lessons>();

        Cursor c = database.rawQuery(s, null);
        int maBH = c.getColumnIndex("maBH");
        int tenBHA = c.getColumnIndex("tenBHA");
        int tenBHV = c.getColumnIndex("tenBHV");
        while(c.moveToNext())
        {
            int mabh = c.getInt(maBH);
            String tenbha = c.getString(tenBHA);
            String tenbhv = c.getString(tenBHV);
            Lessons l = new Lessons(mabh,tenbha,tenbhv);
            ls.add(l);
        }
        c.close();
        return ls;
    }

    public ArrayList<Level> getListLevel()
    {
        String s = "select * from tblLesson";
        ArrayList<Level> ls = new ArrayList<Level>();

        Cursor c = database.rawQuery(s, null);
        int maBH = c.getColumnIndex("maBH");
        int tenBHA = c.getColumnIndex("tenBHA");
        int tenBHV = c.getColumnIndex("tenBHV");
        while(c.moveToNext())
        {
            int mabh = c.getInt(maBH);
            String tenbha = c.getString(tenBHA);
            String tenbhv = c.getString(tenBHV);
            Lessons l = new Lessons(mabh,tenbha,tenbhv);
            Level level = new Level((l));
            ls.add(level);
        }
        c.close();
        return ls;
    }

    public ArrayList<Words> getListWords(String mabaihoc)
    {
        String s = "select * from tblWords where baihoc = '"+mabaihoc+"'  " ;
        ArrayList<Words> ls = new ArrayList<Words>();

        Cursor c = database.rawQuery(s,null);
        int iD = c.getColumnIndex("id");
        int tenTV = c.getColumnIndex("tenTV");
        int nghiaTA = c.getColumnIndex("nghiaTA");
        int nghiaTV = c.getColumnIndex("nghiaDung");
        int viDu = c.getColumnIndex("vidu");
        while(c.moveToNext())
        {
            int id = c.getInt(iD);
            String tentv = c.getString(tenTV);
            String nghiata = c.getString(nghiaTA);
            String nghiatv = c.getString(nghiaTV);
            String vidu = c.getString(viDu);
            Words w = new Words(id,tentv,nghiata,nghiatv,vidu);
            ls.add(w);
        }
        c.close();
        return ls;
    }
    public ArrayList<WordsF> getListFalseWords(int mabaihoc)
    {
        String key = getMaMH(mabaihoc);
        String s = "select * from tblWords where baihoc = '"+key+"'  " ;
        ArrayList<WordsF> ls = new ArrayList<WordsF>();

        Cursor c = database.rawQuery(s,null);
        int iD = c.getColumnIndex("id");
        int tenTV = c.getColumnIndex("tenTV");
        int nghiaTA = c.getColumnIndex("nghiaTA");
        int nghiaTV = c.getColumnIndex("nghiaDung");
        int nghia1 = c.getColumnIndex("nghiaSai1");
        int nghia2 = c.getColumnIndex("nghiaSai2");
        int nghia3 = c.getColumnIndex("nghiaSai3");
        int nghia4 = c.getColumnIndex("nghiaSai4");
        int nghia5 = c.getColumnIndex("nghiaSai5");
        int nghia6 = c.getColumnIndex("nghiaSai6");
        int nghia7 = c.getColumnIndex("nghiaSai7");
        int nghia8 = c.getColumnIndex("nghiaSai8");
        int nghia9 = c.getColumnIndex("nghiaSai9");
        int nghia10 = c.getColumnIndex("nghiaSai10");
        int viDu = c.getColumnIndex("vidu");
        while(c.moveToNext())
        {
            int id = c.getInt(iD);
            String tentv = c.getString(tenTV);
            String nghiata = c.getString(nghiaTA);
            String nghiatv = c.getString(nghiaTV);
            String nghiaS2 = c.getString(nghia2);
            String nghiaS3 = c.getString(nghia3);
            String nghiaS4 = c.getString(nghia4);
            String nghiaS5 = c.getString(nghia5);
            String nghiaS6 = c.getString(nghia6);
            String nghiaS7 = c.getString(nghia7);
            String nghiaS8 = c.getString(nghia8);
            String nghiaS9 = c.getString(nghia9);
            String nghiaS1 = c.getString(nghia1);
            String nghiaS10 = c.getString(nghia10);
            String vidu = c.getString(viDu);
            WordsF w = new WordsF(id,tentv,nghiatv,nghiaS1,nghiaS2,nghiaS3,nghiaS4,nghiaS5,nghiaS6,nghiaS7,nghiaS8,nghiaS9,nghiaS10);

            ls.add(w);
        }
        c.close();
        return ls;
    }
    public String getMaMH(int id){
        if ( id < 10)
        {
            return "L0" + id;

        }
        else
            return "L" + id;
    }

    public void addToFavorite(Words w)
    {
        ContentValues c = new ContentValues();
        c.put("id",w.getId());
        c.put("tenTV",w.getTenTV());
        c.put("nghiaDung",w.getNghiadung());
        c.put("viDu",w.getVidu());
        database.insert("tblFavorite", null, c);
    }
    public ArrayList<Words> getListFavorite()
    {
        String s = "select * from tblFavorite " ;
        ArrayList<Words> ls = new ArrayList<Words>();

        Cursor c = database.rawQuery(s, null);
        int iD = c.getColumnIndex("id");
        int tenTV = c.getColumnIndex("tenTV");
        int nghiaTV = c.getColumnIndex("nghiaDung");
        int viDu = c.getColumnIndex("viDu");
        while(c.moveToNext())
        {
            int id = c.getInt(iD);
            String tentv = c.getString(tenTV);
            String nghiatv = c.getString(nghiaTV);
            String vidu = c.getString(viDu);
            Words w = new Words(id,tentv,nghiatv,vidu);
            ls.add(w);
        }
        c.close();
        return ls;
    }

    public Words getRandomWord()
    {
        Words wo = new Words();
        try {
            ArrayList<Words> list = getListFavorite();

            int size = list.size();
            Random r = new Random();
            int n = r.nextInt(size);

            Words w = list.get(n);

            return w;
        }
        catch (Exception e)
        {
            return wo;
        }
    }
    public void deleteFavorite(int id)
    {
        database.delete("tblFavorite", "id=?", new String[]{"" + id});

    }

    public Times getTimes()
    {
        Times t = new Times();
        String s = "select * from tblSettings where id = '1' ";
        Cursor c = database.rawQuery(s,null);
        if(c.moveToFirst())
        {
            int onoff = c.getColumnIndex("onoff");
            int starth = c.getColumnIndex("starth");
            int endh = c.getColumnIndex("endh");
            int startm = c.getColumnIndex("startm");
            int endm = c.getColumnIndex("endm");
            int mode = c.getColumnIndex("mode");

            int onofff = c.getInt(onoff);
            int startho = c.getInt(starth);
            int endho = c.getInt(endh);
            int startmi = c.getInt(startm);
            int endmi = c.getInt(endm);
            int modes = c.getInt(mode);

            t.setOnoff(onofff);
            t.setStarth(startho);
            t.setEndh(endho);
            t.setStartm(startmi);
            t.setEndm(endmi);
            t.setMode(modes);
        }
        c.close();
        return t;
    }
    public void updateTimes(ContentValues ct)
    {
        database.update("tblSettings", ct, "id = ?", new String[]{"1"});
    }

    public void AddPlayer(Player p)
    {
        ContentValues c = new ContentValues();
        c.put("name",p.getName());
        c.put("score",p.getScore());
        database.insert("tblPlayer",null,c);
    }
    public ArrayList<Player> TopPlayer()
    {
        ArrayList<Player> player = new ArrayList<Player>();
        String s = "SELECT * FROM tblPlayer order by score desc limit 10";
        Cursor c = database.rawQuery(s,null);

        int nname = c.getColumnIndex("name");
        int nscore = c.getColumnIndex("score");

        while (c.moveToNext())
        {
            String ten = c.getString(nname);
            int diem = c.getInt(nscore);
            Player p = new Player(ten,diem);
            player.add(p);
        }
        c.close();
        return player;
    }
    public int BestScore()
    {
        ArrayList<Player> p = TopPlayer();
        if(p.size()>0)
            return p.get(0).getScore();
        else
            return  0;
    }
    public int MinScore()
    {
        ArrayList<Player> p = TopPlayer();
        int i = p.size();
        if(i>=10)
            return p.get(i-1).getScore();
        else
            return  0;
    }
}