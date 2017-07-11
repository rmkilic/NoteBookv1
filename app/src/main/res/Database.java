import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mümin on 11.7.2017.
 */

public class Database extends SQLiteOpenHelper {

    private  static  final  int DATABASE_VERSION=1;
    private  static final  String DATABASE_NAME="db_Notebook";
    private  static  final String TABLE_NAME="Notlar";

    private static   String ID="id";
    private  static  String Konu="Konu";
    private  static  String Icerik="Icerik";
}

public  void onCreate(SQLiteDatabase db)
{
    String Create_Table="Create table "+TABLE_NAME+ "("
            +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +Konu+" TEXT"
            +Icerik+" TEXT )";

    db.execSQL(Create_Table);

}
public void Not_Ekle(String konu,String icerik)
{
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(Konu,konu);
    values.put(Icerik,icerik);

    db.insert(TABLE_NAME,null,values);
    db.close();
}
public ArrayList<<HashMap<String,String>> kayitlar()
        {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectquery=" Select * from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(selectquery,null);
        ArrayList<<HashMap<String,String>> kayitlar =new ArrayList<~>();

        if(cursor.moveToFirst())
        {
        do
        {HashMap<String,String> map = new HashMap<~>();
        for(i=0;i<cursor.getColumnCount();i++)
        {
        map.put(cursor.getColumnName(i),cursor.getString(i));

        }
        kayitlar.add(map);

        }while(cursor.moveToNext())

        }
        db.Close();
        return kayitlar;

        }