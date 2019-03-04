package com.example.user.alphatest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by user on 20/12/2018.
 */

public class TeamList extends ArrayAdapter<Item> {

    private Activity context;
    private List<Item> teamList;

    public TeamList(Activity context, List<Item> teamList){
        super(context,R.layout.list_layout,teamList);
        this.context= context;
        this.teamList=teamList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewNum=(TextView)listViewItem.findViewById(R.id.teamNum);
        TextView textViewability=(TextView)listViewItem.findViewById(R.id.ability);
        TextView numText=(TextView)listViewItem.findViewById(R.id.num);
        Item item=teamList.get(position);
        textViewNum.setText(""+item.getTNum());
        numText.setText("Game number: "+item.getNum());
        textViewability.setText("Ability: "+item.getAbility());
        return listViewItem;
    }
}
