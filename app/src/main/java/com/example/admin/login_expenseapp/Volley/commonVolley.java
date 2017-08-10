package com.example.admin.login_expenseapp.Volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class commonVolley {
    RequestQueue queue;
    String url;
    ProgressDialog dialog;

    Activity activity;
    public Map<String, String> volparams = new HashMap<String, String>();


    private final String TAG="commonVolley";

    public commonVolley(final Activity ctnx, final String url, final int RequestMethod, final Map<String, String>
            volparams, final callbackvol callback){
        activity=ctnx;
        this.volparams=volparams;
        this.url=url;
        dialog=new ProgressDialog(activity);
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();




        Log.i(TAG,"commonVolley line no 34 ");
        queue = Volley.newRequestQueue(ctnx);

         StringRequest response=new StringRequest(RequestMethod, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.i(TAG," commonVolley -- line no 42 response "+response);
                 dialog.dismiss();
                callback.onSuccess(response);
            }
        },new Response.ErrorListener(){

   @Override
   public void onErrorResponse(VolleyError error) {
     // Log.e("url in commonVolley",url);
    Log.e("error", error + "");
dialog.hide();
       Snackbar.make(activity.findViewById(android.R.id.content), "Slow Internet Connection", Snackbar.LENGTH_LONG)
        .setAction("Retry",null/* new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }*/).show();
                Log.i(TAG,"commonVolley -- response error line no 51");
                // dialog.dismiss();
                callback.onFailure(error);
            }
        })
        {

            protected Map<String, String> getParams() {

                return volparams;
            }
        };
        //dialog=ProgressDialog.show(activity,"","sending...");
        queue.add(response);
        Log.i(TAG,"commonVolley -- line no 62");
    }


}

