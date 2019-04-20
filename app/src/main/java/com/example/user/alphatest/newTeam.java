package com.example.user.alphatest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
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

        Intent get1=getIntent();
        Tnum=get1.getStringExtra("team num1");
        Gnum=get1.getStringExtra("game num1");
        auto=get1.getBooleanExtra("auto1", false);
        swi=get1.getIntExtra("switch1", 0);
        scale=get1.getIntExtra("scale1", 0);
        portal=get1.getIntExtra("portal1", 0);
        exch=get1.getIntExtra("exchange1", 0);
        climb=get1.getBooleanExtra("climb1", false);
        help=get1.getBooleanExtra("help1", false);

        tn.setText(Tnum);
        gn.setText(Gnum);
        if (auto== true){s1.setChecked(true);}
        else{s1.setChecked(false);}
        sb1.setProgress(swi);
        sb2.setProgress(scale);
        if(portal>0){
            s2.setChecked(true);
            sb3.setVisibility(View.VISIBLE);
            sb3.setProgress(portal);
        }
        if(exch>0){
            s3.setChecked(true);
            sb4.setVisibility(View.VISIBLE);
            sb4.setProgress(exch);
        }
        if (climb== true){s4.setChecked(true);}
        else{s4.setChecked(false);}
        if (help== true){s5.setChecked(true);}
        else{s5.setChecked(false);}

        ref= FirebaseDatabase.getInstance().getReference("teams");


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
        Gnum=gn.getText().toString();
        Tnum=tn.getText().toString();
        auto = s1.isChecked();
        swi=sb1.getProgress()+1;
        scale=sb2.getProgress()+1;
        if(s2.isChecked()){
            portal=sb3.getProgress()+1;}
        if(s3.isChecked()){
            exch=sb4.getProgress()+1;}
        climb = s4.isChecked();
        help = s5.isChecked();
        Intent go= new Intent(this, webview.class);
        go.putExtra("game num", Gnum);
        go.putExtra("team num", Tnum);
        go.putExtra("auto", auto);
        go.putExtra("switch", swi);
        go.putExtra("scale", scale);
        go.putExtra("portal", portal);
        go.putExtra("exchange", exch);
        go.putExtra("climb", climb);
        go.putExtra("help", help);
        startActivityForResult(go, 1);
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
                Toast.makeText(newTeam.this, "The page has been cleared", Toast.LENGTH_LONG).show();
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
        Gnum=gn.getText().toString();
        Tnum=tn.getText().toString();
        if(Tnum!=null && !Tnum.equals("0") && Gnum!=null && !Gnum.equals("0")){
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
                Toast.makeText(newTeam.this, "The team has been saved to the data base", Toast.LENGTH_LONG).show();
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
        else{
              Toast.makeText(newTeam.this, "Please enter a valid game or team number", Toast.LENGTH_LONG).show();
    }}


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