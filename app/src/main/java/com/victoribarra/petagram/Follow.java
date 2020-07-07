package com.victoribarra.petagram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Follow extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"diste follow",Toast.LENGTH_SHORT).show();
        Log.d("entro", "entro");

    }
}
