package com.example.user.alphatest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Main extends AppCompatActivity {

    EditText Tn, Tnum, Ps, email;
    FirebaseAuth firebaseAuth;
    String pass, Tname, Team;

    BroadcastBatt broadcastBatt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent g=getIntent();
        pass=g.getStringExtra("new password");
        Ps.setText(pass);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(broadcastBatt, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastBatt);
    }

    public void newP(View view) {
        Intent t=new Intent(this, newP.class);
        startActivity(t);
    }


    public void chose(View view) {
        Tname=Tn.getText().toString();
        Team=Tnum.getText().toString();
        final ProgressDialog progressDialog=ProgressDialog.show(Main.this,"Please wait...","Processing...",true);
        (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),Ps.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    if (!Tname.isEmpty() && !Team.isEmpty()) {
                        Toast.makeText(Main.this, "Logged In successfully", Toast.LENGTH_LONG).show();
                        Intent go = new Intent(Main.this, newTeam.class);
                        startActivity(go);}
                    else {
                        Toast.makeText(Main.this, "Please enter Teams' name and number", Toast.LENGTH_LONG).show(); } }
                else {
                    Log.e("Error", task.getException().toString());
                    Toast.makeText(Main.this, "You are not signed, please create a new user", Toast.LENGTH_LONG).show(); }
            }
        });
    }
}
