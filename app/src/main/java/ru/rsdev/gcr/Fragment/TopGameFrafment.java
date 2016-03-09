package ru.rsdev.gcr.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.rsdev.gcr.R;


public class TopGameFrafment extends Fragment {

    OnAnswer answerInterfase;
    public EditText editText;
    public TextView textView8;
    public TextView textView7;
    public Button button2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_game, null);


        button2 = (Button) v.findViewById(R.id.button2);
        editText = (EditText)v.findViewById(R.id.editText);
        editText.setText("");

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Проверка на валидность ответа
                if (editText.getText().length() > 0) {
                    String answerString = "";
                    answerString = editText.getText().toString();
                    answerInterfase.onAnswerButtonClick(answerString);
                } else
                    Toast.makeText(getActivity(), "Введите ответ", Toast.LENGTH_SHORT).show();
            }
        });

        textView8 = (TextView)v.findViewById(R.id.textView8);
        textView7 = (TextView)v.findViewById(R.id.textView7);
        answerInterfase.onAnswerButtonClick();
        return v;
    }

    public interface OnAnswer {
        public void onAnswerButtonClick(String answerString);
        public void onAnswerButtonClick();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            answerInterfase = (OnAnswer) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAnswer");
        }
    }
}
