package com.example.baselabdallah.todo;

/**
 * This class represents an item in the custom list view.
 * For now this item only contains text (Further on, a boolean check might be added)
 */

public class ListViewItem {
    /**
     * A string containing the text in the list item
     */

    private String text;

    /**
     * Simple constructor
     * @param text  Add the text to the string
     */

    public ListViewItem(String text) {
        this.text = text;
    }

    /**
     * Simple getter
     * @return      the text of the list item
     */

    public String getText() {
        return text;
    }

    /**
     * Simple setter
     * @param text  set the text of the list item
     */

    public void setText(String text) {
        this.text = text;
    }


}