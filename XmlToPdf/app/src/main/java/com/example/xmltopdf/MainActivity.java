package com.example.xmltopdf;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xmltopdf.demoInvoice.DemoInvoiceFragment;
import com.example.xmltopdf.model.FailureResponse;
import com.example.xmltopdf.model.SuccessResponse;


public class MainActivity extends AppCompatActivity {
    Button btnPrint,btnInvoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnPrint=findViewById(R.id.bt_print);
        btnInvoice=findViewById(R.id.bt_invoice);

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View content = inflater.inflate(R.layout.barcode, null);

                /*  Test for RecyclerView*/
                 /*   RecyclerView recyclerView = content.findViewById(R.id.list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(new DummyItemRecyclerViewAdapter(DummyContent.ITEMS));*/


                PdfGenerator.getBuilder()
                        .setContext(MainActivity.this)
                        .fromViewSource()
                        .fromView(content,content,content)
                        .setFileName("TestPDF")
                        .setFolderName("Test-PDF-folder")
                        .openPDFafterGeneration(true)
                        .build(new PdfGeneratorListener() {
                            @Override
                            public void onFailure(FailureResponse failureResponse) {
                                super.onFailure(failureResponse);
                            }
                            @Override
                            public void onStartPDFGeneration() {

                            }
                            @Override
                            public void onFinishPDFGeneration() {

                            }
                            @Override
                            public void showLog(String log) {
                                super.showLog(log);
                            }

                            @Override
                            public void onSuccess(SuccessResponse response) {
                                super.onSuccess(response);
                            }
                        });

            }
        });

        btnInvoice.setOnClickListener(v -> {
            if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null) {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new DemoInvoiceFragment())
                        .commit();
            }
        });

    }

}
