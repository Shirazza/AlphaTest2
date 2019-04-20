package com.example.user.alphatest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RadioGroup rg;
    RadioButton rd1;
    RadioButton rd2;
    Spinner sp;
    int option;
    ListView list;
    List<Item> itemList;
    List<Team> teamList;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rg= (RadioGroup) findViewById(R.id.rg);
        rd1= (RadioButton) findViewById(R.id.rd1);
        rd2= (RadioButton) findViewById(R.id.rd2);
        sp= (Spinner) findViewById(R.id.spinner);
        list= (ListView) findViewById(R.id.list);

        rd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rd2.setChecked(false);
                rd1.setChecked(true);
            }
        });
        rd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rd1.setChecked(false);
                rd2.setChecked(true);
            }
        });

        ref = FirebaseDatabase.getInstance().getReference("teams");
        itemList = new ArrayList<>();
        teamList = new ArrayList<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.abilities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);

        rd1.setChecked(true);

        option = 0;
    }

    @Override
    protected void onStart() {

        refreshList(false);

        super.onStart();
    }

    private void refreshList(boolean flag) {

        if (flag) {
            Query q = ref.orderByChild("rating");
            q.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    teamList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Team team = ds.getValue(Team.class);
                        teamList.add(0 , team);
                    }
                    TeamList2 adp = new TeamList2(search.this, teamList);
                    list.setAdapter(adp);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        else {
            Query q = ref;
            q.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    itemList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Team team = ds.getValue(Team.class);
                        Item item = new Item(team.getTeamNum(), team.getAbility(option), Integer.parseInt(team.getNum()));
                        itemList.add(item);
                    }
                    TeamList adp = new TeamList(search.this, itemList);
                    list.setAdapter(adp);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void checkAction(View view) {
        if (rd1.isChecked()){
            refreshList(false);
        }
        else{
            refreshList(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options, menu);
        menu.add(0,0,100,"New team");
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String tm=item.getTitle().toString();
        if(tm.equals("Help")){
            Intent in=new Intent(search.this, Help.class);
            startActivity(in); }
        if(tm.equals("Credits")){
            Intent in=new Intent(search.this, Credits.class);
            startActivity(in);}
        if(tm.equals("New team")){
            Intent in=new Intent(search.this, newTeam.class);
            startActivity(in);}
        return true;}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        option = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
