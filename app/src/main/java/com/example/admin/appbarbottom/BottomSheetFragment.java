package com.example.admin.appbarbottom;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {
    TextView textClose;
    Toolbar toolbar;
    MainActivity mainActivity;
    public LinearLayout linearLayout;

    public BottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sheet_bottom, container, false);
        mainActivity = (MainActivity) getActivity();
        textClose = view.findViewById(R.id.text_close);
        linearLayout = view.findViewById(R.id.linear_layout);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setFitsSystemWindows(true);
        if (toolbar.isScrollbarFadingEnabled()) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return view;
    }

}
