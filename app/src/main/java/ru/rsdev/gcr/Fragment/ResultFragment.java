package ru.rsdev.gcr.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.rsdev.gcr.R;

public class ResultFragment extends Fragment {

    TextView textView10;
    OnStopGame onStopGameInterfase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result, null);

        textView10 = (TextView)v.findViewById(R.id.textView10);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String msg = bundle.getString("result_game");
            if (msg != null) {
                //Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                textView10.setText(msg);

            }
        }

        Button button3 = (Button) v.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"New game", Toast.LENGTH_SHORT).show();
                onStopGameInterfase.onGameStop();

            }
        });




        return v;
    }

    public interface OnStopGame {
        public void onGameStop();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onStopGameInterfase = (OnStopGame) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }





}
