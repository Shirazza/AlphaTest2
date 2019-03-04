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

public class TeamList2 extends ArrayAdapter<Team> {
    private Activity context;
    private List<Team> teamList;

    public TeamList2(Activity context, List<Team> teamList){
        super(context,R.layout.team_layout,teamList);
        this.context= context;
        this.teamList=teamList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.team_layout,null,true);
        TextView teamNumText = listViewItem.findViewById(R.id.teamNumText);
        TextView autoText = listViewItem.findViewById(R.id.autoText);
        TextView helpText = listViewItem.findViewById(R.id.helpText);
        TextView climbText = listViewItem.findViewById(R.id.climbText);
        TextView switchText = listViewItem.findViewById(R.id.switchText);
        TextView scaleText = listViewItem.findViewById(R.id.scaleText);
        TextView portalText = listViewItem.findViewById(R.id.portalText);
        TextView exchangeText = listViewItem.findViewById(R.id.exchangeText);
        Team team =teamList.get(position);
        teamNumText.setText("" + team.getTeamNum());
        autoText.setText("Autonomous: " + team.isAutonomous());
        helpText.setText("Help: " + team.isHelp());
        climbText.setText("Climb: " + team.isClimb());
        switchText.setText("Switch: " + team.getSwi());
        scaleText.setText("Scale: " + team.getScale());
        portalText.setText("Portal: " + team.getPortal());
        exchangeText.setText("Exchange: " + team.getExchange());
        return listViewItem;
    }
}
