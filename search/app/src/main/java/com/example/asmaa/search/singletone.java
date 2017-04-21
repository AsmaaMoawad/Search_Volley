package com.example.asmaa.search;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Asmaa on 21-Apr-17.
 */

public class singletone  {
    private static singletone Instance;
    private static RequestQueue requestQueue;
    private static Context mctx;


    private singletone(Context context){
        this.mctx=context;
        requestQueue=getRequestQueue();
    }




    public RequestQueue getRequestQueue(){
        if(requestQueue==null){


            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());


        }
        return requestQueue;
    }





    public static synchronized singletone getIstance(Context context){
        Instance =new singletone(context);
        return Instance;
    }


    public<T>  void addtorequest(Request<T> request){
        requestQueue.add(request);

    }
}
