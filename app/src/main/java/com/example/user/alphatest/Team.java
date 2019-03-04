package com.example.user.alphatest;

/**
 * Created by user on 20/12/2018.
 */

public class Team {
    public String id;
    public String num;
    public boolean autonomous;
    public boolean help;
    public boolean climb;
    public int swi;
    public int scale;
    public int portal;
    public int exchange;
    public int rating;
    public String teamNum;

    public Team(String id, String teamNum, String num, boolean autonomous, boolean help, boolean climb, int swi, int scale, int portal, int exchange) {
        this.id = id;
        this.teamNum = teamNum;
        this.num = num;
        this.autonomous = autonomous;
        this.help = help;
        this.climb = climb;
        this.swi = swi;
        this.scale = scale;
        this.portal = portal;
        this.exchange = exchange;

        this.rating = 2 * swi + 2 * scale + 5 * exchange;
        if (autonomous)
            rating += 5;
        if (climb)
            rating += 30;
        if (help)
            rating += 5;
    }

    public Team() {
    }

    public String getId() {
        return id;
    }

    public String getTeamNum()
    {
        return teamNum;
    }

    public String getNum() {
        return num;
    }

    public boolean isAutonomous() {
        return autonomous;
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isClimb() {
        return climb;
    }

    public int getSwi() {
        return swi;
    }

    public int getScale() {
        return scale;
    }

    public int getPortal() {
        return portal;
    }

    public int getExchange() {
        return exchange;
    }

    public String getAbility(int s)
    {
        switch(s)
        {
            case 0: return ""+autonomous;
            case 1: return ""+help;
            case 2: return ""+climb;
            case 3: return ""+swi;
            case 4: return ""+scale;
            case 5: return ""+portal;
            default: return ""+exchange;
        }
    }
}
