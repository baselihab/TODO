package com.example.baselabdallah.todo;

import android.widget.TextView;

/**
 * This ViewHolder class is simply composed of a textview.
 * This textview represents the text of the todo object.
 */

public class ViewHolder {
    /**
     * This textview act as an attribute in the viewholder class
     */

    TextView text;

    /**
     * Simple constructor
     * @param text  Add the text to the textview
     */

    public ViewHolder(TextView text) {
        this.text = text;
    }

    /**
     * Simple getter
     * @return      the text of the text view
     */

    public TextView getText() {
        return text;
    }

    /**
     * Simple setter
     * @param text  set the text of the text view
     */

    public void setText(TextView text) {
        this.text = text;
    }
}

