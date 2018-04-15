package com.spoqk.tetris.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spoqk.tetris.R;
import com.spoqk.tetris.activities.MainActivity;


public class SideFragment extends Fragment implements View.OnClickListener
{
    private static SideFragment instance;

    public static SideFragment getInstance()
    {
        return instance;
    }

    private int level;
    private TextView levelTv;

    public void setLevel(int value)
    {
        level = value;
        MainActivity.getInstance().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                levelTv.setText(Integer.toString(level));
            }
        });
    }

    public int getLevel()
    {
        return level;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_side_panel, container, false);
        if (instance == null)
        {

            instance = this;
            levelTv = rootView.findViewById(R.id.level);
            setLevel(3);
            rootView.findViewById(R.id.pauseBtn).setOnClickListener(this);
            rootView.findViewById(R.id.exitBtn).setOnClickListener(this);
        }
        return rootView;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.pauseBtn:
                //GamePanelView.getInstance().pauseClick();
                break;
            case R.id.exitBtn:
                exit();
                break;
        }
    }

    private void exit()
    {
        MainActivity.getInstance().exitApp();
    }
}
