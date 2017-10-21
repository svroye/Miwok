package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven on 18/10/2017.
 */

public class WordAdapter extends ArrayAdapter<Words> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param word A List of Words objects to display in a list
     */
    public WordAdapter(@NonNull Context context,  ArrayList<Words> word) {
        // call the superclass's constructor, with second parameter being 0, since
        // we are not going to use a single TextView as a layout.
        super(context, 0, word);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        // if there are not recycled Views available, inflate a new one
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Words} object located at this position in the list
        Words currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID english_word
        TextView englishTranslationTextView = (TextView) listItemView.findViewById(R.id.english_word);
        // Get the English Translation from the current Words object and
        // set this text on the TextView
        englishTranslationTextView.setText(currentWord.getEnglishTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwok_word
        TextView miwokTranslationTextView = (TextView) listItemView.findViewById(R.id.miwok_word);
        // Get the miwok Translation from the current Words object and
        // set this text on the TextView
        miwokTranslationTextView.setText(currentWord.getmMiwokTranslation());

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}