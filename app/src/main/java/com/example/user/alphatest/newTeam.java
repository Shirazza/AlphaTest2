package com.example.user.alphatest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newTeam extends AppCompatActivity {

    Switch s1, s2, s3, s4, s5;
    boolean auto, climb, help;
    SeekBar sb1,sb2,sb3,sb4;
    int swi, scale, portal, exch;
    EditText gn,tn;
    String Gnum, Tnum;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);

        ref= FirebaseDatabase.getInstance().getReference("teams");
        s1=(Switch) findViewById(R.id.switch1);
        s2=(Switch) findViewById(R.id.switch2);
        s3=(Switch) findViewById(R.id.switch3);
        s4=(Switch) findViewById(R.id.switch4);
        s5=(Switch) findViewById(R.id.switch5);
        sb1=(SeekBar) findViewById(R.id.seekBar);
        sb2=(SeekBar) findViewById(R.id.seekBar2);
        sb3=(SeekBar) findViewById(R.id.seekBar3);
        sb4=(SeekBar) findViewById(R.id.seekBar4);
        gn=(EditText) findViewById(R.id.editText4);
        tn=(EditText) findViewById(R.id.editText3);
        tv1=(TextView)findViewById(R.id.textView16);
        tv2=(TextView)findViewById(R.id.textView29);
        tv3=(TextView)findViewById(R.id.text0View17);
        tv4=(TextView)findViewById(R.id.text0View18);
        tv5=(TextView)findViewById(R.id.text0View33);
        tv6=(TextView)findViewById(R.id.text0View28);
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        tv4.setVisibility(View.INVISIBLE);
        tv5.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);
        sb3.setVisibility(View.INVISIBLE);
        sb4.setVisibility(View.INVISIBLE);

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s2.isChecked()){
                    tv1.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.VISIBLE);
                    tv4.setVisibility(View.VISIBLE);
                    sb3.setVisibility(View.VISIBLE);
                }
                if(!s2.isChecked()){
                    tv1.setVisibility(View.INVISIBLE);
                    tv3.setVisibility(View.INVISIBLE);
                    tv4.setVisibility(View.INVISIBLE);
                    sb3.setVisibility(View.INVISIBLE);
                }
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s3.isChecked()){
                    tv2.setVisibility(View.VISIBLE);
                    tv5.setVisibility(View.VISIBLE);
                    tv6.setVisibility(View.VISIBLE);
                    sb4.setVisibility(View.VISIBLE);
                }
                else{
                    tv2.setVisibility(View.INVISIBLE);
                    tv5.setVisibility(View.INVISIBLE);
                    tv6.setVisibility(View.INVISIBLE);
                    sb4.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void gotoweb(View view) {
        Intent go= new Intent(this, webview.class);
        startActivity(go);
    }

    public void clearpage(View view) {
        AlertDialog.Builder adb1;
        adb1= new AlertDialog.Builder(newTeam.this);
        adb1.setTitle("Confrim delete");
        adb1.setMessage("Are you sure you want to clear the page?");
        adb1.setIcon(R.drawable.qmark);
        adb1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               s1.setChecked(false);
               sb1.setProgress(0);
               sb2.setProgress(0);
               s2.setChecked(false);
               sb3.setProgress(0);
               s3.setChecked(false);
               sb4.setProgress(0);
               s4.setChecked(false);
               s5.setChecked(false);
               gn.setText("");
               tn.setText("");
            }
        });
        adb1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();;
            }
        });
        AlertDialog ad=adb1.create();
        ad.show();}


    public void saveteam(View view) {
        AlertDialog.Builder adb1;
        adb1= new AlertDialog.Builder(newTeam.this);
        adb1.setTitle("Confrim save");
        adb1.setMessage("Are you sure you want to save?");
        adb1.setIcon(R.drawable.qmark);
        adb1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                auto = s1.isChecked();
                swi=sb1.getProgress()+1;
                scale=sb2.getProgress()+1;
                if(s2.isChecked()){
                    portal=sb3.getProgress()+1;}
                if(s3.isChecked()){
                    exch=sb4.getProgress()+1;}
                climb = s4.isChecked();
                help = s5.isChecked();
                Gnum=gn.getText().toString();
                Tnum=tn.getText().toString();

                String id=ref.push().getKey();
                Team team=new Team(id,Tnum,Gnum,auto,help,climb,swi,scale,portal,exch);
                ref.child(id).setValue(team);
            }
        });
        adb1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();;
            }
        });
        AlertDialog ad=adb1.create();
        ad.show();}


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options, menu);
        menu.add(0,0,100,"Search");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String tm=item.getTitle().toString();
        if(tm.equals("Help")){
           Intent in=new Intent(newTeam.this, Help.class);
            startActivity(in);}
        if(tm.equals("Credits")){
            Intent in=new Intent(newTeam.this, Credits.class);
            startActivity(in);}
        if(tm.equals("Search")){
            Intent in=new Intent(newTeam.this, search.class);
            startActivity(in);}
        return true;
    }
}