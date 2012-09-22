package com.esnipe.android.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ShareActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent == null) {
            exit();
            return;
        }

        logIntent(intent);
    }

    void logIntent(Intent intent) {
        Log.d(Tag.TAG, "action: " + intent.getAction());
        Log.d(Tag.TAG, "type: " + intent.getType());
        Log.d(Tag.TAG, "data: " + intent.getDataString());
        Log.d(Tag.TAG, "subj: " + intent.getStringExtra(Intent.EXTRA_SUBJECT));
        Log.d(Tag.TAG, "text: " + intent.getCharSequenceExtra(Intent.EXTRA_TEXT));
    }

    void exit() {
        Toast.makeText(this, R.string.cannot_open, Toast.LENGTH_LONG).show();
        finish();
    }


}
