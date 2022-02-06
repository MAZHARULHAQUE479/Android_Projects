//package com.tamtoanthang.apps.mobileparking.DataBase;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.tamtoanthang.apps.mobileparking.R;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//
///**
// * Created by lue on 04-10-2017.
// */
//
//public class DetailAdapter extends ArrayAdapter<DetailModel> {
//    ArrayList<DetailModel> DetailList;
//    LayoutInflater vi;
//    int Resource;
//    ViewHolder holder;
//
//    public DetailAdapter(Context context, int resource, ArrayList<DetailModel> objects) {
//        super(context, resource, objects);
//        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        Resource = resource;
//        DetailList = objects;
//
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // convert view = design
//
//        View v = convertView;
//        if (v == null) {
//            holder = new ViewHolder();
//            v = vi.inflate(R.layout.list_item3, null);
//            holder.cardid = (TextView) v.findViewById(R.id.cardId);
//            holder.cardno = (TextView) v.findViewById(R.id.card_no);
//            holder.cardtype = (TextView) v.findViewById(R.id.cardtype);
//            holder.cardprice = (TextView) v.findViewById(R.id.cardprice);
//            holder.inimage = (ImageView) v.findViewById(R.id.inimage);
//            holder.outimage = (ImageView) v.findViewById(R.id.outimage);
//            holder.intime = (TextView) v.findViewById(R.id.intime);
//            holder.outtime=(TextView)v.findViewById(R.id.outtime);
//            holder.status=(TextView)v.findViewById(R.id.status);
//            holder.username=(TextView)v.findViewById(R.id.username);
//
//            v.setTag(holder);
//        } else {
//            holder = (ViewHolder) v.getTag();
//        }
//
//        new DownloadImageTask(holder.inimage).execute(DetailList.get(position).getInimage());
//        new DownloadImageTask(holder.outimage).execute(DetailList.get(position).getOutimage());
//        holder.cardid.setText(DetailList.get(position).getcardid());
//        holder.cardno.setText(DetailList.get(position).getcardno());
//        holder.cardtype.setText( DetailList.get(position).getcardtype());
//        holder.cardprice.setText(DetailList.get(position).getCardprice());
//        holder.intime.setText( DetailList.get(position).getIntime());
//        holder.outtime.setText(DetailList.get(position).getOuttime());
//        holder.status.setText(DetailList.get(position).getStatus());
//        holder.username.setText(DetailList.get(position).getUsername());
//        return v;
//
//    }
//
//    static class ViewHolder {
//        public TextView cardid;
//        public TextView cardno;
//        public TextView cardtype;
//        public TextView cardprice;
//        public ImageView inimage;
//        public ImageView outimage;
//        public TextView intime;
//        public TextView outtime;
//        public TextView status;
//        public TextView username;
//
//    }
//
//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
//}
