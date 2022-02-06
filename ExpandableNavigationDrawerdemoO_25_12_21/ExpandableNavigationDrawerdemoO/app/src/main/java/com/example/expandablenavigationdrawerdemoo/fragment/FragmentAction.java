package com.example.expandablenavigationdrawerdemoo.fragment;

import android.app.DatePickerDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.expandablenavigationdrawerdemoo.Model.Detail;
import com.example.expandablenavigationdrawerdemoo.R;
import com.example.expandablenavigationdrawerdemoo.adapter.CustomListViewAdapter;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAction extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String KEY_MOVIE_TITLE = "key_title";
    Button today,months;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    ListView listView;
    public ArrayAdapter<Detail> adapter;
    public ArrayList<Detail> detailList=new ArrayList<Detail>();

    public FragmentAction() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FragmentAction.
     */
    public static Fragment newInstance(String movieTitle) {
        FragmentAction fragmentAction = new FragmentAction();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        fragmentAction.setArguments(args);
        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_action, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Spinner element
        Spinner spinner = (Spinner) view.findViewById(R.id.squater);
//        spinner.setPrompt(R.layout.custom_textview);
        listView = (ListView)view.findViewById(R.id.customer_detail);
        adapter = new CustomListViewAdapter(getActivity(), R.layout.list_item3, detailList);
        adapter.notifyDataSetChanged();
        listView.invalidateViews();
         today = (Button) view.findViewById(R.id.bdate);
         months = (Button) view.findViewById(R.id.bmonth);
         today.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.N)
             @Override
             public void onClick(View view) {
                 calendar = Calendar.getInstance();
                 year = calendar.get(Calendar.YEAR);
                 month = calendar.get(Calendar.MONTH);
                 dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                 datePickerDialog = new DatePickerDialog(getActivity(),
                         new DatePickerDialog.OnDateSetListener() {
                             @Override
                             public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                 today.setText(day + "/" + (month + 1) + "/" + year);
                             }
                         }, year, month, dayOfMonth);
                 datePickerDialog.show();
             }
         });
         months.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.N)
             @Override
             public void onClick(View view) {
                 MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(getActivity(), new MonthPickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(int selectedMonth, int selectedYear) {
                         months.setText( (month + 1) + "/" + year);
//                         String month_name = selectedMonth.format(calendar.getTime());

                     }
                 }, /* activated number in year */ 3, 5);

                 builder.showMonthOnly()
                         .build()
                         .show();
             }
         });


        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("First Quater");
        categories.add("Second Quater");
        categories.add("Third Quater");
        categories.add("Fourth Quater");
//        categories.add("Personal");
//        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        Drawable movieIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.movie_icon, getContext().getTheme());
        if (movieIcon != null) {
            movieIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.pink), PorterDuff.Mode.SRC_ATOP);
        }
//        ((ImageView) view.findViewById(R.id.movie_icon)).setImageDrawable(movieIcon);

//        String movieTitle = getArguments().getString(KEY_MOVIE_TITLE);
//        ((TextView) view.findViewById(R.id.movie_title)).setText(movieTitle);
    }
//    private void chooseMonthOnly() {
//        setContentView(R.layout.activity_choose_month);
//
//        findViewById(R.id.choose_month).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(MainActivity.this, new MonthPickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(int selectedMonth, int selectedYear) {
//
//                    }
//                }, /* activated number in year */ 3, 5);
//
//                builder.showMonthOnly()
//                        .build()
//                        .show();
//            }
//        });
//    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
