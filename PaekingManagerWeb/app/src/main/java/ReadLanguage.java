import android.content.Context;
import android.database.Cursor;

import com.tamtoanthang.apps.mobileparking.DataBase.LanguageDatabase;

/**
 * Created by lue on 21-10-2016.
 */
public class ReadLanguage {
    public static String read(Context ctx)
    {
        String language="";
        LanguageDatabase languageDatabase=new LanguageDatabase(ctx);
        languageDatabase.open();
        Cursor cu=languageDatabase.returnall();
        if(cu.moveToFirst())
        {
            do {
                language=cu.getString(0);
            }
            while (cu.moveToNext());
        }
        languageDatabase.close();
        return language;
    }
}
