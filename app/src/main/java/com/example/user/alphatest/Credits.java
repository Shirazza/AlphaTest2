package com.example.user.alphatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,0,100,"Help");
        menu.add(0,0,200,"New team");
        menu.add(0,0,300,"Search");
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String tm=item.getTitle().toString();
        if(tm.equals("Help")){
            Intent in=new Intent(Credits.this, Help.class);
            startActivity(in);}
        if(tm.equals("New team")){
            Intent in=new Intent(Credits.this, newTeam.class);
            startActivity(in);}
        if(tm.equals("Search")){
            Intent in=new Intent(Credits.this, search.class);
            startActivity(in);}
        return true;}
}
