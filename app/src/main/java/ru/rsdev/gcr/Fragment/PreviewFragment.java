package ru.rsdev.gcr.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.rsdev.gcr.R;

public class PreviewFragment extends Fragment {

    OnStartGameListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_preview, null);
        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Work",Toast.LENGTH_SHORT).show();
                mCallback.onPrevFragButtonClick();
            }
        });
        return v;
    }

    public interface OnStartGameListener {
        public void onPrevFragButtonClick();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnStartGameListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onPrevFragButtonClick");
        }
    }
}
