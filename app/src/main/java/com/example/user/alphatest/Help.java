package com.example.user.alphatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.InputStream;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help); }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,0,100,"Credits");
        menu.add(0,0,200,"New team");
        menu.add(0,0,300,"Search");
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String tm=item.getTitle().toString();
        if(tm.equals("Credits")){
            Intent in=new Intent(Help.this, Credits.class);
            startActivity(in);}
        if(tm.equals("New team")){
            Intent in=new Intent(Help.this, newTeam.class);
            startActivity(in);}
        if(tm.equals("Search")){
            Intent in=new Intent(Help.this, search.class);
            startActivity(in);}
        return true;}
}
