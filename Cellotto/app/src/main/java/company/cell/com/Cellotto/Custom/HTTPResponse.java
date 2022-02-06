package company.cell.com.Cellotto.Custom;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Fujitsu on 24-07-2017.
 */

public class HTTPResponse {
    public static String readResponse(org.apache.http.HttpResponse res) {
        InputStream is=null;
        String return_text="";
        try {
            is=res.getEntity().getContent();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
            String line="";
            StringBuffer sb=new StringBuffer();
            while ((line=bufferedReader.readLine())!=null)
            {
                sb.append(line);
            }
            return_text=sb.toString();
        } catch (Exception e)
        {

        }
        return return_text;

    }
}
