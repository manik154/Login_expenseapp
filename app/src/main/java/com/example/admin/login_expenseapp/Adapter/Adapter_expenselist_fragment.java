package com.example.admin.login_expenseapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.login_expenseapp.Datamodel.Data_expense_list;
import com.example.admin.login_expenseapp.R;
import com.example.admin.login_expenseapp.fragment.ExpenseListFragment;

import java.util.ArrayList;

public class Adapter_expenselist_fragment extends RecyclerView.Adapter<Adapter_expenselist_fragment.MyViewHolder> {

    private ArrayList<Data_expense_list> dataSet = new ArrayList<>();
    Context context;
    private final ExpenseListFragment expenseListFragment;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView expense_date;
        TextView expense_time;
        TextView payment_to;
        TextView expense_category;
        ImageView imageViewIcon;
        TextView amount;
        TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.expense_category = (TextView) itemView.findViewById(R.id.AddExpense_name);
            this.expense_date = (TextView) itemView.findViewById(R.id.expense_date);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image_expense_mode);
            this.amount = (TextView) itemView.findViewById(R.id.amount);
            this.expense_time = (TextView) itemView.findViewById(R.id.expense_time);
            this.payment_to = (TextView) itemView.findViewById(R.id.payeename2);
            this.description = (TextView) itemView.findViewById(R.id.description);
        }
    }

    public Adapter_expenselist_fragment(ArrayList<Data_expense_list> data, Context context, ExpenseListFragment expenseListFragment) {
        this.dataSet = data;
        this.context = context;
        this.expenseListFragment = expenseListFragment;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userrow_expenselist, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final Adapter_expenselist_fragment.MyViewHolder holder, final int listPosition) {
        TextView expense_category = holder.expense_category;
        TextView expense_date = holder.expense_date;
        ImageView imageViewIcon = (ImageView) holder.imageViewIcon;
        TextView amount = (TextView) holder.amount;
        TextView expense_time = (TextView) holder.expense_time;
        TextView payment_To = (TextView) holder.payment_to;
        TextView description = (TextView) holder.description;

        expense_category.setText(dataSet.get(listPosition).getExpense_category());
        expense_date.setText(dataSet.get(listPosition).getExpense_date());
        expense_time.setText(dataSet.get(listPosition).getExpense_time());
        payment_To.setText(dataSet.get(listPosition).getPayment_to());
        //amount.setText(dataSet.get(listPosition).getAmount());
        //Glide.with(context).load(R.drawable.ic_launcher).placeholder(R.drawable.ic_launcher)
        //      .error(R.mipmap.ic_launcher).override(50,50).into(holder.imageViewIcon);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();

    }
}
