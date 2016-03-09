package ru.rsdev.gcr.Fragment;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.rsdev.gcr.Game.SingleGame;
import ru.rsdev.gcr.R;

public class ListGameFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_game, null);
        showDataInList();
        return v;
    }

    public void showDataInList()
    {
        ArrayList<String> list = SingleGame.getInstance().getAllAnswerList();
        ListGameAdapter adapter = new ListGameAdapter(getActivity(), list);
        setListAdapter(adapter);
    }

    public void getUserAnswer(String userAnswer){
        //allAnswer.add(0, userAnswer);
        //SingleGame.getInstance().addAndroidAnswer(userAnswer);
    }
}
