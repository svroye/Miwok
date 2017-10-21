package com.example.android.miwok;

/**
 * Created by Steven on 18/10/2017.
 */

public class Words {

    // English translation of the word
    private String mEnglishTranslation;
    // Miwok translation of the word
    private String mMiwokTranslation;

    /**
     * create a new Word object
     * @param englishTranslation is the English translation of the word
     * @param miwokTranslation is the Miwok translation of the word
     */
    public Words(String englishTranslation, String miwokTranslation ){
        mEnglishTranslation = englishTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * get the English translation of the word
     * @return English translation
     */
    public String getEnglishTranslation(){
        return mEnglishTranslation;
    }

    /**
     * get the Miwok translation
     * @return Miwok translation
     */
    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }
}
