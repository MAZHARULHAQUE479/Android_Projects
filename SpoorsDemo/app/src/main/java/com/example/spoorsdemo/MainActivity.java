package com.example.spoorsdemo;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText Name, add , updateold, updatenew, delete;
    myDbAdapter helper;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button save,discard;
    Switch simpleSwitch;
    String item,t1,t2,statusSwitch,Gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.etname);
        add = (EditText) findViewById(R.id.etadd);
        Spinner spinner = (Spinner) findViewById(R.id.squater);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioGrp);
        simpleSwitch = (Switch) findViewById(R.id.simpleSwitch2);
        save=(Button)findViewById(R.id.button3) ;
        discard=(Button)findViewById(R.id.button);


        helper = new myDbAdapter(this);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("ANDROID");
        categories.add("JAVA");
        categories.add("C++");
        categories.add("PHP");
//        categories.add("Personal");
//        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewdata();
            }
        });
    }

    public void addUser() {
        t1 = Name.getText().toString();
//        Name.setError("This field is required");
        t2 = add.getText().toString();
//        add.setError("This field is required");

        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioSexButton = (RadioButton) findViewById(selectedId);
         Gender=radioSexButton.getText().toString();
        if (simpleSwitch.isChecked()) {
            statusSwitch = simpleSwitch.getTextOn().toString();
        } else
        {
            statusSwitch = simpleSwitch.getTextOff().toString();
    }

       Long id= helper.insertData(t1,t2,item,Gender,statusSwitch);

        if(id<=0)
        {

            Toast.makeText(this, "Insertion Unsuccessful", Toast.LENGTH_LONG).show();

        } else
        {

            Toast.makeText(this, "Insertion Successful", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,ShowActivity.class);
        }

    }

    public void viewdata()
    {
        String data = helper.getData();
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
//        Message.message(this,data);
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        // On selecting a spinner item
         item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}