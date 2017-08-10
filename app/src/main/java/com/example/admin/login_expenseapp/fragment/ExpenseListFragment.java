package com.example.admin.login_expenseapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.login_expenseapp.Adapter.Adapter_expenselist_fragment;
import com.example.admin.login_expenseapp.Database.GetBookDatabase;
import com.example.admin.login_expenseapp.Datamodel.Data_expense_list;

import com.example.admin.login_expenseapp.R;
import com.example.admin.login_expenseapp.RecyclerTouchListener;
import java.util.ArrayList;

public class ExpenseListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    Context context;
    private GetBookDatabase dbHandler;
    private Adapter_expenselist_fragment adapter;
    Bundle args2=new Bundle();
    private TextView expense_time;

    private ArrayList<Data_expense_list> data=new ArrayList<>();
    // Spinner spinner = (Spinner) findViewById(R.id.spinner);
    private OnFragmentInteractionListener mListener;

    public ExpenseListFragment() {
        // Required empty public constructor
    }

    public static ExpenseListFragment newInstance(String param1, String param2) {
        ExpenseListFragment fragment = new ExpenseListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        View view=inflater.inflate(R.layout.fragment_home, container, false);

/*        String[] nameArray = {"Stores", "stores", "Entertain", "Events", "Services", "About Us", "Contact Us","Magazine", "Map", "Favorite", "Floor Plan","Loyalty"};
        Integer[] amount = {5000,6000,7000,5000,6000,7000,5000,6000,7000,5000,6000,7000};

        Integer[] drawableArray = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,};
*/
        expense_time=(TextView)view.findViewById(R.id.expense_time);
        layoutManager=new LinearLayoutManager(context);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        dbHandler = new GetBookDatabase(getActivity());
        Log.e("Insert: ", "Inserting...");
        if (dbHandler.getUsersCount() <= 0) {
            try
            {
                dbHandler.createDataBase();
                dbHandler.openDataBase();
                dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Reading all contacts
        Log.d("Reading: ", "reading contacts..");
        data = dbHandler.getAllUsers();

        adapter=new Adapter_expenselist_fragment(data,context,ExpenseListFragment.this);

 /*       for (int i=0;i<nameArray.length; i++)
        {
            data.add(new Data_expense_list(nameArray[i],drawableArray[i],drawableArray[i],nameArray[i]));
        }*/

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        //      args2.putString("expense_time",expense_time.getText().toString());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, new RecyclerTouchListener.ClickListener() {


            @Override
            public void onClick(View view, int position)
            {
                args2.putInt("id",position+1);
                FragmentManager fm = getFragmentManager();
                MyDialogFragment dialogFragment = new MyDialogFragment ();
                dialogFragment.show (fm,"Sample Fragment");
                dialogFragment.setArguments(args2);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return view;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}




