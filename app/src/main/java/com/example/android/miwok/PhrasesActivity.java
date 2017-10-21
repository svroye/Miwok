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

public class PhrasesActivity extends AppCompatActivity {

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
        words.add(new Words("Where are you going?","minto wuksus",
                R.raw.phrase_where_are_you_going));
        words.add(new Words("What is your name?","tinnə oyaase'nə",R.raw.phrase_what_is_your_name));
        words.add(new Words("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Words("How are you feeling?","michəksəs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Words("I'm feeling good","kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Words("Are you coming?","əənəs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Words("Yes, I'm coming","həə'əənəm", R.raw.phrase_yes_im_coming));
        words.add(new Words("I'm coming","əənəm", R.raw.phrase_im_coming));
        words.add(new Words("Let's go","yoowutis", R.raw.phrase_lets_go));
        words.add(new Words("Come here","ənni'nem", R.raw.phrase_come_here));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter =
                new WordAdapter(this, words, R.color.category_phrases);

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
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,
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
