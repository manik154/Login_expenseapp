package com.example.admin.login_expenseapp.Activity;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.admin.login_expenseapp.Database.DatabaseContract;
import com.example.admin.login_expenseapp.Database.GetBookDatabase;
import com.example.admin.login_expenseapp.R;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class EditExpenses extends AppCompatActivity {


    private EditText amount;
    private GetBookDatabase db;
    private EditText email_id;
    private EditText description;
    private EditText Payment_To;
    private EditText categories;
    private ImageButton imageButton;
    int id_To_Update = 0;
    private Button button_save;
    private EditText expense_date;
    private EditText expense_time;
    private DatabaseContract constant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exoenses);
        Bundle extras = getIntent().getExtras();
        amount = (EditText) findViewById(R.id.amount);
        Payment_To = (EditText) findViewById(R.id.payee);
        expense_time = (EditText) findViewById(R.id.expense_time);
        description = (EditText) findViewById(R.id.description);
        expense_date = (EditText) findViewById(R.id.expense_date);
//        categories= (EditText) findViewById(R.id.category) ;
        email_id = (EditText) findViewById(R.id.userEmailId);
        imageButton=(ImageButton)findViewById(R.id.AddMediaCapture2);
        button_save = (Button) findViewById(R.id.SaveExpense_button);
        db = new GetBookDatabase(this);
        constant = new DatabaseContract();
        final Spinner spinner = (Spinner) findViewById(R.id.category);

        List<String> categories = new ArrayList<String>();
        categories.add("Cash");
        categories.add("Card Payment");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        if (extras != null) {
            int Value = extras.getInt("id");
            Toast.makeText(this, String.valueOf(Value), Toast.LENGTH_LONG).show();
            if (Value > 0) {
                Cursor rs = db.getUser1(Value);
                id_To_Update = Value;
                if (rs.moveToFirst()) {
                    do {
                        String amount1 = rs.getString(rs.getColumnIndex(constant.KEY_AMOUNT));
                        String emailid = rs.getString(rs.getColumnIndex(constant.KEY_EMAILID));
                        //  String categories1= rs.getString(rs.getColumnIndex(constant.KEY_CATEGORIES));
                        String description1 = rs.getString(rs.getColumnIndex(constant.KEY_DESCRPTION));
                        String expense_date1 = rs.getString(rs.getColumnIndex(constant.KEY_EXPENSE_DATE));
                        String expense_time1 = rs.getString(rs.getColumnIndex(constant.KEY_EXPENSE_TIME));
                        String payment_to1 = rs.getString(rs.getColumnIndex(constant.KEY_PAYMENT_TO));

                        //Bitmap picture = rs.getBlob(rs.getColumnIndex(constant.KEY_ICON);


                        amount.setText((CharSequence) amount1);
                        email_id.setText((CharSequence) emailid);
                        description.setText((CharSequence) description1);
                        Payment_To.setText((CharSequence) payment_to1);
                        expense_date.setText((CharSequence) expense_date1);
                        expense_time.setText((CharSequence) expense_time1);
                    } while (rs.moveToNext());
                }
                if (!rs.isClosed()) {
                    rs.close();
                }
//categories.setText((CharSequence)categories1);
/*if(db.onUpgrade(id_To_Update,amount.getText().toString(),
                        email_id.getText().toString(),spinner.getSelectedItem().toString(),description.getText().toString(),
                        Payment_To.getText().toString(),expense_date.getText().toString(),expense_time.getText().toString())l)
                {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                  //  Intent intent = new Intent(getApplicationContext(),.class);
                    //startActivity(intent);
                } else
                    {
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }

            }*/
            }
        }
    }
}