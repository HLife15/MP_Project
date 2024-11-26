package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MatchAdapter extends BaseAdapter{
    LayoutInflater layoutInflater = null;
    private ArrayList<Match> schedules = null;
    private int count;


    public MatchAdapter(ArrayList<Match> matches) {
        schedules = matches;
        count = schedules.size();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            final Context context = parent.getContext();
            if (layoutInflater == null) {
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = layoutInflater.inflate(R.layout.match, parent, false);
        }

        ImageView mainImage = convertView.findViewById(R.id.opplogo);
        ImageView resultImage = convertView.findViewById(R.id.result);

        TextView roundText = convertView.findViewById(R.id.round);
        TextView timeText = convertView.findViewById(R.id.time);
        TextView teamText = convertView.findViewById(R.id.team);
        TextView dateText = convertView.findViewById(R.id.date);
        TextView stadiumText = convertView.findViewById(R.id.stadium);
        TextView homeText = convertView.findViewById(R.id.score_home);
        TextView homeCText = convertView.findViewById(R.id.score_homeCheck);
        TextView subText = convertView.findViewById(R.id.sub);
        TextView awayText = convertView.findViewById(R.id.score_away);
        TextView awayCText = convertView.findViewById(R.id.score_awayCheck);

        mainImage.setImageResource(schedules.get(position).mainImage);
        resultImage.setImageResource(schedules.get(position).resultImage);

        roundText.setText(schedules.get(position).roundText);
        timeText.setText(schedules.get(position).timeText);
        teamText.setText(schedules.get(position).teamText);
        dateText.setText(schedules.get(position).dateText);
        stadiumText.setText(schedules.get(position).stadiumText);
        homeText.setText(schedules.get(position).homeText);
        homeCText.setText(schedules.get(position).homeCText);
        subText.setText(schedules.get(position).subText);
        awayText.setText(schedules.get(position).awayText);
        awayCText.setText(schedules.get(position).awayCText);



        return convertView;
    }

}
