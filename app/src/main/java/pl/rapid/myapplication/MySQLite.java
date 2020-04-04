package pl.rapid.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public MySQLite(@Nullable Context context) {
        super(context, "animalsDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE =
                "create table animals " +
                        "(_id integer primary key autoincrement," +
                        "species text not null," +
                        "color text not null," +
                        "size real not null," +
                        "description text not null);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS animals");
        onCreate(db);
    }

    public void add(Animal animal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("species", animal.getSpecies());
        values.put("color", animal.getColor());
        values.put("size", animal.getSize());
        values.put("description", animal.getDescription());
        db.insert("animals", null, values);
        db.close();
    }

    public void delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("animals", "_id = ?", new String[]{id});
        db.close();
    }

    public int update(Animal animal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("species", animal.getSpecies());
        values.put("color", animal.getColor());
        values.put("size", animal.getSize());
        values.put("description", animal.getDescription());

        int i = db.update("animals", values, "_id = ?", new String[]{String.valueOf(animal.getId())});
        db.close();

        return i;
    }

    public Animal getAnimal(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query("animals", //a. table name
                        new String[]{"_id", "species", "color", "size", "description"}, // b.column names
                        "_id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Animal animal = new Animal(cursor.getString(1), cursor.getString(2),
                cursor.getFloat(3), cursor.getString(4));
        animal.setId(Integer.parseInt(cursor.getString(0)));
        return animal;
    }

    public Cursor list(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from animals",null);
    }

}
