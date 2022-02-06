package company.cell.com.Cellotto.Custom;

import android.app.Application;

import company.cell.com.Cellotto.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by lue on 01-08-2017.
 */

//public final class FontsActivity {
//
//
//    public static void setDefaultFont(Context context,
//                                      String staticTypefaceFieldName, String fontAssetName) {
//        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
//                fontAssetName);
//        replaceFont(staticTypefaceFieldName, regular);
//    }
//
//    protected static void replaceFont(String staticTypefaceFieldName,
//                                      final Typeface newTypeface) {
//        try {
//            final Field staticField = Typeface.class
//                    .getDeclaredField(staticTypefaceFieldName);
//            staticField.setAccessible(true);
//            staticField.set(null, newTypeface);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//}

  class FontsActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initalize Calligraphy
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("corbel.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    /**
     * Created by lue on 05-08-2017.
     */


}