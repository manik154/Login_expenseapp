package com.example.admin.login_expenseapp.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.login_expenseapp.Activity.EditExpenses;
import com.example.admin.login_expenseapp.Database.GetBookDatabase;
import com.example.admin.login_expenseapp.R;


public class MyDialogFragment extends DialogFragment
{

    private TextView edit_expenses;
    private TextView delete_expenses;
    private TextView expense_id;
Bundle args2=new Bundle();
    GetBookDatabase db;
  int Value=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment, container, false);
        getDialog().setTitle("Simple Dialog");
edit_expenses=(TextView)rootView.findViewById(R.id.edit_expenses);
delete_expenses=(TextView)rootView.findViewById(R.id.delete_expenses);
expense_id=(TextView)rootView.findViewById(R.id.expneseId);
db=new GetBookDatabase(getActivity());
        args2=getArguments();
        Value=args2.getInt("id");
      //  Toast.makeText(getActivity(),String.valueOf(args2.getInt("id")), Toast.LENGTH_SHORT).show();
        if(args2!=null)
        {
            expense_id.setText(String.valueOf(args2.getInt("id")));
        }
        edit_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                args2.putInt("id",args2.getInt("id"));
                getDialog().dismiss();
             Intent intent=new Intent(getActivity(),EditExpenses.class);
                intent.putExtras(args2);
              startActivity(intent);
            }
        });
        delete_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getDialog().dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setCancelable(false);
                alertDialog.setMessage("Delete item?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteContact(Value);
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
        return rootView;
    }}