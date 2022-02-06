//package com.tamtoanthang.apps.mobileparking.DialogFragment;
//
//import android.app.Activity;
//import android.app.DialogFragment;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.tamtoanthang.apps.mobileparking.DataBase.DatabaseHelper;
//import com.tamtoanthang.apps.mobileparking.DataBase.SQLiteHelper;
//import com.tamtoanthang.apps.mobileparking.R;
//import com.tamtoanthang.apps.mobileparking.SecondActivity;
//import com.tamtoanthang.apps.mobileparking.java.HTTPResponse;
//
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static android.content.Context.MODE_PRIVATE;
//import static com.tamtoanthang.apps.mobileparking.SecondActivity.galleryimage;
//
//
//public class UpdateFragment extends DialogFragment {
//
//    Button bt1;
//    EditText ed1;
//    String img="http://";
//    SQLiteHelper sQLiteHelper;
//    SQLiteDatabase database;
//    private SecondActivity mListener;
//    private Bitmap bitmap;
//    String hc = "";
//    String cardprice="";
//    Bundle bundle = new Bundle();
//    byte[]image=null;
//    private int position = 0;
//    private int position1=0;
//    String dhg, dhg2, dfragment;
//    String n;
//    String SessionUser,SessionLogin,CardId,Baseimage,CardPrice,InImage,Status="",SessionStatus,UserId,AdminId,BaseUrl;
//    String CardNo;
//    String In_time;
//    String TextIntent=null;
//    public static final String MyPREFERENCES ="MyPrefrence1" ;
//    public static final String MyPREFERENCES2 ="MyPrefrence2" ;
//    public static final String Statusman ="statm" ;
//    SharedPreferences pref,Bu;
//    SharedPreferences pref2;
//    SharedPreferences prefstatus;
//    public static DatabaseHelper dbHelper;
//    public static Cursor cursor;
//    TextView price;
//    String v="";
//    static List<String> vaa=new ArrayList<>();
//    static List<String> c=new ArrayList<>();
//    public static String v2="";
//    public double sum =0;
//    public static double sumprice=0;
//    public static  String vcardno;
//    private static final String TAG = "CameraDemo";
//    String numb1="";
//    byte[]imageValue;
//    public static File pictureFile1;
//    public  static String filename;
//    public static String filenameInsert;
//    @Override
//    public void onAttach(Activity activity) {
//        mListener = (SecondActivity) activity;
//        super.onAttach(activity);
//
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.dragment_two, container, false);
//
//
//        getDialog().getActionBar();
////        dbHelper = new DatabaseHelper(getActivity());
////        sQLiteHelper = new SQLiteHelper( getActivity());
//        ed1=(EditText)rootView.findViewById(R.id.InputId) ;
//        pref=getActivity().getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
//        SessionUser=pref.getString("user_name","");
//        System.out.println("=========SessionUser================"+SessionUser);
//        SessionLogin=pref.getString("login_time","");
//        System.out.println("=========SessionLogin================"+SessionLogin);
//        UserId=pref.getString("user_id","");
//        AdminId=pref.getString("adminId","");
//        pref2=getActivity().getSharedPreferences(MyPREFERENCES2, MODE_PRIVATE);
//        CardId=pref.getString("card_id","");
//
//        Bu = getActivity().getSharedPreferences("Base", Context.MODE_PRIVATE);
//        BaseUrl = Bu.getString("baseurl", "");
//
//
//
//        bundle=getArguments();
//        if (bundle!= null) {
////             TextIntent = bundle.getString("link");
//            imageValue=bundle.getByteArray("imagevalue");
////            ed1.setText(TextIntent);
//        }else {
//            ed1.getText().toString();
//            String TextUser=ed1.getText().toString();
//        }
//
//
//        bt1=(Button)rootView.findViewById(R.id.buttonok);
//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                CardNo=ed1.getText().toString();
////                image = SecondActivity.imagevalue;
//
//
//                prefstatus=getActivity().getSharedPreferences(Statusman,Context.MODE_PRIVATE);
//                SessionStatus=prefstatus.getString("stat","");
//
//
//                Register(SessionUser,UserId,AdminId, SessionLogin, CardNo, In_time, Baseimage);
//
//
//
//
//
//                dismiss();
//
//            }
//
//
//        });
//
//
//        return rootView;
//
//    }
//
//
//
//
//
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState); getDialog().setTitle("Choose Attachment");
//
//
//    }
//
////    public void Insert() {
////        try {
////            ArrayList<ContactModel> contact2 = sQLiteHelper.getAllRecordsUser();
////            for(int z = 0; z < contact2.size(); z++) {
////                v = contact2.get(z).getUserName();
////            }
////            ArrayList<ContactModel> contact15 = sQLiteHelper.getAllRecordsAccordingCardNo(dhg2);
////            for (int i = 0; i < contact15.size(); i++) {
////                if(dhg2.equals(contact15.get(i).getCardNo())) {
////                    numb1 = contact15.get(i).getCardId();
////                    SecondActivity.id.setText(numb1);
////                    Log.d("card_id_", "" + numb1);
////                }
////            }
////                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
////                String string = dateFormat.format(new Date());
////                String Id = ed1.getText().toString();
////                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blankimage);
////                ByteArrayOutputStream bos = new ByteArrayOutputStream();
////                bitmap.compress(Bitmap.CompressFormat.JPEG,40 , bos);
////                byte[] bitmapdata = bos.toByteArray();
////            File pictureFileDir1 =getDir1();
////            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
////            String date1 = dateFormat1.format(new Date());
////            String photoFile = "in_"+numb1+"_"+ date1 + ".jpg";
////            filenameInsert = pictureFileDir1.getPath() + File.separator + photoFile;
////
////            pictureFile1 = new File(filenameInsert);
////            if (!pictureFileDir1.exists() && !pictureFileDir1.mkdirs()) {
////                Toast.makeText(getActivity(), getString(R.string.Cantcreatedirectorytosaveimage),
////                        Toast.LENGTH_LONG).show();
////                return;
////
////            }
////
////            try {
////                FileOutputStream fos1 = new FileOutputStream(pictureFile1);
////
////
////                fos1.write(image);
////                fos1.close();
////            }catch (Exception e){
////                e.printStackTrace();
////            }
////                dbHelper.inserRecord(new Contact(numb1, cardprice, image, bitmapdata, "1", string,"",dhg2,v,filenameInsert,filename));
////            ArrayList<ContactModel> contact11 = sQLiteHelper.getAllRecordsUser4();
////            if (contact11.size() == 0) {
////
////                        ContactModel contact1 = new ContactModel();
////                        contact1.setUserName(v);
////                        contact1.setUserINTime(DFragmentUser.userintime);
////                        contact1.setCardIdNo(dhg2);
////
////                        contact1.setPrice(cardprice);
////                        contact1.setUsserOutTime("");
////                vaa.add(cardprice);
////                c.add(dhg2);
////
////                        sQLiteHelper.insertRecordUser4(contact1);
////
////                vaa.clear();
////                c.clear();
////                SecondActivity.sumpriceActivity=0;
////                sumprice=0;
////                SecondActivity.v2= String.valueOf(0);
////                v2= String.valueOf(0);
////                vaa.add(cardprice);
////                c.add(dhg2);
////                v2 = String.valueOf(c.size());
////
////                double sum =0;
////                for (int p = 0; p < vaa.size(); p++) {
////                    sum +=  (Double.valueOf(vaa.get(p)));
////
////
////                }
////
////                sumprice=sum;
////
////                    } else {
////
////                        boolean isNotUpdated = true;
////
////                        for (int a = 0; a < contact11.size(); a++) {
////                            String x = contact11.get(a).getUserINTime();
////                            String y =contact11.get(a).getUsserOutTime();
////
////                            if ((DFragmentUser.userintime.equals(x))&&y.equals("")) {
////                                position1 = a;
////
////                                c.add(dhg2);
////                                vaa.add(cardprice);
////
////                                v2 = String.valueOf(c.size());
////
////
////                               for (int p = 0; p < vaa.size(); p++) {
////                                sum +=  (Double.valueOf(vaa.get(p)));
////
////
////                                }
////
////                                sumprice=sum;
////                                ContactModel contact23 = contact11.get(position1);
////                                int dh=0 ;
////                                if(SecondActivity.v2.equals(""))
////                                {
////                                    SecondActivity.v2= String.valueOf(0);
////                                }if(v2.equals("")){
////                                    v2= String.valueOf(0);
////                                }
////                                dh= (Integer.parseInt(v2) + Integer.parseInt(SecondActivity.v2));
////                                contact23.setCardIdNo(String.valueOf(dh));
////
////                                contact23.setPrice(String.valueOf(SecondActivity.sumpriceActivity+sumprice));
////                                contact23.setUsserOutTime("");
////                                sQLiteHelper.updateRecordUser4(contact23);
////
////                                isNotUpdated = false;
////
////                                break;
////                            } else {
////                                Log.d("else part","working");
////                                continue;
////                            }
////
////                        }
////
////                        if (isNotUpdated) {
////                            Log.d("else", "else");
////                            ContactModel contact1 = new ContactModel();
////                            contact1.setUserName(v);
////                            contact1.setUserINTime(DFragmentUser.userintime);
////                            contact1.setCardIdNo(dhg2);
////                            contact1.setPrice(cardprice);
////                            contact1.setUsserOutTime("");
////                            sQLiteHelper.insertRecordUser4(contact1);
////                            Log.d("checking"," "+"checking");
////                            vaa.clear();
////                            c.clear();
////                            SecondActivity.sumpriceActivity=0;
////                            sumprice=0;
////                            SecondActivity.v2= String.valueOf(0);
////                            v2= String.valueOf(0);
////                            vaa.add(cardprice);
////                            c.add(dhg2);
////                            v2 = String.valueOf(c.size());
////
////                            double sum =0;
////                            for (int p = 0; p < vaa.size(); p++) {
////                                sum +=  (Double.valueOf(vaa.get(p)));
////                                Log.i("kkd", (Double.valueOf(vaa.get(p))) + ""+sum);
////
////                            }
////
////                            sumprice=sum;
////                        }
////
////
////                }
////            Toast.makeText(getActivity(), getString(R.string.Please_Come_in), Toast.LENGTH_LONG).show();
////            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
////            toneG.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 200);
////
////        } catch (Exception e) {
////
////            e.printStackTrace();
////
////        }
////    }
////
////    public void update() {
////        dbHelper = new DatabaseHelper(getActivity());
////       dbHelper.getWritableDatabase();
////        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
////        String string1  = dateFormat1.format(new Date());
////        String Id = ed1.getText().toString();
////        ArrayList<Contact> contact = dbHelper.getContact1(Id,"1");
////        for (int i = 0; i < contact.size(); i++) {
////            if ((contact.get(i).getCardNo().equals(Id)&&(contact.get(i).getStatus().equals("1")))){
////                position = i;
////
////            }
////
////        }
////        Log.d("msg", "contact" + contact);
////        Contact c = contact.get(position);
////        String cardzid=c.getCardIdNo();
////        String INImagePATH=c.getIn_ImagePath();
////        SecondActivity.id.setText(cardzid);
////        File pictureFileDir1 =getDir1();
////        SimpleDateFormat dateFormat12 = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
////        String date1 = dateFormat12.format(new Date());
////        String photoFile = "out_"+cardzid+"_"+ date1 + ".jpg";
////        filename = pictureFileDir1.getPath() + File.separator + photoFile;
////
////        pictureFile1 = new File(filename);
////        if (!pictureFileDir1.exists() && !pictureFileDir1.mkdirs()) {
////            Toast.makeText(getActivity(), getString(R.string.Cantcreatedirectorytosaveimage),
////                    Toast.LENGTH_LONG).show();
////            return;
////
////        }
////
////        try {
////            FileOutputStream fos1 = new FileOutputStream(pictureFile1);
////
////            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
////            Bitmap rihxcb=  rotateImage(90,bmp);
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            rihxcb.compress(Bitmap.CompressFormat.PNG, 100, stream);
////            byte[] flippedImageByteArray = stream.toByteArray();
////
////
////            fos1.write(flippedImageByteArray);
////
////            fos1.close();
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////
////        c.setCardNo(Id);
////        c.setOutImage(image);
////        c.setOutTime(string1);
////        c.setStatus("0");
////        c.setOut_ImagePath(filename);
////        c.setIn_ImagePath(INImagePATH);
////        Log.d("update", "update" );
////        dbHelper.updateContact(c);
////        byte[]cm=contact.get(position).getInImage();
////        Log.d("Tag","ggggg "+filename);
////        Bitmap b = BitmapFactory.decodeByteArray(cm,0,cm.length);
////        int width = b.getWidth();
////        int height = b.getHeight();
////        int bounding = dpToPx(getActivity(),350);
////        float xScale = ((float) bounding) / width;
////        float yScale = ((float) bounding) / height;
////        float scale =     (xScale <= yScale) ? xScale : yScale;
////        Matrix matrix = new Matrix();
////        matrix.postScale(scale, scale);
////        matrix.postRotate(90);
////        Bitmap scaledBitmap = Bitmap.createBitmap(b, 0, 0, width, height, matrix, true);
////
////        galleryimage.setImageBitmap(scaledBitmap);
////        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
////        toneG.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 200);
////        Toast.makeText(getActivity(), getString(R.string.Please_go_out), Toast.LENGTH_LONG).show();
////    }
////
////    boolean containsCardno(ArrayList<ContactModel> contact1, String name) {
////        for (ContactModel item : contact1) {
////            if (item.getCardNo().equals(name)) {
////                return true;
////            }
////        }
////        return false;
////    }
////    boolean containscardid(ArrayList<ContactModel> contact1, String name) {
////        for (ContactModel item : contact1) {
////            if (item.getCardId().equals(name)) {
////                return true;
////            }
////        }
////        return false;
////    }
//
//
//    public void  Register(final String SessionUser,
//                          final String UserId,
//                          final String  AdminId,
//                          final String SessionLogin,
//                          final String CardNo,
//                          final String In_time,
//                          final String Image
//
//
//
//    ){
//        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
//
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//            @Override
//            protected String doInBackground(String... params) {
//                String return_text="";
//                JSONObject jsonObject=new JSONObject();
//                try {
//                    jsonObject.accumulate("user_name",SessionUser);
//                    jsonObject.accumulate("user_id",UserId);
//                    jsonObject.accumulate("admin_id",AdminId);
//                    jsonObject.accumulate("login_time",SessionLogin);
//                    jsonObject.accumulate("card_no",CardNo);
//                    jsonObject.accumulate("in_time",In_time);
//                    jsonObject.accumulate("in_image",Image);
//
//
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpPost httpPost = new HttpPost(BaseUrl+"/transaction_create_manual");
//                    StringEntity httpiEntity=new StringEntity(jsonObject.toString());
//                    httpPost.setEntity(httpiEntity);
//                    org.apache.http.HttpResponse response = httpClient.execute(httpPost);
//                    return_text= HTTPResponse.readResponse(response);
//                    return return_text;
//                } catch (ClientProtocolException e) {
//
//                } catch (IOException e) {
//                }
//                return return_text;
//            }
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//
//                try {
//                    JSONObject jsonObject=new JSONObject(result);
//                    if (jsonObject.getString("error").equalsIgnoreCase("false")) {
//
//                        JSONArray message = jsonObject.getJSONArray("message");
//                        String sucess=jsonObject.getString("messsage1");
//                        Toast.makeText(getActivity(), sucess, Toast.LENGTH_LONG).show();
//
//                        if (message != null) {
//
//                            for (int i = 0; i < message.length(); i++) {
//                                JSONObject m = message.getJSONObject(i);
//                                CardPrice=m.getString("card_price");
//                                InImage=m.getString("in_image");
//                                Status=m.getString("status");
//
////                                Picasso.with(getApplicationContext()).load(InImage).into(galleryimage);
//                                Glide.with(getContext()).load(img+InImage)
//                                        .thumbnail(0.5f)
//                                        .crossFade()
//                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                        .into(galleryimage);
//
//
//                            }
//
//                            System.out.println("===message===="+sucess);
//
//
//                        }
//                    }
//                    else if (jsonObject.getString("error").equalsIgnoreCase("true")) {
//                        String  message = jsonObject.getString("messsage");
//                        if (message != null) {
//                            {
//                                System.out.println("===========================error");
//                                Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        try {
//            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
//            sendPostReqAsyncTask.execute();
//        }catch (Exception e){
//
//        }
//    }
//
//
//
//}