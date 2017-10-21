/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    //mediaplayer object to play the audio files
    private MediaPlayer mMediaPlayer;

    // listener for when the audio has finished playing
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("father","əpə", R.drawable.family_father, R.raw.family_father));
        words.add(new Words("mother","əta", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Words("son","angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Words("daughter","tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Words("older brother","taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Words("younger brother","chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Words("older sister","tete", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Words("younger sister","kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Words("grandmother","ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Words("grandfather","paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter =
                new WordAdapter(this, words, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // create and setup the mediaplayer with the audiofile of the list item that
                // the user clicked
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this,
                        words.get(i).getAudioResource());
                // start the audio file
                mMediaPlayer.start();

                // set up an onCompletionListener, which is called when the audio file is finished
                mMediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });
    }

    private void releaseMediaPlayer(){
        // if the mediaplayer is not null
        if (mMediaPlayer != null){
            // release the resources
            mMediaPlayer.release();
            // restore the variable
            mMediaPlayer = null;
            Log.i(NumbersActivity.class.toString(), "Released");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
