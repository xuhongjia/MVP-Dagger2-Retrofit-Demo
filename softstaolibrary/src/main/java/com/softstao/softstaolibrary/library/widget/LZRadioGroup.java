package com.softstao.softstaolibrary.library.widget;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class LZRadioGroup {

    List<RadioButton> radios = new ArrayList<RadioButton>();

    /**
     * Constructor, which allows you to pass number of RadioButton instances,
     * making a group.
     *
     * @param radios
     *            One RadioButton or more.
     */
    public LZRadioGroup(RadioButton... radios) {
        super();

        for (RadioButton rb : radios) {
            this.radios.add(rb);
            rb.setOnClickListener(onClick);
        }
    }

    /**
     * Constructor, which allows you to pass number of RadioButtons
     * represented by resource IDs, making a group.
     *
     * @param activity
     *            Current View (or Activity) to which those RadioButtons
     *            belong.
     * @param radiosIDs
     *            One RadioButton or more.
     */
    public LZRadioGroup(View activity, int... radiosIDs) {
        super();

        for (int radioButtonID : radiosIDs) {
            RadioButton rb = (RadioButton)activity.findViewById(radioButtonID);
            if (rb != null) {
                this.radios.add(rb);
                rb.setOnClickListener(onClick);
            }
        }
    }

    /**
     * This occurs everytime when one of RadioButtons is clicked,
     * and deselects all others in the group.
     */
    OnClickListener onClick = new OnClickListener() {

        @Override
        public void onClick(View v) {

            toggle(v);
        }
    };

    public void toggle(View v)
    {
        // let's deselect all radios in group
        for (RadioButton rb : radios) {

            ViewParent p = rb.getParent();
            if (p.getClass().equals(RadioGroup.class)) {
                // if RadioButton belongs to RadioGroup,
                // then deselect all radios in it
                RadioGroup rg = (RadioGroup) p;
                rg.clearCheck();
            } else {
                // if RadioButton DOES NOT belong to RadioGroup,
                // just deselect it
                rb.setChecked(false);
            }
        }

        // now let's select currently clicked RadioButton
        if (v.getClass().equals(RadioButton.class)) {
            RadioButton rb = (RadioButton) v;
            rb.setChecked(true);
        }
    }
}