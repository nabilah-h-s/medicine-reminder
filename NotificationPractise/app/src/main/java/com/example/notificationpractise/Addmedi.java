package com.example.notificationpractise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Addmedi extends AppCompatActivity {
    CustomAdapter adp;
    ArrayList<Medicinedetails> arrayList;
    Medicinedetails md;
    EditText edmediname;
    EditText edtime;
    EditText edqn;
    ListView listmedi;
    int error = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmedi);
        edmediname = findViewById(R.id.edname);
        edtime = findViewById(R.id.edtime);
        edqn = findViewById(R.id.edqn);
        listmedi = findViewById(R.id.lstmedi);

        arrayList = DataHolder.getInstance().prev;

        adp = new CustomAdapter(this, arrayList);
        listmedi.setAdapter(adp);
        Button btnsave = findViewById(R.id.btndone);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error= savesata();
                if(error==0)
                {
                    arrayList.add(md);
                    adp.notifyDataSetChanged();
                }
                else
                {
                    shwerrtoast();
                    error=0;
                }


            }
        });

    }

    private void shwerrtoast() {
        Toast.makeText(this, "Warning!!!can't save.\nEnter your data properly.", Toast.LENGTH_LONG).show();
    }

    private int savesata() {
        md = new Medicinedetails();

        int b = 1;
        String quantity;
        int m = 0;
        quantity = edqn.getText().toString();
        if(quantity.isEmpty())
        {
            edqn.setError("you can add your medicine to take at least once per day.");
            error=1;
        }
        else
        {

        b = Integer.parseInt(quantity);

        if (b > 0 && b <= 24) {
            m = 24 / b;

        }
        else if (b == 0) {
           error=1;
            edqn.setError("you can add your medicine to take at least once per day.");
        } else if (b > 24) {
            error=1;
            edqn.setError("overdosed!!! don't have your medicine without prescribed dose");
        }


        }

        int d;
        String tmptm="" , tmptm1;
        tmptm1 = edtime.getText().toString();
        if(tmptm1.isEmpty())
        {
            edtime.setError("this field can't be empty");
            error=1;
        }

        else if(error==0){
            d = Integer.parseInt(tmptm1);
            if (d < 0 || d > 24) {
                edtime.setError("time should be in 24hr format");
                error=1;
            } else {
                int n = d;
                if(n==0){
                    String tp = String.valueOf(12);
                    tmptm = tp + " am ";
                }
                else if (n <12) {
                    tmptm = edtime.getText().toString() + " am ";
                } else if(n==12)
                {
                    tmptm = edtime.getText().toString() + " pm ";
                }
                else {
                    String tp = String.valueOf(n - 12);
                    tmptm = tp + " pm ";
                }
                int c;
                for (c = 1; c < b; c++) {
                    n = n + m;
                    if (n %24==0)
                        tmptm = tmptm + " , " + String.valueOf(12) + " am ";

                    else if (n < 12)
                        tmptm = tmptm + " , " + String.valueOf(n) + " am ";
                    else if(n==12)
                    {
                        tmptm = tmptm + " , " + String.valueOf(n) + " pm ";
                    }
                    else if (n < 24) {
                        tmptm = tmptm + " , " + String.valueOf(n - 12) + " pm ";
                    } else {
                        tmptm = tmptm + " , " + String.valueOf(n - 24) + " am ";

                    }
                }
            }
        }
        if(error==0)
        {
            md.setName(edmediname.getText().toString());
            md.setTime(tmptm);
        }

        return  error;

    }


    public void resetData(View view) {
        edmediname.setText("");
        edtime.setText("");
        edqn.setText("");

    }
}
