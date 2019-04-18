package com.example.user.alphatest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class newP extends AppCompatActivity {

    EditText thephoneNum, theCode, thenewP, theemail;
    String phoneNum, code, codeEntered, newP, email;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_p);
        thephoneNum= (EditText) findViewById(R.id.thephoneNum);
        theCode= (EditText) findViewById(R.id.theCode);
        thenewP= (EditText) findViewById(R.id.thenewP);
        theemail= (EditText) findViewById(R.id.theemail);

        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void sendM(View view) {
        phoneNum=thephoneNum.getText().toString();
        if(phoneNum.equals("0509022098")|| phoneNum.equals("0506994969")) {
            code=""+((int)(Math.random()*900)+100);
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum, null, code, null, null);
            Toast.makeText(this, "The message has been sent to your mentor's phone", Toast.LENGTH_LONG).show();}
        else
            Toast.makeText(this, "Enter only your mentor's phone number", Toast.LENGTH_LONG).show();
    }

    /*public boolean checkPhone(){
        if(phoneNum.length()!=10){
            return false; }
        if(!phoneNum.substring(0,2).equals("05")){
            return false; }
        if(phoneNum.substring(2,3).equals("7")|| phoneNum.substring(2,3).equals("9")){
            return false; }
        return true; }*/

    public void checkC(View view) {
        codeEntered=theCode.getText().toString();
        if (codeEntered.equals(code))
            Toast.makeText(this, "the code you entered is correct", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "the code you entered is incorrect", Toast.LENGTH_LONG).show();
    }


    public void next(View view) {
        codeEntered=theCode.getText().toString();
        newP=thenewP.getText().toString();
        email=theemail.getText().toString();

        if (newP!=null && codeEntered!=null){
            AlertDialog.Builder adb1;
            adb1= new AlertDialog.Builder(this);
            adb1.setTitle("Are you sure you want to continue?");
            adb1.setMessage("Are you sure this is the new password you want: "+newP+ " ?");
            adb1.setIcon(R.drawable.qmark);
            adb1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent goStart=new Intent(newP.this, Main.class);
                    goStart.putExtra("new password", newP);
                    startActivityForResult(goStart, 1); }
            });
            adb1.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();;
                }
            });
            AlertDialog ad=adb1.create();
            ad.show();}
        else
            Toast.makeText(this, "enter a new password", Toast.LENGTH_LONG).show();
    }

    public void backtostart(View view) {
        Intent ba= new Intent(newP.this, Main.class);
        startActivity(ba);
    }
}
