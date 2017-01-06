package com.example.baselabdallah.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * This class aims to form a custom adapter that binds the custom list items
 * to the list view in the main activity
 */

public class CustomAdapter extends ArrayAdapter {

    /**
     * Array of list view items
     */
    private ListViewItem[] objects;

    /**
     *  Simple constructor
     * @param context the list calling the adapter
     * @param resource the variable to change
     * @param objects array of objects to propagate
     */
    public CustomAdapter(Context context, int resource, ListViewItem[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    /**
     * Override method that fills the array of objects with the wanted text
     * @param position the position of the object in the array
     * @param convertView the view that needs to get converted
     * @param parent
     * @return      the final converted view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ListViewItem listViewItem = objects[position];
        int listViewItemType = getItemViewType(position);
        TextView textView;

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            textView = (TextView) convertView.findViewById(R.id.litem);

            viewHolder = new ViewHolder(textView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.getText().setText(listViewItem.getText());

        return convertView;
    }

}