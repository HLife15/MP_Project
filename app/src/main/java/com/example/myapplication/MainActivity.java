package com.example.myapplication;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView resultView;
    ImageView imageView;
    Spinner spinner;

    ListView listView;

    int win, draw, lose, pts, gf, ga, gd;

    boolean haCheck;

    int[] logos = {R.drawable.def,
            R.drawable.ars, R.drawable.avl, R.drawable.bou, R.drawable.bre, R.drawable.bha,
            R.drawable.che, R.drawable.cry, R.drawable.eve, R.drawable.ful, R.drawable.ips,
            R.drawable.lei, R.drawable.liv, R.drawable.mci, R.drawable.mun, R.drawable.nca,
            R.drawable.nfo, R.drawable.sou, R.drawable.tot, R.drawable.whu, R.drawable.wol};

    String[] teams = {"<Select Your Team>",
                    "Arsenal", "Aston Villa", "Bournemouth", "Brentford", "Brighton & Hove Albion",
                    "Chelsea", "Crystal Palace", "Everton", "Fulham", "Ipswich Town",
                    "Leicester City", "Liverpool", "Manchester City", "Manchester United", "Newcastle United",
                    "Nottingham Forest", "Southampton", "Tottenham Hotspur", "West Ham United", "Wolverhampton Wanderers"};

    String[] teams_short = {"<Select Your Team>",
                    "Arsenal", "Aston Villa", "Bournemouth", "Brentford", "Brighton",
                    "Chelsea", "Crystal Palace", "Everton", "Fulham","Ipswich",
                    "Leicester", "Liverpool", "Man City", "Man Utd", "Newcastle",
                    "Nott'm Forest", "Southampton", "Spurs", "West Ham", "Wolves"};

    String[] stadiums = {"",
                    "Emirates Stadium, London", "Villa Park, Birmingham", "Vitality Stadium, Bournemouth", "Gtech Community Stadium, Brentford", "American Express Stadium, Falmer",
                    "Stamford Bridge, London", "Selhurst Park, London", "Goodison Park, Liverpool", "Craven Cottage, London", "Portman Road, Ipswich",
                    "King Power Stadium, Leicester", "Anfield, Liverpool", "Ethihad Stadium, Manchester", "Old Trafford, Manchester", "St.James' Park, Newcastle",
                    "The City Ground, Nottingham", "St.Mary's Stadium, Southampton", "Tottenham Hotspur Stadium, London", "London Stadium, London", "Molineux Stadium, Wolverhampton"};

    String[] scores = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    int[] results = {R.drawable.zwin, R.drawable.zdraw, R.drawable.zlose};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_team);
        resultView = (TextView) findViewById(R.id.table);
        imageView = findViewById(R.id.logo);
        spinner = (Spinner) findViewById(R.id.spinner);

        listView = findViewById(R.id.match_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.spinner_layout, teams
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                win = draw = lose = pts = 0;
                textView.setText(teams_short[position]);
                textView.setVisibility(View.VISIBLE);
                ArrayList<Match> schedules = new ArrayList<>();

                switch (position) {
                    case 0:
                        textView.setVisibility(View.INVISIBLE);
                        imageView.setImageResource(logos[position]);
                        resultView.setVisibility(View.GONE);
                        break;
                    case 1:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);
                        setMatch(schedules, logos[20], "1R", teams_short[20], "2024-08-17 Sat", "23:00", stadiums[1], scores[2], scores[0], results[0]);
                        setMatch(schedules, logos[2], "2R", teams_short[2], "2024-08-25 Sun", "01:30", stadiums[2], scores[0], scores[2], results[0]);
                        setMatch(schedules, logos[5], "3R", teams_short[5], "2024-08-31 Sat", "20:30", stadiums[1], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[18], "4R", teams_short[18], "2024-09-15 Sun", "22:00", stadiums[18], scores[0], scores[1], results[0]);
                        setMatch(schedules, logos[13], "5R", teams_short[13], "2024-09-23 Mon", "00:30", stadiums[13], scores[2], scores[2], results[1]);
                        setMatch(schedules, logos[11], "6R", teams_short[11], "2024-09-28 Sat", "23:00", stadiums[1], scores[4], scores[2], results[0]);

                        setMatch(schedules, logos[17], "7R", teams_short[17], "2024-10-05 Sat", "23:00", stadiums[1], scores[3], scores[1], results[0]);
                        setMatch(schedules, logos[3], "8R", teams_short[3], "2024-10-20 Sun", "01:30", stadiums[3], scores[2], scores[0], results[2]);
                        setMatch(schedules, logos[12], "9R", teams_short[12], "2024-10-28 Mon", "01:30", stadiums[1], scores[2], scores[2], results[1]);

                        setMatch(schedules, logos[15], "10R", teams_short[15], "2024-11-02 Sat", "21:30", stadiums[15], scores[1], scores[0], results[2]);
                        setMatch(schedules, logos[6], "11R", teams_short[6], "2024-11-11 Mon", "01:30", stadiums[6], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[16], "12R", teams_short[16], "2024-11-24 Sun", "00:00", stadiums[1], scores[3], scores[0], results[0]);

                        setMatch(schedules, logos[19], "13R", teams_short[19], "2024-12-01 Sun", "02:30", stadiums[19], scores[2], scores[5], results[0]);
                        setMatch(schedules, logos[14], "14R", teams_short[14], "2024-12-05 Thu", "05:15", stadiums[1], scores[2], scores[0], results[0]);
                        setMatch(schedules, logos[9], "15R", teams_short[9], "2024-12-08 Sun", "23:00", stadiums[9]);
                        setMatch(schedules, logos[8], "16R", teams_short[8], "2024-12-15 Sun", "00:00", stadiums[1]);
                        setMatch(schedules, logos[7], "17R", teams_short[7], "2024-12-22 Sun", "02:30", stadiums[7]);
                        setMatch(schedules, logos[10], "18R", teams_short[10], "2024-12-28 Sat", "05:15", stadiums[1]);

                        setMatch(schedules, logos[4], "19R", teams_short[4], "2025-01-02 Thu", "02:30", stadiums[4]);
                        setMatch(schedules, logos[5], "20R", teams_short[5], "2025-01-05 Sun", "02:30", stadiums[5]);
                        setMatch(schedules, logos[18], "21R", teams_short[18], "2025-01-16 Thu", "05:00", stadiums[1]);
                        setMatch(schedules, logos[2], "22R", teams_short[2], "2025-01-19 Sun", "02:30", stadiums[1]);
                        setMatch(schedules, logos[20], "23R", teams_short[20], "2025-01-26 Sun", "00:00", stadiums[20]);

                        setMatch(schedules, logos[13], "24R", teams_short[13], "2025-02-02 Sun", "00:00", stadiums[1]);
                        setMatch(schedules, logos[11], "25R", teams_short[11], "2025-02-16 Sun", "00:00", stadiums[11]);
                        setMatch(schedules, logos[19], "26R", teams_short[19], "2025-02-23 Sun", "00:00", stadiums[1]);
                        setMatch(schedules, logos[16], "27R", teams_short[16], "2025-02-26 Wed", "04:45", stadiums[16]);

                        setMatch(schedules, logos[14], "28R", teams_short[14], "2025-03-09 Sun", "00:00", stadiums[14]);
                        setMatch(schedules, logos[6], "29R", teams_short[6], "2025-03-16 Sun", "00:00", stadiums[1]);

                        setMatch(schedules, logos[9], "30R", teams_short[9], "2025-04-02 Wed", "03:45", stadiums[1]);
                        setMatch(schedules, logos[8], "31R", teams_short[8], "2025-04-05 Sat", "23:00", stadiums[8]);
                        setMatch(schedules, logos[4], "32R", teams_short[4], "2025-04-12 Sat", "23:00", stadiums[1]);
                        setMatch(schedules, logos[10], "33R", teams_short[10], "2025-04-19 Sat", "23:00", stadiums[10]);
                        setMatch(schedules, logos[7], "34R", teams_short[7], "2025-04-26 Sat", "23:00", stadiums[1]);

                        setMatch(schedules, logos[3], "35R", teams_short[3], "2025-05-03 Sat", "23:00", stadiums[1]);
                        setMatch(schedules, logos[12], "36R", teams_short[12], "2025-05-10 Sat", "23:00", stadiums[12]);
                        setMatch(schedules, logos[15], "37R", teams_short[15], "2025-05-17 Sat", "23:00", stadiums[1]);
                        setMatch(schedules, logos[17], "38R", teams_short[17], "2025-05-26 Mon", "00:00", stadiums[17]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[19], "1R", teams_short[19], "2024-08-18 Sun", "01:30", stadiums[19], scores[1], scores[2], results[0]);
                        setMatch(schedules, logos[1], "2R", teams_short[1], "2024-08-25 Sun", "01:30", stadiums[2], scores[0], scores[2], results[2]);
                        setMatch(schedules, logos[11], "3R", teams_short[11], "2024-08-31 Sat", "23:00", stadiums[11], scores[1], scores[2], results[0]);

                        setMatch(schedules, logos[8], "4R", teams_short[8], "2024-09-15 Sun", "01:30", stadiums[2], scores[3], scores[2], results[0]);
                        setMatch(schedules, logos[20], "5R", teams_short[20], "2024-09-21 Sat", "23:00", stadiums[2], scores[3], scores[1], results[0]);
                        setMatch(schedules, logos[10], "6R", teams_short[10], "2024-09-29 Sun", "22:00", stadiums[10], scores[2], scores[2], results[1]);

                        setMatch(schedules, logos[14], "7R", teams_short[14], "2024-10-06 Sun", "22:00", stadiums[2], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[9], "8R", teams_short[9], "2024-10-19 Sat", "23:00", stadiums[9], scores[1], scores[3], results[0]);
                        setMatch(schedules, logos[3], "9R", teams_short[3], "2024-10-26 Sat", "23:00", stadiums[2], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[18], "10R", teams_short[18], "2024-11-03 Sun", "23:00", stadiums[18], scores[4], scores[1], results[2]);
                        setMatch(schedules, logos[12], "11R", teams_short[12], "2024-11-10 Sun", "05:00", stadiums[12], scores[2], scores[0], results[2]);
                        setMatch(schedules, logos[7], "12R", teams_short[7], "2024-11-24 Sun", "00:00", stadiums[2], scores[2], scores[2], results[1]);

                        setMatch(schedules, logos[6], "13R", teams_short[6], "2024-12-01 Sun", "22:30", stadiums[6], scores[3], scores[0], results[2]);
                        setMatch(schedules, logos[4], "14R", teams_short[4], "2024-12-05 Thu", "05:15", stadiums[2], scores[3], scores[1], results[0]);
                        setMatch(schedules, logos[17], "15R", teams_short[17], "2024-12-08 Sun", "00:00", stadiums[2]);
                        setMatch(schedules, logos[16], "16R", teams_short[16], "2024-12-15 Sun", "02:30", stadiums[16]);
                        setMatch(schedules, logos[13], "17R", teams_short[13], "2024-12-21 Sat", "21:30", stadiums[2]);
                        setMatch(schedules, logos[15], "18R", teams_short[15], "2024-12-27 Fri", "00:00", stadiums[15]);
                        setMatch(schedules, logos[5], "19R", teams_short[5], "2024-12-31 Tue", "04:45", stadiums[2]);

                        setMatch(schedules, logos[11], "20R", teams_short[11], "2025-01-05 Sun", "00:00", stadiums[2]);
                        setMatch(schedules, logos[8], "21R", teams_short[8], "2025-01-16 Thu", "04:30", stadiums[8]);
                        setMatch(schedules, logos[1], "22R", teams_short[1], "2025-01-19 Sun", "02:30", stadiums[1]);
                        setMatch(schedules, logos[19], "23R", teams_short[19], "2025-01-27 Mon", "01:30", stadiums[2]);

                        setMatch(schedules, logos[20], "24R", teams_short[20], "2025-02-02 Sun", "00:00", stadiums[20]);
                        setMatch(schedules, logos[10], "25R", teams_short[10], "2025-02-16 Sun", "00:00", stadiums[2]);
                        setMatch(schedules, logos[6], "26R", teams_short[6], "2025-02-23 Sun", "00:00", stadiums[2]);
                        setMatch(schedules, logos[7], "27R", teams_short[7], "2025-02-26 Wed", "05:00", stadiums[7]);

                        setMatch(schedules, logos[4], "28R", teams_short[4], "2025-03-09 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[12], "29R", teams_short[12], "2025-03-16 Sun", "00:00", stadiums[2]);

                        setMatch(schedules, logos[5], "30R", teams_short[5], "2025-04-02 Wed", "03:45", stadiums[5]);
                        setMatch(schedules, logos[16], "31R", teams_short[16], "2025-04-05 Sat", "23:00", stadiums[2]);
                        setMatch(schedules, logos[17], "32R", teams_short[17], "2025-04-12 Sat", "23:00", stadiums[17]);
                        setMatch(schedules, logos[15], "33R", teams_short[15], "2025-04-19 Sat", "23:00", stadiums[2]);
                        setMatch(schedules, logos[13], "34R", teams_short[13], "2025-04-26 Sat", "23:00", stadiums[13]);

                        setMatch(schedules, logos[9], "35R", teams_short[9], "2025-05-03 Sat", "23:00", stadiums[2]);
                        setMatch(schedules, logos[3], "36R", teams_short[3], "2025-05-10 Sat", "23:00", stadiums[3]);
                        setMatch(schedules, logos[18], "37R", teams_short[18], "2025-05-18 Sun", "23:00", stadiums[2]);
                        setMatch(schedules, logos[14], "38R", teams_short[14], "2025-05-26 Mon", "00:00", stadiums[14]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[16], "1R", teams_short[16], "2024-08-17 Sat", "23:00", stadiums[16], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[15], "2R", teams_short[15], "2024-08-25 Sun", "22:00", stadiums[3], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[8], "3R", teams_short[8], "2024-08-31 Sat", "23:00", stadiums[8], scores[2], scores[3], results[0]);

                        setMatch(schedules, logos[6], "4R", teams_short[6], "2024-09-15 Sun", "04:00", stadiums[3], scores[0], scores[1], results[2]);
                        setMatch(schedules, logos[12], "5R", teams_short[12], "2024-09-21 Sat", "23:00", stadiums[12], scores[3], scores[0], results[2]);

                        setMatch(schedules, logos[17], "6R", teams_short[17], "2024-10-01 Tue", "04:00", stadiums[3], scores[3], scores[1], results[0]);
                        setMatch(schedules, logos[11], "7R", teams_short[11], "2024-10-05 Sat", "04:00", stadiums[11], scores[1], scores[0], results[2]);
                        setMatch(schedules, logos[1], "8R", teams_short[1], "2024-10-20 Sun", "01:30", stadiums[3], scores[2], scores[0], results[0]);
                        setMatch(schedules, logos[2], "9R", teams_short[2], "2024-10-26 Sat", "23:00", stadiums[2], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[13], "10R", teams_short[13], "2024-11-03 Sun", "00:00", stadiums[3], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[4], "11R", teams_short[4], "2024-11-10 Sun", "00:00", stadiums[4], scores[3], scores[2], results[2]);
                        setMatch(schedules, logos[5], "12R", teams_short[5], "2024-11-24 Sun", "00:00", stadiums[3], scores[1], scores[2], results[2]);

                        setMatch(schedules, logos[20], "13R", teams_short[20], "2024-12-01 Sun", "00:00", stadiums[20], scores[2], scores[4], results[0]);
                        setMatch(schedules, logos[18], "14R", teams_short[18], "2024-12-06 Fri", "05:15", stadiums[3]);
                        setMatch(schedules, logos[10], "15R", teams_short[10], "2024-12-08 Sun", "23:00", stadiums[10]);
                        setMatch(schedules, logos[19], "16R", teams_short[19], "2024-12-17 Tue", "05:00", stadiums[3]);
                        setMatch(schedules, logos[14], "17R", teams_short[14], "2024-12-22 Sun", "23:00", stadiums[14]);
                        setMatch(schedules, logos[7], "18R", teams_short[7], "2024-12-27 Fri", "00:00", stadiums[3]);
                        setMatch(schedules, logos[9], "19R", teams_short[9], "2024-12-30 Mon", "00:00", stadiums[9]);

                        setMatch(schedules, logos[8], "20R", teams_short[8], "2025-01-05 Sun", "00:00", stadiums[3]);
                        setMatch(schedules, logos[6], "21R", teams_short[6], "2025-01-15 Wed", "04:30", stadiums[6]);
                        setMatch(schedules, logos[15], "22R", teams_short[15], "2025-01-18 Sat", "21:30", stadiums[15]);
                        setMatch(schedules, logos[16], "23R", teams_short[16], "2025-01-26 Sun", "00:00", stadiums[3]);

                        setMatch(schedules, logos[12], "24R", teams_short[12], "2025-02-02 Sun", "00:00", stadiums[3]);
                        setMatch(schedules, logos[17], "25R", teams_short[17], "2025-02-16 Sun", "00:00", stadiums[17]);
                        setMatch(schedules, logos[20], "26R", teams_short[20], "2025-02-23 Sun", "00:00", stadiums[3]);
                        setMatch(schedules, logos[5], "27R", teams_short[5], "2025-02-26 Wed", "04:45", stadiums[5]);

                        setMatch(schedules, logos[18], "28R", teams_short[18], "2025-03-09 Sun", "00:00", stadiums[18]);
                        setMatch(schedules, logos[4], "29R", teams_short[4], "2025-03-16 Sun", "00:00", stadiums[3]);

                        setMatch(schedules, logos[10], "30R", teams_short[10], "2025-04-02 Wed", "03:45", stadiums[3]);
                        setMatch(schedules, logos[19], "31R", teams_short[19], "2025-04-05 Sat", "23:00", stadiums[19]);
                        setMatch(schedules, logos[9], "32R", teams_short[9], "2025-04-12 Sat", "23:00", stadiums[3]);
                        setMatch(schedules, logos[7], "33R", teams_short[7], "2025-04-19 Sat", "23:00", stadiums[7]);
                        setMatch(schedules, logos[14], "34R", teams_short[14], "2025-04-26 Sat", "23:00", stadiums[3]);

                        setMatch(schedules, logos[1], "35R", teams_short[1], "2025-05-03 Sat", "23:00", stadiums[1]);
                        setMatch(schedules, logos[2], "36R", teams_short[2], "2025-05-10 Sat", "23:00", stadiums[3]);
                        setMatch(schedules, logos[13], "37R", teams_short[13], "2025-05-17 Sat", "23:00", stadiums[13]);
                        setMatch(schedules, logos[11], "38R", teams_short[11], "2025-05-26 Mon", "00:00", stadiums[3]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[7], "1R", teams_short[7], "2024-08-18 Sun", "22:00", stadiums[4], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[12], "2R", teams_short[12], "2024-08-26 Mon", "00:30", stadiums[12], scores[2], scores[0], results[2]);
                        setMatch(schedules, logos[17], "3R", teams_short[17], "2024-08-31 Sat", "23:00", stadiums[4], scores[3], scores[1], results[0]);

                        setMatch(schedules, logos[13], "4R", teams_short[13], "2024-09-14 Sat", "23:00", stadiums[13], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[18], "5R", teams_short[18], "2024-09-21 Sat", "23:00", stadiums[18], scores[3], scores[1], results[2]);
                        setMatch(schedules, logos[19], "6R", teams_short[19], "2024-09-28 Sat", "23:00", stadiums[4], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[20], "7R", teams_short[20], "2024-10-05 Sat", "23:00", stadiums[4], scores[5], scores[3], results[0]);
                        setMatch(schedules, logos[14], "8R", teams_short[14], "2024-10-19 Sat", "23:00", stadiums[14], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[10], "9R", teams_short[10], "2024-10-26 Sat", "23:00", stadiums[4], scores[4], scores[3], results[0]);

                        setMatch(schedules, logos[9], "10R", teams_short[9], "2024-11-05 Tue", "05:00", stadiums[9], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[3], "11R", teams_short[3], "2024-11-10 Sun", "00:00", stadiums[4], scores[3], scores[2], results[0]);
                        setMatch(schedules, logos[8], "12R", teams_short[8], "2024-11-24 Sun", "00:00", stadiums[8], scores[0], scores[0], results[1]);

                        setMatch(schedules, logos[11], "13R", teams_short[11], "2024-12-01 Sun", "00:00", stadiums[4], scores[4], scores[1], results[0]);
                        setMatch(schedules, logos[2], "14R", teams_short[2], "2024-12-05 Thu", "05:15", stadiums[2], scores[3], scores[1], results[2]);
                        setMatch(schedules, logos[15], "15R", teams_short[15], "2024-12-08 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[6], "16R", teams_short[6], "2024-12-16 Mon", "04:00", stadiums[6]);
                        setMatch(schedules, logos[16], "17R", teams_short[16], "2024-12-22 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[5], "18R", teams_short[5], "2024-12-28 Sat", "04:00", stadiums[5]);

                        setMatch(schedules, logos[1], "19R", teams_short[1], "2025-01-02 Thu", "02:30", stadiums[4]);
                        setMatch(schedules, logos[17], "20R", teams_short[17], "2025-01-05 Sun", "00:00", stadiums[17]);
                        setMatch(schedules, logos[13], "21R", teams_short[13], "2025-01-15 Wed", "04:30", stadiums[4]);
                        setMatch(schedules, logos[12], "22R", teams_short[12], "2025-01-19 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[7], "23R", teams_short[7], "2025-01-26 Sun", "23:00", stadiums[7]);

                        setMatch(schedules, logos[18], "24R", teams_short[18], "2025-02-02 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[19], "25R", teams_short[19], "2025-02-16 Sun", "00:00", stadiums[19]);
                        setMatch(schedules, logos[11], "26R", teams_short[11], "2025-02-23 Sun", "00:00", stadiums[11]);
                        setMatch(schedules, logos[8], "27R", teams_short[8], "2025-02-26 Wed", "04:45", stadiums[4]);

                        setMatch(schedules, logos[2], "28R", teams_short[2], "2025-03-09 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[3], "29R", teams_short[3], "2025-03-16 Sun", "00:00", stadiums[3]);

                        setMatch(schedules, logos[15], "30R", teams_short[15], "2025-04-03 Thu", "03:45", stadiums[15]);
                        setMatch(schedules, logos[6], "31R", teams_short[6], "2025-04-05 Sat", "23:00", stadiums[4]);
                        setMatch(schedules, logos[1], "32R", teams_short[1], "2025-04-12 Sat", "23:00", stadiums[1]);
                        setMatch(schedules, logos[5], "33R", teams_short[5], "2025-04-19 Sat", "23:00", stadiums[4]);
                        setMatch(schedules, logos[16], "34R", teams_short[16], "2025-04-26 Sat", "23:00", stadiums[16]);

                        setMatch(schedules, logos[14], "35R", teams_short[14], "2025-05-03 Sat", "23:00", stadiums[4]);
                        setMatch(schedules, logos[10], "36R", teams_short[10], "2025-05-10 Sat", "23:00", stadiums[10]);
                        setMatch(schedules, logos[9], "37R", teams_short[9], "2025-05-18 Sun", "23:00", stadiums[4]);
                        setMatch(schedules, logos[20], "38R", teams_short[20], "2025-05-26 Mon", "00:00", stadiums[20]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[8], "1R", teams_short[8], "2024-08-17 Sat", "23:00", stadiums[8], scores[0], scores[3], results[0]);
                        setMatch(schedules, logos[14], "2R", teams_short[14], "2024-08-24 Sat", "20:30", stadiums[5], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[1], "3R", teams_short[1], "2024-08-31 Sat", "20:30", stadiums[1], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[10], "4R", teams_short[10], "2024-09-14 Sat", "23:00", stadiums[5], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[16], "5R", teams_short[16], "2024-09-22 Sun", "22:00", stadiums[5], scores[2], scores[2], results[1]);
                        setMatch(schedules, logos[6], "6R", teams_short[6], "2024-09-28 Sat", "23:00", stadiums[6], scores[4], scores[2], results[2]);

                        setMatch(schedules, logos[18], "7R", teams_short[18], "2024-10-07 Mon", "00:30", stadiums[5], scores[3], scores[2], results[0]);
                        setMatch(schedules, logos[15], "8R", teams_short[15], "2024-10-19 Sat", "23:00", stadiums[15], scores[0], scores[1], results[0]);
                        setMatch(schedules, logos[20], "9R", teams_short[20], "2024-10-26 Sat", "23:00", stadiums[5], scores[2], scores[2], results[1]);

                        setMatch(schedules, logos[12], "10R", teams_short[12], "2024-11-03 Sun", "00:00", stadiums[12], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[13], "11R", teams_short[13], "2024-11-10 Sun", "02:30", stadiums[5], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[3], "12R", teams_short[3], "2024-11-24 Sun", "00:00", stadiums[3], scores[1], scores[2], results[0]);
                        setMatch(schedules, logos[17], "13R", teams_short[17], "2024-11-30 Sat", "05:00", stadiums[5], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[9], "14R", teams_short[9], "2024-12-06 Fri", "04:30", stadiums[9]);
                        setMatch(schedules, logos[11], "15R", teams_short[11], "2024-12-08 Sun", "23:00", stadiums[11]);
                        setMatch(schedules, logos[7], "16R", teams_short[7], "2024-12-15 Sun", "23:00", stadiums[5]);
                        setMatch(schedules, logos[19], "17R", teams_short[19], "2024-12-22 Sun", "00:00", stadiums[19]);
                        setMatch(schedules, logos[4], "18R", teams_short[4], "2024-12-28 Sat", "04:00", stadiums[5]);
                        setMatch(schedules, logos[2], "19R", teams_short[2], "2024-12-31 Tue", "04:45", stadiums[2]);

                        setMatch(schedules, logos[1], "20R", teams_short[1], "2025-01-05 Sun", "02:30", stadiums[5]);
                        setMatch(schedules, logos[10], "21R", teams_short[10], "2025-01-17 Fri", "04:30", stadiums[10]);
                        setMatch(schedules, logos[14], "22R", teams_short[14], "2025-01-19 Sun", "23:00", stadiums[14]);
                        setMatch(schedules, logos[8], "23R", teams_short[8], "2025-01-26 Sun", "00:00", stadiums[5]);

                        setMatch(schedules, logos[16], "24R", teams_short[16], "2025-02-02 Sun", "00:00", stadiums[16]);
                        setMatch(schedules, logos[6], "25R", teams_short[6], "2025-02-16 Sun", "00:00", stadiums[5]);
                        setMatch(schedules, logos[17], "26R", teams_short[17], "2025-02-23 Sun", "00:00", stadiums[17]);
                        setMatch(schedules, logos[3], "27R", teams_short[3], "2025-02-26 Wed", "04:45", stadiums[5]);

                        setMatch(schedules, logos[9], "28R", teams_short[9], "2025-03-09 Sun", "00:00", stadiums[5]);
                        setMatch(schedules, logos[13], "29R", teams_short[13], "2025-03-16 Sun", "00:00", stadiums[13]);

                        setMatch(schedules, logos[2], "30R", teams_short[2], "2025-04-02 Wed", "03:45", stadiums[5]);
                        setMatch(schedules, logos[7], "31R", teams_short[7], "2025-04-05 Sat", "23:00", stadiums[7]);
                        setMatch(schedules, logos[11], "32R", teams_short[11], "2025-04-12 Sat", "23:00", stadiums[5]);
                        setMatch(schedules, logos[4], "33R", teams_short[4], "2025-04-19 Sat", "23:00", stadiums[4]);
                        setMatch(schedules, logos[19], "34R", teams_short[19], "2025-04-26 Sat", "23:00", stadiums[5]);

                        setMatch(schedules, logos[15], "35R", teams_short[15], "2025-05-03 Sat", "23:00", stadiums[5]);
                        setMatch(schedules, logos[20], "36R", teams_short[20], "2025-05-10 Sat", "23:00", stadiums[20]);
                        setMatch(schedules, logos[12], "37R", teams_short[12], "2025-05-18 Sun", "23:00", stadiums[5]);
                        setMatch(schedules, logos[18], "38R", teams_short[18], "2025-05-26 Mon", "00:00", stadiums[18]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[13], "1R", teams_short[13], "2024-08-19 Mon", "00:30", stadiums[6], scores[0], scores[2], results[2]);
                        setMatch(schedules, logos[20], "2R", teams_short[20], "2024-08-25 Sun", "22:00", stadiums[20], scores[2], scores[6], results[0]);

                        setMatch(schedules, logos[7], "3R", teams_short[7], "2024-09-01 Sun", "21:30", stadiums[6], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[3], "4R", teams_short[3], "2024-09-15 Sun", "04:00", stadiums[3], scores[0], scores[1], results[0]);
                        setMatch(schedules, logos[19], "5R", teams_short[19], "2024-09-21 Sat", "20:30", stadiums[19], scores[0], scores[3], results[0]);
                        setMatch(schedules, logos[5], "6R", teams_short[5], "2024-09-28 Sat", "23:00", stadiums[6], scores[4], scores[2], results[0]);

                        setMatch(schedules, logos[16], "7R", teams_short[16], "2024-10-06 Sun", "22:00", stadiums[6], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[12], "8R", teams_short[12], "2024-10-21 Mon", "00:30", stadiums[12], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[15], "9R", teams_short[15], "2024-10-27 Sun", "23:00", stadiums[6], scores[2], scores[1], results[0]);

                        setMatch(schedules, logos[14], "10R", teams_short[14], "2024-11-04 Mon", "01:30", stadiums[14], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[1], "11R", teams_short[1], "2024-11-11 Mon", "01:30", stadiums[6], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[11], "12R", teams_short[11], "2024-11-23 Sat", "21:30", stadiums[11], scores[1], scores[2], results[0]);

                        setMatch(schedules, logos[2], "13R", teams_short[2], "2024-12-01 Sun", "22:30", stadiums[6], scores[3], scores[0], results[0]);
                        setMatch(schedules, logos[17], "14R", teams_short[17], "2024-12-05 Thu", "04:30", stadiums[17], scores[1], scores[5], results[0]);
                        setMatch(schedules, logos[18], "15R", teams_short[18], "2024-12-09 Mon", "01:30", stadiums[18]);
                        setMatch(schedules, logos[4], "16R", teams_short[4], "2024-12-16 Mon", "04:00", stadiums[6]);
                        setMatch(schedules, logos[8], "17R", teams_short[8], "2024-12-22 Sun", "23:00", stadiums[8]);
                        setMatch(schedules, logos[9], "18R", teams_short[9], "2024-12-27 Fri", "00:00", stadiums[6]);
                        setMatch(schedules, logos[10], "19R", teams_short[10], "2024-12-31 Tue", "04:45", stadiums[10]);

                        setMatch(schedules, logos[7], "20R", teams_short[7], "2025-01-05 Sun", "00:00", stadiums[7]);
                        setMatch(schedules, logos[3], "21R", teams_short[3], "2025-01-15 Wed", "04:30", stadiums[6]);
                        setMatch(schedules, logos[20], "22R", teams_short[20], "2025-01-21 Tue", "05:00", stadiums[6]);
                        setMatch(schedules, logos[13], "23R", teams_short[13], "2025-01-26 Sun", "02:30", stadiums[13]);

                        setMatch(schedules, logos[19], "24R", teams_short[19], "2025-02-02 Sun", "00:00", stadiums[6]);
                        setMatch(schedules, logos[5], "25R", teams_short[5], "2025-02-16 Sun", "00:00", stadiums[5]);
                        setMatch(schedules, logos[2], "26R", teams_short[2], "2025-02-23 Sun", "00:00", stadiums[2]);
                        setMatch(schedules, logos[17], "27R", teams_short[17], "2025-02-27 Thu", "04:45", stadiums[6]);

                        setMatch(schedules, logos[11], "28R", teams_short[11], "2025-03-09 Sun", "00:00", stadiums[6]);
                        setMatch(schedules, logos[1], "29R", teams_short[1], "2025-03-16 Sun", "00:00", stadiums[1]);

                        setMatch(schedules, logos[18], "30R", teams_short[18], "2025-04-03 Thu", "03:45", stadiums[6]);
                        setMatch(schedules, logos[4], "31R", teams_short[4], "2025-04-05 Sat", "23:00", stadiums[4]);
                        setMatch(schedules, logos[10], "32R", teams_short[10], "2025-04-12 Sat", "23:00", stadiums[6]);
                        setMatch(schedules, logos[9], "33R", teams_short[9], "2025-04-19 Sat", "23:00", stadiums[9]);
                        setMatch(schedules, logos[8], "34R", teams_short[8], "2025-04-26 Sat", "23:00", stadiums[6]);

                        setMatch(schedules, logos[12], "35R", teams_short[12], "2025-05-03 Sat", "23:00", stadiums[6]);
                        setMatch(schedules, logos[15], "36R", teams_short[15], "2025-05-10 Sat", "23:00", stadiums[15]);
                        setMatch(schedules, logos[14], "37R", teams_short[14], "2025-05-18 Sun", "23:00", stadiums[6]);
                        setMatch(schedules, logos[16], "38R", teams_short[16], "2025-05-26 Mon", "00:00", stadiums[16]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[4], "1R", teams_short[4], "2024-08-18 Sun", "22:00", stadiums[4], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[19], "2R", teams_short[19], "2024-08-24 Sat", "23:00", stadiums[7], scores[0], scores[2], results[2]);

                        setMatch(schedules, logos[6], "3R", teams_short[6], "2024-09-01 Sun", "21:30", stadiums[6], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[11], "4R", teams_short[11], "2024-09-14 Sat", "23:00", stadiums[7], scores[2], scores[2], results[1]);
                        setMatch(schedules, logos[14], "5R", teams_short[14], "2024-09-22 Sun", "01:30", stadiums[7], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[8], "6R", teams_short[8], "2024-09-28 Sat", "23:00", stadiums[8], scores[2], scores[1], results[2]);

                        setMatch(schedules, logos[12], "7R", teams_short[12], "2024-10-05 Sat", "20:30", stadiums[7], scores[0], scores[1], results[2]);
                        setMatch(schedules, logos[16], "8R", teams_short[16], "2024-10-22 Tue", "04:00", stadiums[16], scores[1], scores[0], results[2]);
                        setMatch(schedules, logos[18], "9R", teams_short[18], "2024-10-27 Sun", "23:00", stadiums[7], scores[1], scores[0], results[0]);

                        setMatch(schedules, logos[20], "10R", teams_short[20], "2024-11-03 Sun", "02:30", stadiums[20], scores[2], scores[2], results[1]);
                        setMatch(schedules, logos[9], "11R", teams_short[9], "2024-11-10 Sun", "00:00", stadiums[7], scores[0], scores[2], results[2]);
                        setMatch(schedules, logos[2], "12R", teams_short[2], "2024-11-24 Sun", "00:00", stadiums[2], scores[2], scores[2], results[1]);

                        setMatch(schedules, logos[15], "13R", teams_short[15], "2024-12-01 Sun", "00:00", stadiums[7], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[10], "14R", teams_short[10], "2024-12-04 Wed", "04:30", stadiums[10], scores[0], scores[1], results[0]);
                        setMatch(schedules, logos[13], "15R", teams_short[13], "2024-12-08 Sun", "00:00", stadiums[7]);
                        setMatch(schedules, logos[5], "16R", teams_short[5], "2024-12-15 Sun", "23:00", stadiums[5]);
                        setMatch(schedules, logos[1], "17R", teams_short[1], "2024-12-22 Sun", "02:30", stadiums[7]);
                        setMatch(schedules, logos[3], "18R", teams_short[3], "2024-12-27 Fri", "00:00", stadiums[3]);
                        setMatch(schedules, logos[17], "19R", teams_short[17], "2024-12-30 Mon", "00:00", stadiums[7]);

                        setMatch(schedules, logos[6], "20R", teams_short[6], "2025-01-05 Sun", "00:00", stadiums[7]);
                        setMatch(schedules, logos[11], "21R", teams_short[11], "2025-01-16 Thu", "04:30", stadiums[11]);
                        setMatch(schedules, logos[19], "22R", teams_short[19], "2025-01-19 Sun", "00:00", stadiums[19]);
                        setMatch(schedules, logos[4], "23R", teams_short[4], "2025-01-26 Sun", "23:00", stadiums[7]);

                        setMatch(schedules, logos[14], "24R", teams_short[14], "2025-02-02 Sun", "00:00", stadiums[14]);
                        setMatch(schedules, logos[8], "25R", teams_short[8], "2025-02-16 Sun", "00:00", stadiums[7]);
                        setMatch(schedules, logos[9], "26R", teams_short[9], "2025-02-23 Sun", "00:00", stadiums[9]);
                        setMatch(schedules, logos[2], "27R", teams_short[2], "2025-02-26 Wed", "05:00", stadiums[7]);

                        setMatch(schedules, logos[10], "28R", teams_short[10], "2025-03-09 Sun", "00:00", stadiums[7]);
                        setMatch(schedules, logos[15], "29R", teams_short[15], "2025-03-16 Sun", "00:00", stadiums[15]);

                        setMatch(schedules, logos[17], "30R", teams_short[17], "2025-04-03 Thu", "03:45", stadiums[17]);
                        setMatch(schedules, logos[5], "31R", teams_short[5], "2025-04-05 Sat", "23:00", stadiums[7]);
                        setMatch(schedules, logos[13], "32R", teams_short[13], "2025-04-12 Sat", "23:00", stadiums[13]);
                        setMatch(schedules, logos[3], "33R", teams_short[3], "2025-04-19 Sat", "23:00", stadiums[7]);
                        setMatch(schedules, logos[1], "34R", teams_short[1], "2025-04-26 Sat", "23:00", stadiums[1]);

                        setMatch(schedules, logos[16], "35R", teams_short[16], "2025-05-03 Sat", "23:00", stadiums[7]);
                        setMatch(schedules, logos[18], "36R", teams_short[18], "2025-05-10 Sat", "23:00", stadiums[18]);
                        setMatch(schedules, logos[20], "37R", teams_short[20], "2025-05-18 Sun", "23:00", stadiums[7]);
                        setMatch(schedules, logos[12], "38R", teams_short[12], "2025-05-26 Mon", "00:00", stadiums[12]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[5], "1R", teams_short[5], "2024-08-17 Sat", "23:00", stadiums[8], scores[0], scores[3], results[2]);
                        setMatch(schedules, logos[18], "2R", teams_short[18], "2024-08-24 Sat", "23:00", stadiums[18], scores[4], scores[0], results[2]);
                        setMatch(schedules, logos[3], "3R", teams_short[3], "2024-08-31 Sat", "23:00", stadiums[8], scores[2], scores[3], results[2]);

                        setMatch(schedules, logos[2], "4R", teams_short[2], "2024-09-15 Sun", "01:30", stadiums[2], scores[3], scores[2], results[2]);
                        setMatch(schedules, logos[11], "5R", teams_short[11], "2024-09-21 Sat", "23:00", stadiums[11], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[7], "6R", teams_short[7], "2024-09-28 Sat", "23:00", stadiums[8], scores[2], scores[1], results[0]);

                        setMatch(schedules, logos[15], "7R", teams_short[15], "2024-10-06 Sun", "01:30", stadiums[8], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[10], "8R", teams_short[10], "2024-10-19 Sat", "23:15", stadiums[10], scores[0], scores[2], results[0]);
                        setMatch(schedules, logos[9], "9R", teams_short[9], "2024-10-27 Sun", "01:30", stadiums[8], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[17], "10R", teams_short[17], "2024-11-03 Sun", "00:00", stadiums[17], scores[1], scores[0], results[2]);
                        setMatch(schedules, logos[19], "11R", teams_short[19], "2024-11-10 Sun", "00:00", stadiums[19], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[4], "12R", teams_short[4], "2024-11-24 Sun", "00:00", stadiums[8], scores[0], scores[0], results[1]);

                        setMatch(schedules, logos[14], "13R", teams_short[14], "2024-12-01 Sun", "22:30", stadiums[14], scores[4], scores[0], results[2]);
                        setMatch(schedules, logos[20], "14R", teams_short[20], "2024-12-05 Thu", "04:30", stadiums[8], scores[4], scores[0], results[0]);
                        setMatch(schedules, logos[12], "15R", teams_short[12], "2024-12-07 Sat", "21:30", stadiums[8]);
                        setMatch(schedules, logos[1], "16R", teams_short[1], "2024-12-15 Sun", "00:00", stadiums[1]);
                        setMatch(schedules, logos[6], "17R", teams_short[6], "2024-12-22 Sun", "23:00", stadiums[8]);
                        setMatch(schedules, logos[13], "18R", teams_short[13], "2024-12-26 Thu", "21:30", stadiums[13]);
                        setMatch(schedules, logos[16], "19R", teams_short[16], "2024-12-30 Mon", "00:00", stadiums[8]);

                        setMatch(schedules, logos[3], "20R", teams_short[3], "2025-01-05 Sun", "00:00", stadiums[3]);
                        setMatch(schedules, logos[2], "21R", teams_short[2], "2025-01-16 Thu", "04:30", stadiums[8]);
                        setMatch(schedules, logos[18], "22R", teams_short[18], "2025-01-19 Sun", "23:00", stadiums[8]);
                        setMatch(schedules, logos[5], "23R", teams_short[5], "2025-01-26 Sun", "00:00", stadiums[5]);

                        setMatch(schedules, logos[11], "24R", teams_short[11], "2025-02-02 Sun", "00:00", stadiums[8]);
                        setMatch(schedules, logos[7], "25R", teams_short[7], "2025-02-16 Sun", "00:00", stadiums[7]);
                        setMatch(schedules, logos[14], "26R", teams_short[14], "2025-02-23 Sun", "00:00", stadiums[8]);
                        setMatch(schedules, logos[4], "27R", teams_short[4], "2025-02-26 Wed", "04:45", stadiums[4]);

                        setMatch(schedules, logos[20], "28R", teams_short[20], "2025-03-09 Sun", "00:00", stadiums[20]);
                        setMatch(schedules, logos[19], "29R", teams_short[19], "2025-03-16 Sun", "00:00", stadiums[8]);

                        setMatch(schedules, logos[12], "30R", teams_short[12], "2025-04-03 Thu", "04:00", stadiums[12]);
                        setMatch(schedules, logos[1], "31R", teams_short[1], "2025-04-05 Sat", "23:00", stadiums[8]);
                        setMatch(schedules, logos[16], "32R", teams_short[16], "2025-04-12 Sat", "23:00", stadiums[16]);
                        setMatch(schedules, logos[13], "33R", teams_short[13], "2025-04-19 Sat", "23:00", stadiums[8]);
                        setMatch(schedules, logos[6], "34R", teams_short[6], "2025-04-26 Sat", "23:00", stadiums[6]);

                        setMatch(schedules, logos[10], "35R", teams_short[10], "2025-05-03 Sat", "23:00", stadiums[8]);
                        setMatch(schedules, logos[9], "36R", teams_short[9], "2025-05-10 Sat", "23:00", stadiums[9]);
                        setMatch(schedules, logos[17], "37R", teams_short[17], "2025-05-18 Sun", "23:00", stadiums[8]);
                        setMatch(schedules, logos[15], "38R", teams_short[15], "2025-05-26 Mon", "00:00", stadiums[15]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 9:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[14], "1R", teams_short[14], "2024-08-17 Sat", "04:00", stadiums[14], scores[1], scores[0], results[2]);
                        setMatch(schedules, logos[11], "2R", teams_short[11], "2024-08-24 Sat", "23:00", stadiums[9], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[10], "3R", teams_short[10], "2024-08-31 Sat", "23:00", stadiums[10], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[19], "4R", teams_short[19], "2024-09-14 Sat", "23:00", stadiums[9], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[15], "5R", teams_short[15], "2024-09-21 Sat", "23:00", stadiums[9], scores[3], scores[1], results[0]);
                        setMatch(schedules, logos[16], "6R", teams_short[16], "2024-09-28 Sat", "23:00", stadiums[16], scores[0], scores[1], results[0]);

                        setMatch(schedules, logos[13], "7R", teams_short[13], "2024-10-05 Sat", "23:00", stadiums[13], scores[3], scores[2], results[2]);
                        setMatch(schedules, logos[2], "8R", teams_short[2], "2024-10-19 Sat", "23:00", stadiums[9], scores[1], scores[3], results[2]);
                        setMatch(schedules, logos[8], "9R", teams_short[8], "2024-10-27 Sun", "01:30", stadiums[8], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[4], "10R", teams_short[4], "2024-11-05 Tue", "05:00", stadiums[9], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[7], "11R", teams_short[7], "2024-11-10 Sun", "00:00", stadiums[7], scores[0], scores[2], results[0]);
                        setMatch(schedules, logos[20], "12R", teams_short[20], "2024-11-24 Sun", "00:00", stadiums[9], scores[1], scores[4], results[2]);

                        setMatch(schedules, logos[18], "13R", teams_short[18], "2024-12-01 Sun", "22:30", stadiums[18], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[5], "14R", teams_short[5], "2024-12-06 Fri", "04:30", stadiums[9]);
                        setMatch(schedules, logos[1], "15R", teams_short[1], "2024-12-08 Sun", "23:00", stadiums[9]);
                        setMatch(schedules, logos[12], "16R", teams_short[12], "2024-12-15 Sun", "00:00", stadiums[12]);
                        setMatch(schedules, logos[17], "17R", teams_short[17], "2024-12-22 Sun", "23:00", stadiums[9]);
                        setMatch(schedules, logos[6], "18R", teams_short[6], "2024-12-27 Fri", "00:00", stadiums[6]);
                        setMatch(schedules, logos[3], "19R", teams_short[3], "2024-12-30 Mon", "00:00", stadiums[9]);

                        setMatch(schedules, logos[10], "20R", teams_short[10], "2025-01-05 Sun", "23:00", stadiums[9]);
                        setMatch(schedules, logos[19], "21R", teams_short[19], "2025-01-15 Wed", "04:30", stadiums[19]);
                        setMatch(schedules, logos[11], "22R", teams_short[11], "2025-01-19 Sun", "00:00", stadiums[11]);
                        setMatch(schedules, logos[14], "23R", teams_short[14], "2025-01-27 Mon", "04:00", stadiums[9]);

                        setMatch(schedules, logos[15], "24R", teams_short[15], "2025-02-02 Sun", "00:00", stadiums[15]);
                        setMatch(schedules, logos[16], "25R", teams_short[16], "2025-02-16 Sun", "00:00", stadiums[9]);
                        setMatch(schedules, logos[7], "26R", teams_short[7], "2025-02-23 Sun", "00:00", stadiums[9]);
                        setMatch(schedules, logos[20], "27R", teams_short[20], "2025-02-26 Wed", "04:45", stadiums[20]);

                        setMatch(schedules, logos[5], "28R", teams_short[5], "2025-03-09 Sun", "00:00", stadiums[5]);
                        setMatch(schedules, logos[18], "29R", teams_short[18], "2025-03-16 Sun", "00:00", stadiums[9]);

                        setMatch(schedules, logos[1], "30R", teams_short[1], "2025-04-02 Wed", "03:45", stadiums[1]);
                        setMatch(schedules, logos[12], "31R", teams_short[12], "2025-04-05 Sat", "23:00", stadiums[9]);
                        setMatch(schedules, logos[3], "32R", teams_short[3], "2025-04-12 Sat", "23:00", stadiums[3]);
                        setMatch(schedules, logos[6], "33R", teams_short[6], "2025-04-19 Sat", "23:00", stadiums[9]);
                        setMatch(schedules, logos[17], "34R", teams_short[17], "2025-04-26 Sat", "23:00", stadiums[17]);

                        setMatch(schedules, logos[2], "35R", teams_short[2], "2025-05-03 Sat", "23:00", stadiums[2]);
                        setMatch(schedules, logos[8], "36R", teams_short[8], "2025-05-10 Sat", "23:00", stadiums[9]);
                        setMatch(schedules, logos[4], "37R", teams_short[4], "2025-05-18 Sun", "23:00", stadiums[4]);
                        setMatch(schedules, logos[13], "38R", teams_short[13], "2025-05-26 Mon", "00:00", stadiums[9]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 10:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[12], "1R", teams_short[12], "2024-08-17 Sat", "20:30", stadiums[10], scores[0], scores[2], results[2]);
                        setMatch(schedules, logos[13], "2R", teams_short[13], "2024-08-24 Sat", "23:00", stadiums[13], scores[4], scores[1], results[2]);
                        setMatch(schedules, logos[9], "3R", teams_short[9], "2024-08-31 Sat", "23:00", stadiums[10], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[5], "4R", teams_short[5], "2024-09-14 Sat", "23:00", stadiums[5], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[17], "5R", teams_short[17], "2024-09-21 Sat", "23:00", stadiums[17], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[2], "6R", teams_short[2], "2024-09-29 Sun", "22:00", stadiums[10], scores[2], scores[2], results[1]);

                        setMatch(schedules, logos[19], "7R", teams_short[19], "2024-10-05 Sat", "23:00", stadiums[19], scores[4], scores[1], results[2]);
                        setMatch(schedules, logos[8], "8R", teams_short[8], "2024-10-19 Sat", "23:15", stadiums[10], scores[0], scores[2], results[2]);
                        setMatch(schedules, logos[4], "9R", teams_short[4], "2024-10-26 Sat", "23:00", stadiums[4], scores[4], scores[3], results[2]);

                        setMatch(schedules, logos[11], "10R", teams_short[11], "2024-11-03 Sun", "00:00", stadiums[10], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[18], "11R", teams_short[18], "2024-11-10 Sun", "23:00", stadiums[18], scores[1], scores[2], results[0]);
                        setMatch(schedules, logos[14], "12R", teams_short[14], "2024-11-25 Mon", "01:30", stadiums[10], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[16], "13R", teams_short[16], "2024-12-01 Sun", "00:00", stadiums[16], scores[1], scores[0], results[2]);
                        setMatch(schedules, logos[7], "14R", teams_short[7], "2024-12-04 Wed", "04:30", stadiums[10], scores[0], scores[1], results[2]);
                        setMatch(schedules, logos[3], "15R", teams_short[3], "2024-12-08 Sun", "23:00", stadiums[10]);
                        setMatch(schedules, logos[20], "16R", teams_short[20], "2024-12-15 Sun", "00:00", stadiums[20]);
                        setMatch(schedules, logos[15], "17R", teams_short[15], "2024-12-22 Sun", "00:00", stadiums[10]);
                        setMatch(schedules, logos[1], "18R", teams_short[1], "2024-12-28 Sat", "05:15", stadiums[1]);
                        setMatch(schedules, logos[6], "19R", teams_short[6], "2024-12-31 Tue", "04:45", stadiums[10]);

                        setMatch(schedules, logos[9], "20R", teams_short[9], "2025-01-05 Sun", "23:00", stadiums[9]);
                        setMatch(schedules, logos[5], "21R", teams_short[5], "2025-01-17 Fri", "04:30", stadiums[10]);
                        setMatch(schedules, logos[13], "22R", teams_short[13], "2025-01-20 Mon", "01:30", stadiums[10]);
                        setMatch(schedules, logos[12], "23R", teams_short[12], "2025-01-26 Sun", "00:00", stadiums[12]);

                        setMatch(schedules, logos[17], "24R", teams_short[17], "2025-02-02 Sun", "00:00", stadiums[10]);
                        setMatch(schedules, logos[2], "25R", teams_short[2], "2025-02-16 Sun", "00:00", stadiums[2]);
                        setMatch(schedules, logos[18], "26R", teams_short[18], "2025-02-23 Sun", "00:00", stadiums[10]);
                        setMatch(schedules, logos[14], "27R", teams_short[14], "2025-02-27 Thu", "05:00", stadiums[14]);

                        setMatch(schedules, logos[7], "28R", teams_short[7], "2025-03-09 Sun", "00:00", stadiums[7]);
                        setMatch(schedules, logos[16], "29R", teams_short[16], "2025-03-16 Sun", "00:00", stadiums[10]);

                        setMatch(schedules, logos[3], "30R", teams_short[3], "2025-04-02 Wed", "03:45", stadiums[3]);
                        setMatch(schedules, logos[20], "31R", teams_short[20], "2025-04-05 Sat", "23:00", stadiums[10]);
                        setMatch(schedules, logos[6], "32R", teams_short[6], "2025-04-12 Sat", "23:00", stadiums[6]);
                        setMatch(schedules, logos[1], "33R", teams_short[1], "2025-04-19 Sat", "23:00", stadiums[10]);
                        setMatch(schedules, logos[15], "34R", teams_short[15], "2025-04-26 Sat", "23:00", stadiums[15]);

                        setMatch(schedules, logos[8], "35R", teams_short[8], "2025-05-03 Sat", "23:00", stadiums[8]);
                        setMatch(schedules, logos[4], "36R", teams_short[4], "2025-05-10 Sat", "23:00", stadiums[10]);
                        setMatch(schedules, logos[11], "37R", teams_short[11], "2025-05-18 Sun", "23:00", stadiums[11]);
                        setMatch(schedules, logos[19], "38R", teams_short[19], "2025-05-26 Mon", "00:00", stadiums[10]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 11:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);
                        setMatch(schedules, logos[18], "1R", teams_short[18], "2024-08-20 Tue", "04:00", stadiums[11], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[9], "2R", teams_short[9], "2024-08-24 Sat", "23:00", stadiums[9], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[2], "3R", teams_short[2], "2024-08-31 Sat", "23:00", stadiums[11], scores[1], scores[2], results[2]);

                        setMatch(schedules, logos[7], "4R", teams_short[7], "2024-09-14 Sat", "23:00", stadiums[7], scores[2], scores[2], results[1]);
                        setMatch(schedules, logos[8], "5R", teams_short[8], "2024-09-21 Sat", "23:00", stadiums[11], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[1], "6R", teams_short[1], "2024-09-28 Sat", "23:00", stadiums[1], scores[4], scores[2], results[2]);

                        setMatch(schedules, logos[3], "7R", teams_short[3], "2024-10-05 Sat", "04:00", stadiums[11], scores[1], scores[0], results[0]);
                        setMatch(schedules, logos[17], "8R", teams_short[17], "2024-10-19 Sat", "23:00", stadiums[17], scores[2], scores[3], results[0]);
                        setMatch(schedules, logos[16], "9R", teams_short[16], "2024-10-26 Sat", "04:00", stadiums[11], scores[1], scores[3], results[2]);

                        setMatch(schedules, logos[10], "10R", teams_short[10], "2024-11-03 Sun", "00:00", stadiums[10], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[14], "11R", teams_short[14], "2024-11-10 Sun", "23:00", stadiums[14], scores[3], scores[0], results[2]);
                        setMatch(schedules, logos[6], "12R", teams_short[6], "2024-11-23 Sat", "21:30", stadiums[11], scores[1], scores[2], results[2]);

                        setMatch(schedules, logos[4], "13R", teams_short[4], "2024-12-01 Sun", "00:00", stadiums[4], scores[4], scores[1], results[2]);
                        setMatch(schedules, logos[19], "14R", teams_short[19], "2024-12-04 Wed", "05:15", stadiums[11], scores[3], scores[1], results[0]);
                        setMatch(schedules, logos[5], "15R", teams_short[5], "2024-12-08 Sun", "23:00", stadiums[11]);
                        setMatch(schedules, logos[15], "16R", teams_short[15], "2024-12-15 Sun", "00:00", stadiums[15]);
                        setMatch(schedules, logos[20], "17R", teams_short[20], "2024-12-22 Sun", "23:00", stadiums[11]);
                        setMatch(schedules, logos[12], "18R", teams_short[12], "2024-12-27 Fri", "05:00", stadiums[12]);
                        setMatch(schedules, logos[13], "19R", teams_short[13], "2024-12-29 Sun", "23:30", stadiums[11]);

                        setMatch(schedules, logos[2], "20R", teams_short[2], "2025-01-05 Sun", "00:00", stadiums[2]);
                        setMatch(schedules, logos[7], "21R", teams_short[7], "2025-01-16 Thu", "04:30", stadiums[11]);
                        setMatch(schedules, logos[9], "22R", teams_short[9], "2025-01-19 Sun", "00:00", stadiums[11]);
                        setMatch(schedules, logos[18], "23R", teams_short[18], "2025-01-26 Sun", "23:00", stadiums[18]);

                        setMatch(schedules, logos[8], "24R", teams_short[8], "2025-02-02 Sun", "00:00", stadiums[8]);
                        setMatch(schedules, logos[1], "25R", teams_short[1], "2025-02-16 Sun", "00:00", stadiums[11]);
                        setMatch(schedules, logos[4], "26R", teams_short[4], "2025-02-23 Sun", "00:00", stadiums[11]);
                        setMatch(schedules, logos[19], "27R", teams_short[19], "2025-02-26 Wed", "04:45", stadiums[19]);

                        setMatch(schedules, logos[6], "28R", teams_short[6], "2025-03-09 Sun", "00:00", stadiums[6]);
                        setMatch(schedules, logos[14], "29R", teams_short[14], "2025-03-16 Sun", "00:00", stadiums[11]);

                        setMatch(schedules, logos[13], "30R", teams_short[13], "2025-04-03 Thu", "03:45", stadiums[13]);
                        setMatch(schedules, logos[15], "31R", teams_short[15], "2025-04-05 Sat", "23:00", stadiums[11]);
                        setMatch(schedules, logos[5], "32R", teams_short[5], "2025-04-12 Sat", "23:00", stadiums[5]);
                        setMatch(schedules, logos[12], "33R", teams_short[12], "2025-04-19 Sat", "23:00", stadiums[11]);
                        setMatch(schedules, logos[20], "34R", teams_short[20], "2025-04-26 Sat", "23:00", stadiums[20]);

                        setMatch(schedules, logos[17], "35R", teams_short[17], "2025-05-03 Sat", "23:00", stadiums[11]);
                        setMatch(schedules, logos[16], "36R", teams_short[16], "2025-05-10 Sat", "23:00", stadiums[16]);
                        setMatch(schedules, logos[10], "37R", teams_short[10], "2025-05-18 Sun", "23:00", stadiums[11]);
                        setMatch(schedules, logos[3], "38R", teams_short[3], "2025-05-26 Mon", "00:00", stadiums[3]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 12:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[10], "1R", teams_short[10], "2024-08-17 Sat", "20:30", stadiums[10], scores[0], scores[2], results[0]);
                        setMatch(schedules, logos[4], "2R", teams_short[4], "2024-08-26 Mon", "00:30", stadiums[12], scores[2], scores[0], results[0]);

                        setMatch(schedules, logos[14], "3R", teams_short[14], "2024-09-02 Mon", "00:00", stadiums[14], scores[0], scores[3], results[0]);
                        setMatch(schedules, logos[16], "4R", teams_short[16], "2024-09-14 Sat", "23:00", stadiums[12], scores[0], scores[1], results[2]);
                        setMatch(schedules, logos[3], "5R", teams_short[3], "2024-09-21 Sat", "23:00", stadiums[12], scores[3], scores[0], results[0]);
                        setMatch(schedules, logos[20], "6R", teams_short[20], "2024-09-29 Sun", "01:30", stadiums[20], scores[1], scores[2], results[0]);

                        setMatch(schedules, logos[7], "7R", teams_short[7], "2024-10-05 Sat", "20:30", stadiums[7], scores[0], scores[1], results[0]);
                        setMatch(schedules, logos[6], "8R", teams_short[6], "2024-10-21 Mon", "00:30", stadiums[12], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[1], "9R", teams_short[1], "2024-10-28 Mon", "01:30", stadiums[1], scores[2], scores[2], results[1]);

                        setMatch(schedules, logos[5], "10R", teams_short[5], "2024-11-03 Sun", "00:00", stadiums[12], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[2], "11R", teams_short[2], "2024-11-10 Sun", "05:00", stadiums[12], scores[2], scores[0], results[0]);
                        setMatch(schedules, logos[17], "12R", teams_short[17], "2024-11-24 Sun", "23:00", stadiums[17], scores[2], scores[3], results[0]);

                        setMatch(schedules, logos[13], "13R", teams_short[13], "2024-12-02 Mon", "01:00", stadiums[12], scores[2], scores[0], results[0]);
                        setMatch(schedules, logos[15], "14R", teams_short[15], "2024-12-05 Thu", "04:00", stadiums[15], scores[3], scores[3], results[1]);
                        setMatch(schedules, logos[8], "15R", teams_short[8], "2024-12-07 Sat", "21:30", stadiums[8]);
                        setMatch(schedules, logos[9], "16R", teams_short[9], "2024-12-15 Sun", "00:00", stadiums[12]);
                        setMatch(schedules, logos[18], "17R", teams_short[18], "2024-12-23 Mon", "01:30", stadiums[18]);
                        setMatch(schedules, logos[11], "18R", teams_short[11], "2024-12-27 Fri", "05:00", stadiums[12]);
                        setMatch(schedules, logos[19], "19R", teams_short[19], "2024-12-30 Mon", "02:15", stadiums[19]);

                        setMatch(schedules, logos[14], "20R", teams_short[14], "2025-01-06 Mon", "01:30", stadiums[12]);
                        setMatch(schedules, logos[16], "21R", teams_short[16], "2025-01-15 Wed", "05:00", stadiums[16]);
                        setMatch(schedules, logos[4], "22R", teams_short[4], "2025-01-19 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[10], "23R", teams_short[10], "2025-01-26 Sun", "00:00", stadiums[12]);

                        setMatch(schedules, logos[3], "24R", teams_short[3], "2025-02-02 Sun", "00:00", stadiums[3]);
                        setMatch(schedules, logos[20], "25R", teams_short[20], "2025-02-16 Sun", "00:00", stadiums[12]);
                        setMatch(schedules, logos[13], "26R", teams_short[13], "2025-02-23 Sun", "00:00", stadiums[13]);
                        setMatch(schedules, logos[15], "27R", teams_short[15], "2025-02-27 Thu", "05:00", stadiums[12]);

                        setMatch(schedules, logos[17], "28R", teams_short[17], "2025-03-09 Sun", "00:00", stadiums[12]);
                        setMatch(schedules, logos[2], "29R", teams_short[2], "2025-03-16 Sun", "00:00", stadiums[2]);

                        setMatch(schedules, logos[8], "30R", teams_short[8], "2025-04-03 Thu", "04:00", stadiums[12]);
                        setMatch(schedules, logos[9], "31R", teams_short[9], "2025-04-05 Sat", "23:00", stadiums[9]);
                        setMatch(schedules, logos[19], "32R", teams_short[19], "2025-04-12 Sat", "23:00", stadiums[12]);
                        setMatch(schedules, logos[11], "33R", teams_short[11], "2025-04-19 Sat", "23:00", stadiums[11]);
                        setMatch(schedules, logos[18], "34R", teams_short[18], "2025-04-26 Sat", "23:00", stadiums[12]);

                        setMatch(schedules, logos[6], "35R", teams_short[6], "2025-05-03 Sat", "23:00", stadiums[6]);
                        setMatch(schedules, logos[1], "36R", teams_short[1], "2025-05-10 Sat", "23:00", stadiums[12]);
                        setMatch(schedules, logos[5], "37R", teams_short[5], "2025-05-18 Sun", "23:00", stadiums[5]);
                        setMatch(schedules, logos[7], "38R", teams_short[7], "2025-05-26 Mon", "00:00", stadiums[12]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 13:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);
                        setMatch(schedules, logos[6], "1R", teams_short[6], "2024-08-19 Mon", "00:30", stadiums[6], scores[0], scores[2], results[0]);
                        setMatch(schedules, logos[10], "2R", teams_short[10], "2024-08-24 Sat", "23:00", stadiums[13], scores[4], scores[1], results[0]);

                        setMatch(schedules, logos[19], "3R", teams_short[19], "2024-09-01 Sun", "01:30", stadiums[19], scores[1], scores[3], results[0]);
                        setMatch(schedules, logos[4], "4R", teams_short[4], "2024-09-14 Sat", "23:00", stadiums[13], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[1], "5R", teams_short[1], "2024-09-23 Mon", "00:30", stadiums[13], scores[2], scores[2], results[1]);
                        setMatch(schedules, logos[15], "6R", teams_short[15], "2024-09-28 Sat", "20:30", stadiums[15], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[9], "7R", teams_short[9], "2024-10-05 Sat", "23:00", stadiums[13], scores[3], scores[2], results[0]);
                        setMatch(schedules, logos[20], "8R", teams_short[20], "2024-10-20 Sun", "22:00", stadiums[20], scores[1], scores[2], results[0]);
                        setMatch(schedules, logos[17], "9R", teams_short[17], "2024-10-26 Sat", "23:00", stadiums[13], scores[1], scores[0], results[0]);

                        setMatch(schedules, logos[3], "10R", teams_short[3], "2024-11-03 Sun", "00:00", stadiums[3], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[5], "11R", teams_short[5], "2024-11-10 Sun", "02:30", stadiums[5], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[18], "12R", teams_short[18], "2024-11-24 Sun", "02:30", stadiums[13], scores[0], scores[4], results[2]);

                        setMatch(schedules, logos[12], "13R", teams_short[12], "2024-12-02 Mon", "01:00", stadiums[12], scores[2], scores[0], results[2]);
                        setMatch(schedules, logos[16], "14R", teams_short[16], "2024-12-05 Thu", "04:30", stadiums[13], scores[3], scores[0], results[0]);
                        setMatch(schedules, logos[7], "15R", teams_short[7], "2024-12-08 Sun", "00:00", stadiums[7]);
                        setMatch(schedules, logos[14], "16R", teams_short[14], "2024-12-16 Mon", "01:30", stadiums[13]);
                        setMatch(schedules, logos[2], "17R", teams_short[2], "2024-12-21 Sat", "21:30", stadiums[2]);
                        setMatch(schedules, logos[8], "18R", teams_short[8], "2024-12-26 Thu", "21:30", stadiums[13]);
                        setMatch(schedules, logos[11], "19R", teams_short[11], "2024-12-29 Sun", "23:30", stadiums[11]);

                        setMatch(schedules, logos[19], "20R", teams_short[19], "2025-01-05 Sun", "00:00", stadiums[13]);
                        setMatch(schedules, logos[4], "21R", teams_short[4], "2025-01-15 Wed", "04:30", stadiums[4]);
                        setMatch(schedules, logos[10], "22R", teams_short[10], "2025-01-20 Mon", "01:30", stadiums[10]);
                        setMatch(schedules, logos[6], "23R", teams_short[6], "2025-01-26 Sun", "02:30", stadiums[13]);

                        setMatch(schedules, logos[1], "24R", teams_short[1], "2025-02-02 Sun", "00:00", stadiums[1]);
                        setMatch(schedules, logos[15], "25R", teams_short[15], "2025-02-16 Sun", "00:00", stadiums[13]);
                        setMatch(schedules, logos[12], "26R", teams_short[12], "2025-02-23 Sun", "00:00", stadiums[13]);
                        setMatch(schedules, logos[18], "27R", teams_short[18], "2025-02-26 Wed", "04:45", stadiums[18]);

                        setMatch(schedules, logos[16], "28R", teams_short[16], "2025-03-09 Sun", "00:00", stadiums[16]);
                        setMatch(schedules, logos[5], "29R", teams_short[5], "2025-03-16 Sun", "00:00", stadiums[13]);

                        setMatch(schedules, logos[11], "30R", teams_short[11], "2025-04-03 Thu", "03:45", stadiums[13]);
                        setMatch(schedules, logos[14], "31R", teams_short[14], "2025-04-05 Sat", "23:00", stadiums[14]);
                        setMatch(schedules, logos[7], "32R", teams_short[7], "2025-04-12 Sat", "23:00", stadiums[13]);
                        setMatch(schedules, logos[8], "33R", teams_short[8], "2025-04-19 Sat", "23:00", stadiums[8]);
                        setMatch(schedules, logos[2], "34R", teams_short[2], "2025-04-26 Sat", "23:00", stadiums[13]);

                        setMatch(schedules, logos[20], "35R", teams_short[20], "2025-05-03 Sat", "23:00", stadiums[13]);
                        setMatch(schedules, logos[17], "36R", teams_short[17], "2025-05-10 Sat", "23:00", stadiums[17]);
                        setMatch(schedules, logos[3], "37R", teams_short[3], "2025-05-17 Sat", "23:00", stadiums[13]);
                        setMatch(schedules, logos[9], "38R", teams_short[9], "2025-05-26 Mon", "00:00", stadiums[9]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 14:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[9], "1R", teams_short[9], "2024-08-17 Sat", "04:00", stadiums[14], scores[1], scores[0], results[0]);
                        setMatch(schedules, logos[5], "2R", teams_short[5], "2024-08-24 Sat", "20:30", stadiums[5], scores[2], scores[1], results[2]);

                        setMatch(schedules, logos[12], "3R", teams_short[12], "2024-09-02 Mon", "00:00", stadiums[14], scores[0], scores[3], results[2]);
                        setMatch(schedules, logos[17], "4R", teams_short[17], "2024-09-14 Sat", "20:30", stadiums[17], scores[0], scores[3], results[0]);
                        setMatch(schedules, logos[7], "5R", teams_short[7], "2024-09-22 Sun", "01:30", stadiums[7], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[18], "6R", teams_short[18], "2024-09-30 Mon", "00:30", stadiums[14], scores[0], scores[3], results[2]);

                        setMatch(schedules, logos[2], "7R", teams_short[2], "2024-10-06 Sun", "22:00", stadiums[2], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[4], "8R", teams_short[4], "2024-10-19 Sat", "23:00", stadiums[14], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[19], "9R", teams_short[19], "2024-10-27 Sun", "23:00", stadiums[19], scores[2], scores[1], results[2]);

                        setMatch(schedules, logos[6], "10R", teams_short[6], "2024-11-04 Mon", "01:30", stadiums[14], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[11], "11R", teams_short[11], "2024-11-10 Sun", "23:00", stadiums[14], scores[3], scores[0], results[0]);
                        setMatch(schedules, logos[10], "12R", teams_short[10], "2024-11-25 Mon", "01:30", stadiums[10], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[8], "13R", teams_short[8], "2024-12-01 Sun", "22:30", stadiums[14], scores[4], scores[0], results[0]);
                        setMatch(schedules, logos[1], "14R", teams_short[1], "2024-12-05 Thu", "05:15", stadiums[1], scores[2], scores[0], results[2]);
                        setMatch(schedules, logos[16], "15R", teams_short[16], "2024-12-08 Sun", "02:30", stadiums[14]);
                        setMatch(schedules, logos[13], "16R", teams_short[13], "2024-12-16 Mon", "01:30", stadiums[13]);
                        setMatch(schedules, logos[3], "17R", teams_short[3], "2024-12-22 Sun", "23:00", stadiums[14]);
                        setMatch(schedules, logos[20], "18R", teams_short[20], "2024-12-27 Fri", "02:30", stadiums[20]);
                        setMatch(schedules, logos[15], "19R", teams_short[15], "2024-12-31 Tue", "05:00", stadiums[14]);

                        setMatch(schedules, logos[12], "20R", teams_short[12], "2025-01-06 Mon", "01:30", stadiums[12]);
                        setMatch(schedules, logos[17], "21R", teams_short[17], "2025-01-17 Fri", "05:00", stadiums[14]);
                        setMatch(schedules, logos[5], "22R", teams_short[5], "2025-01-19 Sun", "23:00", stadiums[14]);
                        setMatch(schedules, logos[9], "23R", teams_short[9], "2025-01-27 Mon", "04:00", stadiums[9]);

                        setMatch(schedules, logos[7], "24R", teams_short[7], "2025-02-02 Sun", "00:00", stadiums[14]);
                        setMatch(schedules, logos[18], "25R", teams_short[18], "2025-02-16 Sun", "00:00", stadiums[18]);
                        setMatch(schedules, logos[8], "26R", teams_short[8], "2025-02-23 Sun", "00:00", stadiums[8]);
                        setMatch(schedules, logos[10], "27R", teams_short[10], "2025-02-27 Thu", "05:00", stadiums[14]);

                        setMatch(schedules, logos[1], "28R", teams_short[1], "2025-03-09 Sun", "00:00", stadiums[14]);
                        setMatch(schedules, logos[11], "29R", teams_short[11], "2025-03-16 Sun", "00:00", stadiums[11]);

                        setMatch(schedules, logos[16], "30R", teams_short[16], "2025-04-02 Wed", "03:45", stadiums[16]);
                        setMatch(schedules, logos[13], "31R", teams_short[13], "2025-04-05 Sat", "23:00", stadiums[14]);
                        setMatch(schedules, logos[15], "32R", teams_short[15], "2025-04-12 Sat", "23:00", stadiums[15]);
                        setMatch(schedules, logos[20], "33R", teams_short[20], "2025-04-19 Sat", "23:00", stadiums[14]);
                        setMatch(schedules, logos[3], "34R", teams_short[3], "2025-04-26 Sat", "23:00", stadiums[3]);

                        setMatch(schedules, logos[4], "35R", teams_short[4], "2025-05-03 Sat", "23:00", stadiums[4]);
                        setMatch(schedules, logos[19], "36R", teams_short[19], "2025-05-10 Sat", "23:00", stadiums[14]);
                        setMatch(schedules, logos[6], "37R", teams_short[6], "2025-05-18 Sun", "23:00", stadiums[6]);
                        setMatch(schedules, logos[2], "38R", teams_short[2], "2025-05-26 Mon", "00:00", stadiums[14]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 15:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[17], "1R", teams_short[17], "2024-08-17 Sat", "23:00", stadiums[15], scores[1], scores[0], results[0]);
                        setMatch(schedules, logos[3], "2R", teams_short[3], "2024-08-25 Sun", "22:00", stadiums[3], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[18], "3R", teams_short[18], "2024-09-01 Sun", "21:30", stadiums[15], scores[2], scores[1], results[0]);
                        setMatch(schedules, logos[20], "4R", teams_short[20], "2024-09-16 Mon", "00:30", stadiums[20], scores[1], scores[2], results[0]);
                        setMatch(schedules, logos[9], "5R", teams_short[9], "2024-09-21 Sat", "23:00", stadiums[9], scores[3], scores[1], results[2]);
                        setMatch(schedules, logos[13], "6R", teams_short[13], "2024-09-28 Sat", "20:30", stadiums[15], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[8], "7R", teams_short[8], "2024-10-06 Sun", "01:30", stadiums[8], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[5], "8R", teams_short[5], "2024-10-19 Sat", "23:00", stadiums[15], scores[0], scores[1], results[2]);
                        setMatch(schedules, logos[6], "9R", teams_short[6], "2024-10-27 Sun", "23:00", stadiums[6], scores[2], scores[1], results[2]);

                        setMatch(schedules, logos[1], "10R", teams_short[1], "2024-11-02 Sat", "21:30", stadiums[15], scores[1], scores[0], results[0]);
                        setMatch(schedules, logos[16], "11R", teams_short[16], "2024-11-10 Sun", "23:00", stadiums[16], scores[1], scores[3], results[0]);
                        setMatch(schedules, logos[19], "12R", teams_short[19], "2024-11-26 Thu", "05:00", stadiums[15], scores[0], scores[2], results[2]);

                        setMatch(schedules, logos[7], "13R", teams_short[7], "2024-12-01 Sun", "00:00", stadiums[7], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[12], "14R", teams_short[12], "2024-12-05 Thu", "04:00", stadiums[15], scores[3], scores[3], results[1]);
                        setMatch(schedules, logos[4], "15R", teams_short[4], "2024-12-08 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[11], "16R", teams_short[11], "2024-12-15 Sun", "00:00", stadiums[15]);
                        setMatch(schedules, logos[10], "17R", teams_short[10], "2024-12-22 Sun", "00:00", stadiums[10]);
                        setMatch(schedules, logos[2], "18R", teams_short[2], "2024-12-27 Fri", "00:00", stadiums[15]);
                        setMatch(schedules, logos[14], "19R", teams_short[14], "2024-12-31 Tue", "05:00", stadiums[14]);

                        setMatch(schedules, logos[18], "20R", teams_short[18], "2025-01-04 Sat", "21:30", stadiums[18]);
                        setMatch(schedules, logos[20], "21R", teams_short[20], "2025-01-16 Thu", "04:30", stadiums[15]);
                        setMatch(schedules, logos[3], "22R", teams_short[3], "2025-01-18 Sat", "21:30", stadiums[15]);
                        setMatch(schedules, logos[17], "23R", teams_short[17], "2025-01-26 Sun", "00:00", stadiums[17]);

                        setMatch(schedules, logos[9], "24R", teams_short[9], "2025-02-02 Sun", "00:00", stadiums[15]);
                        setMatch(schedules, logos[13], "25R", teams_short[13], "2025-02-16 Sun", "00:00", stadiums[13]);
                        setMatch(schedules, logos[16], "26R", teams_short[16], "2025-02-23 Sun", "00:00", stadiums[15]);
                        setMatch(schedules, logos[12], "27R", teams_short[12], "2025-02-27 Thu", "05:00", stadiums[12]);

                        setMatch(schedules, logos[19], "28R", teams_short[19], "2025-03-09 Sun", "00:00", stadiums[19]);
                        setMatch(schedules, logos[7], "29R", teams_short[7], "2025-03-16 Sun", "00:00", stadiums[15]);

                        setMatch(schedules, logos[4], "30R", teams_short[4], "2025-04-03 Thu", "03:45", stadiums[15]);
                        setMatch(schedules, logos[11], "31R", teams_short[11], "2025-04-05 Sat", "23:00", stadiums[11]);
                        setMatch(schedules, logos[14], "32R", teams_short[14], "2025-04-12 Sat", "23:00", stadiums[15]);
                        setMatch(schedules, logos[2], "33R", teams_short[2], "2025-04-19 Sat", "23:00", stadiums[2]);
                        setMatch(schedules, logos[10], "34R", teams_short[10], "2025-04-26 Sat", "23:00", stadiums[15]);

                        setMatch(schedules, logos[5], "35R", teams_short[5], "2025-05-03 Sat", "23:00", stadiums[5]);
                        setMatch(schedules, logos[6], "36R", teams_short[6], "2025-05-10 Sat", "23:00", stadiums[15]);
                        setMatch(schedules, logos[1], "37R", teams_short[1], "2025-05-17 Sat", "23:00", stadiums[1]);
                        setMatch(schedules, logos[8], "38R", teams_short[8], "2025-05-26 Mon", "00:00", stadiums[15]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 16:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[3], "1R", teams_short[3], "2024-08-17 Sat", "23:00", stadiums[16], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[17], "2R", teams_short[17], "2024-08-24 Sat", "23:00", stadiums[17], scores[0], scores[1], results[0]);
                        setMatch(schedules, logos[20], "3R", teams_short[20], "2024-08-31 Sat", "23:00", stadiums[16], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[12], "4R", teams_short[12], "2024-09-14 Sat", "23:00", stadiums[12], scores[0], scores[1], results[0]);
                        setMatch(schedules, logos[5], "5R", teams_short[5], "2024-09-22 Sun", "22:00", stadiums[5], scores[2], scores[2], results[1]);
                        setMatch(schedules, logos[9], "6R", teams_short[9], "2024-09-28 Sat", "23:00", stadiums[16], scores[0], scores[1], results[2]);

                        setMatch(schedules, logos[6], "7R", teams_short[6], "2024-10-06 Sun", "22:00", stadiums[6], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[7], "8R", teams_short[7], "2024-10-22 Tue", "04:00", stadiums[16], scores[1], scores[0], results[0]);
                        setMatch(schedules, logos[11], "9R", teams_short[11], "2024-10-26 Sat", "04:00", stadiums[11], scores[1], scores[3], results[0]);

                        setMatch(schedules, logos[19], "10R", teams_short[19], "2024-11-03 Sun", "00:00", stadiums[16], scores[3], scores[0], results[0]);
                        setMatch(schedules, logos[15], "11R", teams_short[15], "2024-11-10 Sun", "23:00", stadiums[16], scores[1], scores[3], results[2]);
                        setMatch(schedules, logos[1], "12R", teams_short[1], "2024-11-24 Sun", "00:00", stadiums[1], scores[3], scores[0], results[2]);

                        setMatch(schedules, logos[10], "13R", teams_short[10], "2024-12-01 Sun", "00:00", stadiums[16], scores[1], scores[0], results[0]);
                        setMatch(schedules, logos[13], "14R", teams_short[13], "2024-12-05 Thu", "04:30", stadiums[13], scores[3], scores[0], results[2]);
                        setMatch(schedules, logos[14], "15R", teams_short[14], "2024-12-08 Sun", "02:30", stadiums[14]);
                        setMatch(schedules, logos[2], "16R", teams_short[2], "2024-12-15 Sun", "02:30", stadiums[16]);
                        setMatch(schedules, logos[4], "17R", teams_short[4], "2024-12-22 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[18], "18R", teams_short[18], "2024-12-27 Fri", "00:00", stadiums[16]);
                        setMatch(schedules, logos[8], "19R", teams_short[8], "2024-12-30 Mon", "00:00", stadiums[8]);

                        setMatch(schedules, logos[20], "20R", teams_short[20], "2025-01-07 Tue", "05:00", stadiums[20]);
                        setMatch(schedules, logos[12], "21R", teams_short[12], "2025-01-15 Wed", "05:00", stadiums[16]);
                        setMatch(schedules, logos[17], "22R", teams_short[17], "2025-01-19 Sun", "23:00", stadiums[16]);
                        setMatch(schedules, logos[3], "23R", teams_short[3], "2025-01-26 Sun", "00:00", stadiums[3]);

                        setMatch(schedules, logos[5], "24R", teams_short[5], "2025-02-02 Sun", "00:00", stadiums[16]);
                        setMatch(schedules, logos[9], "25R", teams_short[9], "2025-02-16 Sun", "00:00", stadiums[9]);
                        setMatch(schedules, logos[15], "26R", teams_short[15], "2025-02-23 Sun", "00:00", stadiums[15]);
                        setMatch(schedules, logos[1], "27R", teams_short[1], "2025-02-26 Wed", "04:45", stadiums[16]);

                        setMatch(schedules, logos[13], "28R", teams_short[13], "2025-03-09 Sun", "00:00", stadiums[16]);
                        setMatch(schedules, logos[10], "29R", teams_short[10], "2025-03-16 Sun", "00:00", stadiums[10]);

                        setMatch(schedules, logos[14], "30R", teams_short[14], "2025-04-02 Wed", "03:45", stadiums[16]);
                        setMatch(schedules, logos[2], "31R", teams_short[2], "2025-04-05 Sat", "23:00", stadiums[2]);
                        setMatch(schedules, logos[8], "32R", teams_short[8], "2025-04-12 Sat", "23:00", stadiums[16]);
                        setMatch(schedules, logos[18], "33R", teams_short[18], "2025-04-19 Sat", "23:00", stadiums[18]);
                        setMatch(schedules, logos[4], "34R", teams_short[4], "2025-04-26 Sat", "23:00", stadiums[16]);

                        setMatch(schedules, logos[7], "35R", teams_short[7], "2025-05-03 Sat", "23:00", stadiums[7]);
                        setMatch(schedules, logos[11], "36R", teams_short[11], "2025-05-10 Sat", "23:00", stadiums[16]);
                        setMatch(schedules, logos[19], "37R", teams_short[19], "2025-05-18 Sun", "23:00", stadiums[19]);
                        setMatch(schedules, logos[6], "38R", teams_short[6], "2025-05-26 Mon", "00:00", stadiums[16]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 17:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[15], "1R", teams_short[15], "2024-08-17 Sat", "23:00", stadiums[15], scores[1], scores[0], results[2]);
                        setMatch(schedules, logos[16], "2R", teams_short[16], "2024-08-24 Sat", "23:00", stadiums[17], scores[0], scores[1], results[2]);
                        setMatch(schedules, logos[4], "3R", teams_short[4], "2024-08-31 Sat", "23:00", stadiums[4], scores[3], scores[1], results[2]);

                        setMatch(schedules, logos[14], "4R", teams_short[14], "2024-09-14 Sat", "20:30", stadiums[17], scores[0], scores[3], results[2]);
                        setMatch(schedules, logos[10], "5R", teams_short[10], "2024-09-21 Sat", "23:00", stadiums[17], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[3], "6R", teams_short[3], "2024-10-01 Tue", "04:00", stadiums[3], scores[3], scores[1], results[2]);
                        setMatch(schedules, logos[1], "7R", teams_short[1], "2024-10-05 Sat", "23:00", stadiums[1], scores[3], scores[1], results[2]);
                        setMatch(schedules, logos[11], "8R", teams_short[11], "2024-10-19 Sat", "23:00", stadiums[17], scores[2], scores[3], results[2]);
                        setMatch(schedules, logos[13], "9R", teams_short[13], "2024-10-26 Sat", "23:00", stadiums[13], scores[1], scores[0], results[2]);

                        setMatch(schedules, logos[8], "10R", teams_short[8], "2024-11-03 Sun", "00:00", stadiums[17], scores[1], scores[0], results[0]);
                        setMatch(schedules, logos[20], "11R", teams_short[20], "2024-11-10 Sun", "00:00", stadiums[20], scores[2], scores[0], results[2]);
                        setMatch(schedules, logos[12], "12R", teams_short[12], "2024-11-24 Sun", "23:00", stadiums[17], scores[2], scores[3], results[2]);
                        setMatch(schedules, logos[5], "13R", teams_short[5], "2024-11-30 Sat", "05:00", stadiums[5], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[6], "14R", teams_short[6], "2024-12-05 Thu", "04:30", stadiums[17], scores[1], scores[5], results[2]);
                        setMatch(schedules, logos[2], "15R", teams_short[2], "2024-12-08 Sun", "00:00", stadiums[2]);
                        setMatch(schedules, logos[18], "16R", teams_short[18], "2024-12-16 Mon", "04:00", stadiums[17]);
                        setMatch(schedules, logos[9], "17R", teams_short[9], "2024-12-22 Sun", "23:00", stadiums[9]);
                        setMatch(schedules, logos[19], "18R", teams_short[19], "2024-12-27 Fri", "00:00", stadiums[17]);
                        setMatch(schedules, logos[7], "19R", teams_short[7], "2024-12-30 Mon", "00:00", stadiums[7]);

                        setMatch(schedules, logos[4], "20R", teams_short[4], "2025-01-05 Sun", "00:00", stadiums[17]);
                        setMatch(schedules, logos[14], "21R", teams_short[14], "2025-01-17 Fri", "05:00", stadiums[14]);
                        setMatch(schedules, logos[16], "22R", teams_short[16], "2025-01-19 Sun", "23:00", stadiums[16]);
                        setMatch(schedules, logos[15], "23R", teams_short[15], "2025-01-26 Sun", "00:00", stadiums[17]);

                        setMatch(schedules, logos[10], "24R", teams_short[10], "2025-02-02 Sun", "00:00", stadiums[10]);
                        setMatch(schedules, logos[3], "25R", teams_short[3], "2025-02-16 Sun", "00:00", stadiums[17]);
                        setMatch(schedules, logos[5], "26R", teams_short[5], "2025-02-23 Sun", "00:00", stadiums[17]);
                        setMatch(schedules, logos[6], "27R", teams_short[6], "2025-02-27 Thu", "04:45", stadiums[6]);

                        setMatch(schedules, logos[12], "28R", teams_short[12], "2025-03-09 Sun", "00:00", stadiums[12]);
                        setMatch(schedules, logos[20], "29R", teams_short[20], "2025-03-16 Sun", "00:00", stadiums[17]);

                        setMatch(schedules, logos[7], "30R", teams_short[7], "2025-04-03 Thu", "03:45", stadiums[17]);
                        setMatch(schedules, logos[18], "31R", teams_short[18], "2025-04-05 Sat", "23:00", stadiums[18]);
                        setMatch(schedules, logos[2], "32R", teams_short[2], "2025-04-12 Sat", "23:00", stadiums[17]);
                        setMatch(schedules, logos[19], "33R", teams_short[19], "2025-04-19 Sat", "23:00", stadiums[19]);
                        setMatch(schedules, logos[9], "34R", teams_short[9], "2025-04-26 Sat", "23:00", stadiums[17]);

                        setMatch(schedules, logos[11], "35R", teams_short[11], "2025-05-03 Sat", "23:00", stadiums[11]);
                        setMatch(schedules, logos[13], "36R", teams_short[13], "2025-05-10 Sat", "23:00", stadiums[17]);
                        setMatch(schedules, logos[8], "37R", teams_short[8], "2025-05-18 Sun", "23:00", stadiums[8]);
                        setMatch(schedules, logos[1], "38R", teams_short[1], "2025-05-26 Mon", "00:00", stadiums[17]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 18:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);
                        setMatch(schedules, logos[11], "1R", teams_short[11], "2024-08-20 Tue", "04:00", stadiums[11], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[8], "2R", teams_short[8], "2024-08-24 Sat", "23:00", stadiums[18], scores[4], scores[0], results[0]);

                        setMatch(schedules, logos[15], "3R", teams_short[15], "2024-09-01 Sun", "21:30", stadiums[15], scores[2], scores[1], results[2]);
                        setMatch(schedules, logos[1], "4R", teams_short[1], "2024-09-15 Sun", "22:00", stadiums[18], scores[0], scores[1], results[2]);
                        setMatch(schedules, logos[4], "5R", teams_short[4], "2024-09-21 Sat", "23:00", stadiums[18], scores[3], scores[1], results[0]);
                        setMatch(schedules, logos[14], "6R", teams_short[14], "2024-09-30 Mon", "00:30", stadiums[14], scores[0], scores[3], results[0]);

                        setMatch(schedules, logos[5], "7R", teams_short[5], "2024-10-07 Mon", "00:30", stadiums[5], scores[3], scores[2], results[2]);
                        setMatch(schedules, logos[19], "8R", teams_short[19], "2024-10-19 Sat", "20:30", stadiums[18], scores[4], scores[1], results[0]);
                        setMatch(schedules, logos[7], "9R", teams_short[7], "2024-10-27 Sun", "23:00", stadiums[7], scores[1], scores[0], results[2]);

                        setMatch(schedules, logos[2], "10R", teams_short[2], "2024-11-03 Sun", "23:00", stadiums[18], scores[4], scores[1], results[0]);
                        setMatch(schedules, logos[10], "11R", teams_short[10], "2024-11-10 Sun", "23:00", stadiums[18], scores[1], scores[2], results[2]);
                        setMatch(schedules, logos[13], "12R", teams_short[13], "2024-11-24 Sun", "02:30", stadiums[13], scores[0], scores[4], results[0]);

                        setMatch(schedules, logos[9], "13R", teams_short[9], "2024-12-01 Sun", "22:30", stadiums[18], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[3], "14R", teams_short[3], "2024-12-06 Fri", "05:15", stadiums[3]);
                        setMatch(schedules, logos[6], "15R", teams_short[6], "2024-12-09 Mon", "01:30", stadiums[18]);
                        setMatch(schedules, logos[17], "16R", teams_short[17], "2024-12-16 Mon", "04:00", stadiums[17]);
                        setMatch(schedules, logos[12], "17R", teams_short[12], "2024-12-23 Mon", "01:30", stadiums[18]);
                        setMatch(schedules, logos[16], "18R", teams_short[16], "2024-12-27 Fri", "00:00", stadiums[16]);
                        setMatch(schedules, logos[20], "19R", teams_short[20], "2024-12-30 Mon", "00:00", stadiums[18]);

                        setMatch(schedules, logos[15], "20R", teams_short[15], "2025-01-04 Sat", "21:30", stadiums[18]);
                        setMatch(schedules, logos[1], "21R", teams_short[1], "2025-01-16 Thu", "05:00", stadiums[1]);
                        setMatch(schedules, logos[8], "22R", teams_short[8], "2025-01-19 Sun", "23:00", stadiums[8]);
                        setMatch(schedules, logos[11], "23R", teams_short[11], "2025-01-26 Sun", "23:00", stadiums[18]);

                        setMatch(schedules, logos[4], "24R", teams_short[4], "2025-02-02 Sun", "00:00", stadiums[4]);
                        setMatch(schedules, logos[14], "25R", teams_short[14], "2025-02-16 Sun", "00:00", stadiums[18]);
                        setMatch(schedules, logos[10], "26R", teams_short[10], "2025-02-23 Sun", "00:00", stadiums[10]);
                        setMatch(schedules, logos[13], "27R", teams_short[13], "2025-02-26 Wed", "04:45", stadiums[18]);

                        setMatch(schedules, logos[3], "28R", teams_short[3], "2025-03-09 Sun", "00:00", stadiums[18]);
                        setMatch(schedules, logos[9], "29R", teams_short[9], "2025-03-16 Sun", "00:00", stadiums[9]);

                        setMatch(schedules, logos[6], "30R", teams_short[6], "2025-04-03 Thu", "03:45", stadiums[6]);
                        setMatch(schedules, logos[17], "31R", teams_short[17], "2025-04-05 Sat", "23:00", stadiums[18]);
                        setMatch(schedules, logos[20], "32R", teams_short[20], "2025-04-12 Sat", "23:00", stadiums[20]);
                        setMatch(schedules, logos[16], "33R", teams_short[16], "2025-04-19 Sat", "23:00", stadiums[18]);
                        setMatch(schedules, logos[12], "34R", teams_short[12], "2025-04-26 Sat", "23:00", stadiums[12]);

                        setMatch(schedules, logos[19], "35R", teams_short[19], "2025-05-03 Sat", "23:00", stadiums[19]);
                        setMatch(schedules, logos[7], "36R", teams_short[7], "2025-05-10 Sat", "23:00", stadiums[18]);
                        setMatch(schedules, logos[2], "37R", teams_short[2], "2025-05-18 Sun", "23:00", stadiums[2]);
                        setMatch(schedules, logos[5], "38R", teams_short[5], "2025-05-26 Mon", "00:00", stadiums[18]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 19:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);

                        setMatch(schedules, logos[2], "1R", teams_short[2], "2024-08-18 Sun", "01:30", stadiums[19], scores[1], scores[2], results[2]);
                        setMatch(schedules, logos[7], "2R", teams_short[7], "2024-08-24 Sat", "23:00", stadiums[7], scores[0], scores[2], results[0]);

                        setMatch(schedules, logos[13], "3R", teams_short[13], "2024-09-01 Sun", "01:30", stadiums[19], scores[1], scores[3], results[2]);
                        setMatch(schedules, logos[9], "4R", teams_short[9], "2024-09-14 Sat", "23:00", stadiums[9], scores[1], scores[1], results[1]);
                        setMatch(schedules, logos[6], "5R", teams_short[6], "2024-09-21 Sat", "20:30", stadiums[19], scores[0], scores[3], results[2]);
                        setMatch(schedules, logos[4], "6R", teams_short[4], "2024-09-28 Sat", "23:00", stadiums[4], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[10], "7R", teams_short[10], "2024-10-05 Sat", "23:00", stadiums[19], scores[4], scores[1], results[0]);
                        setMatch(schedules, logos[18], "8R", teams_short[18], "2024-10-19 Sat", "20:30", stadiums[18], scores[4], scores[1], results[2]);
                        setMatch(schedules, logos[14], "9R", teams_short[14], "2024-10-27 Sun", "23:00", stadiums[19], scores[2], scores[1], results[0]);

                        setMatch(schedules, logos[16], "10R", teams_short[16], "2024-11-03 Sun", "00:00", stadiums[16], scores[3], scores[0], results[2]);
                        setMatch(schedules, logos[8], "11R", teams_short[8], "2024-11-10 Sun", "00:00", stadiums[19], scores[0], scores[0], results[1]);
                        setMatch(schedules, logos[15], "12R", teams_short[15], "2024-11-26 Thu", "05:00", stadiums[15], scores[0], scores[2], results[0]);

                        setMatch(schedules, logos[1], "13R", teams_short[1], "2024-12-01 Sun", "02:30", stadiums[19], scores[2], scores[5], results[2]);
                        setMatch(schedules, logos[11], "14R", teams_short[11], "2024-12-04 Wed", "05:15", stadiums[11], scores[3], scores[1], results[2]);
                        setMatch(schedules, logos[20], "15R", teams_short[20], "2024-12-10 Tue", "05:00", stadiums[19]);
                        setMatch(schedules, logos[3], "16R", teams_short[3], "2024-12-17 Tue", "05:00", stadiums[3]);
                        setMatch(schedules, logos[5], "17R", teams_short[5], "2024-12-22 Sun", "00:00", stadiums[19]);
                        setMatch(schedules, logos[17], "18R", teams_short[17], "2024-12-27 Fri", "00:00", stadiums[17]);
                        setMatch(schedules, logos[12], "19R", teams_short[12], "2024-12-30 Mon", "02:15", stadiums[19]);

                        setMatch(schedules, logos[13], "20R", teams_short[13], "2025-01-05 Sun", "00:00", stadiums[13]);
                        setMatch(schedules, logos[9], "21R", teams_short[9], "2025-01-15 Wed", "04:30", stadiums[19]);
                        setMatch(schedules, logos[7], "22R", teams_short[7], "2025-01-19 Sun", "00:00", stadiums[19]);
                        setMatch(schedules, logos[2], "23R", teams_short[2], "2025-01-27 Mon", "01:30", stadiums[2]);

                        setMatch(schedules, logos[6], "24R", teams_short[6], "2025-02-02 Sun", "00:00", stadiums[6]);
                        setMatch(schedules, logos[4], "25R", teams_short[4], "2025-02-16 Sun", "00:00", stadiums[19]);
                        setMatch(schedules, logos[1], "26R", teams_short[1], "2025-02-23 Sun", "00:00", stadiums[1]);
                        setMatch(schedules, logos[11], "27R", teams_short[11], "2025-02-26 Wed", "04:45", stadiums[19]);

                        setMatch(schedules, logos[15], "28R", teams_short[15], "2025-03-09 Sun", "00:00", stadiums[19]);
                        setMatch(schedules, logos[8], "29R", teams_short[8], "2025-03-16 Sun", "00:00", stadiums[8]);

                        setMatch(schedules, logos[20], "30R", teams_short[20], "2025-04-02 Wed", "03:45", stadiums[20]);
                        setMatch(schedules, logos[3], "31R", teams_short[3], "2025-04-05 Sat", "23:00", stadiums[19]);
                        setMatch(schedules, logos[12], "32R", teams_short[12], "2025-04-12 Sat", "23:00", stadiums[12]);
                        setMatch(schedules, logos[17], "33R", teams_short[17], "2025-04-19 Sat", "23:00", stadiums[19]);
                        setMatch(schedules, logos[5], "34R", teams_short[5], "2025-04-26 Sat", "23:00", stadiums[5]);

                        setMatch(schedules, logos[18], "35R", teams_short[18], "2025-05-03 Sat", "23:00", stadiums[19]);
                        setMatch(schedules, logos[14], "36R", teams_short[14], "2025-05-10 Sat", "23:00", stadiums[14]);
                        setMatch(schedules, logos[16], "37R", teams_short[16], "2025-05-18 Sun", "23:00", stadiums[19]);
                        setMatch(schedules, logos[10], "38R", teams_short[10], "2025-05-26 Mon", "00:00", stadiums[10]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                    case 20:
                        imageView.setImageResource(logos[position]);
                        imageView.setVisibility(View.VISIBLE);

                        listView.setVisibility(View.VISIBLE);
                        setMatch(schedules, logos[1], "1R", teams_short[1], "2024-08-17 Sat", "23:00", stadiums[1], scores[2], scores[0], results[2]);
                        setMatch(schedules, logos[6], "2R", teams_short[6], "2024-08-25 Sun", "22:00", stadiums[20], scores[2], scores[6], results[2]);
                        setMatch(schedules, logos[16], "3R", teams_short[16], "2024-08-31 Sat", "23:00", stadiums[16], scores[1], scores[1], results[1]);

                        setMatch(schedules, logos[15], "4R", teams_short[15], "2024-09-16 Mon", "00:30", stadiums[20], scores[1], scores[2], results[2]);
                        setMatch(schedules, logos[2], "5R", teams_short[2], "2024-09-21 Sat", "23:00", stadiums[2], scores[3], scores[1], results[2]);
                        setMatch(schedules, logos[12], "6R", teams_short[12], "2024-09-29 Sun", "01:30", stadiums[20], scores[1], scores[2], results[2]);

                        setMatch(schedules, logos[4], "7R", teams_short[4], "2024-10-05 Sat", "23:00", stadiums[4], scores[5], scores[3], results[2]);
                        setMatch(schedules, logos[13], "8R", teams_short[13], "2024-10-20 Sun", "22:00", stadiums[20], scores[1], scores[2], results[2]);
                        setMatch(schedules, logos[5], "9R", teams_short[5], "2024-10-26 Sat", "23:00", stadiums[5], scores[2], scores[2], results[1]);

                        setMatch(schedules, logos[7], "10R", teams_short[7], "2024-11-03 Sun", "02:30", stadiums[20], scores[2], scores[2], results[1]);
                        setMatch(schedules, logos[17], "11R", teams_short[17], "2024-11-10 Sun", "00:00", stadiums[20], scores[2], scores[0], results[0]);
                        setMatch(schedules, logos[9], "12R", teams_short[9], "2024-11-24 Sun", "00:00", stadiums[9], scores[1], scores[4], results[0]);

                        setMatch(schedules, logos[3], "13R", teams_short[3], "2024-12-01 Sun", "00:00", stadiums[20], scores[2], scores[4], results[2]);
                        setMatch(schedules, logos[8], "14R", teams_short[8], "2024-12-05 Thu", "04:30", stadiums[8], scores[4], scores[0], results[2]);
                        setMatch(schedules, logos[19], "15R", teams_short[19], "2024-12-10 Tue", "05:00", stadiums[19]);
                        setMatch(schedules, logos[10], "16R", teams_short[10], "2024-12-15 Sun", "00:00", stadiums[20]);
                        setMatch(schedules, logos[11], "17R", teams_short[11], "2024-12-22 Sun", "23:00", stadiums[11]);
                        setMatch(schedules, logos[14], "18R", teams_short[14], "2024-12-27 Fri", "02:30", stadiums[20]);
                        setMatch(schedules, logos[18], "19R", teams_short[18], "2024-12-30 Mon", "00:00", stadiums[18]);

                        setMatch(schedules, logos[16], "20R", teams_short[16], "2025-01-07 Tue", "05:00", stadiums[20]);
                        setMatch(schedules, logos[15], "21R", teams_short[15], "2025-01-16 Thu", "04:30", stadiums[15]);
                        setMatch(schedules, logos[6], "22R", teams_short[6], "2025-01-21 Tue", "05:00", stadiums[6]);
                        setMatch(schedules, logos[1], "23R", teams_short[1], "2025-01-26 Sun", "00:00", stadiums[20]);

                        setMatch(schedules, logos[2], "24R", teams_short[2], "2025-02-02 Sun", "00:00", stadiums[20]);
                        setMatch(schedules, logos[12], "25R", teams_short[12], "2025-02-16 Sun", "00:00", stadiums[12]);
                        setMatch(schedules, logos[3], "26R", teams_short[3], "2025-02-23 Sun", "00:00", stadiums[3]);
                        setMatch(schedules, logos[9], "27R", teams_short[9], "2025-02-26 Wed", "04:45", stadiums[20]);

                        setMatch(schedules, logos[8], "28R", teams_short[8], "2025-03-09 Sun", "00:00", stadiums[20]);
                        setMatch(schedules, logos[17], "29R", teams_short[17], "2025-03-16 Sun", "00:00", stadiums[17]);

                        setMatch(schedules, logos[19], "30R", teams_short[19], "2025-04-02 Wed", "03:45", stadiums[20]);
                        setMatch(schedules, logos[10], "31R", teams_short[10], "2025-04-05 Sat", "23:00", stadiums[10]);
                        setMatch(schedules, logos[18], "32R", teams_short[18], "2025-04-12 Sat", "23:00", stadiums[20]);
                        setMatch(schedules, logos[14], "33R", teams_short[14], "2025-04-19 Sat", "23:00", stadiums[14]);
                        setMatch(schedules, logos[11], "34R", teams_short[11], "2025-04-26 Sat", "23:00", stadiums[20]);

                        setMatch(schedules, logos[13], "35R", teams_short[13], "2025-05-03 Sat", "23:00", stadiums[13]);
                        setMatch(schedules, logos[5], "36R", teams_short[5], "2025-05-10 Sat", "23:00", stadiums[20]);
                        setMatch(schedules, logos[7], "37R", teams_short[7], "2025-05-18 Sun", "23:00", stadiums[7]);
                        setMatch(schedules, logos[4], "38R", teams_short[4], "2025-05-26 Mon", "00:00", stadiums[20]);

                        resultView.setText(win + "W " + draw + "D " + lose + "L  " + "|  " + pts + "pts");
                        resultView.setVisibility(View.VISIBLE);
                        break;
                }
                ListAdapter oAdapter = new MatchAdapter(schedules);
                listView.setAdapter(oAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textView.setText("Select");
            }
        });

    }

    public void setMatch(ArrayList<Match> schedules, int logo, String round, String team, String date, String time, String stadium, String home, String away, int result) {
        Match match = new Match();

        match.mainImage = logo;

        match.roundText = round;
        match.dateText = date;
        match.timeText = time;
        match.stadiumText = stadium;

        match.subText = "-";

        match.resultImage = result;

        haCheck = true;
        for (int i = 1; i < 21; i++) {
            if (teams_short[i] == team && stadiums[i] == stadium) {
                haCheck = false;
                break;
            }
        }

        if (result == R.drawable.zwin) {
            win += 1;
            pts += 3;
        }
        else if (result == R.drawable.zdraw) {
            draw += 1;
            pts += 1;
        }
        else {
            lose += 1;
        }

        if (haCheck) {
            match.teamText = team + " (H)";
            gf = Integer.parseInt(home);
            ga = Integer.parseInt(away);
            match.homeCText = home;
            match.awayText = away;
        }
        else {
            match.teamText = team + " (A)";
            gf = Integer.parseInt(away);
            ga = Integer.parseInt(home);
            match.homeText = home;
            match.awayCText = away;
        }

        gd += gf - ga;

        schedules.add(match);
    }

    public void setMatch(ArrayList<Match> schedules, int logo, String round, String team, String date, String time, String stadium) {
        Match match = new Match();

        match.mainImage = logo;

        match.roundText = round;
        match.dateText = date;
        match.timeText = time;
        match.stadiumText = stadium;

        haCheck = true;
        for (int i = 1; i < 21; i++) {
            if (teams_short[i] == team && stadiums[i] == stadium) {
                haCheck = false;
                break;
            }
        }

        if (haCheck) {
            match.teamText = team + " (H)";
        }
        else {
            match.teamText = team + " (A)";
        }
        schedules.add(match);
    }
}

