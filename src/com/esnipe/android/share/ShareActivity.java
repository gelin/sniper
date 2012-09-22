package com.esnipe.android.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShareActivity extends Activity {

    static final Pattern ID_PATTERN = Pattern.compile("id=(\\d+)");
    static final int ID_GROUP = 1;

    static final String ESNIPE_URL;
    static {
        try {
            ESNIPE_URL = "http://esnipe.com/snipe-it?url=" +
                    URLEncoder.encode("http://www.ebay.com/itm/", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);  //should never happen
        }
    }

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

        CharSequence text = intent.getCharSequenceExtra(Intent.EXTRA_TEXT);
        if (text == null) {
            exit();
            return;
        }

        String itemId = extractItemId(text);
        if (itemId == null) {
            exit();
            return;
        }
        Toast.makeText(this, getString(R.string.opening_item) + itemId, Toast.LENGTH_LONG).show();

        openInBrowser(createUrl(itemId));

        finish();
    }

    void logIntent(Intent intent) {
        Log.d(Tag.TAG, "action: " + intent.getAction());
        Log.d(Tag.TAG, "type: " + intent.getType());
        Log.d(Tag.TAG, "data: " + intent.getDataString());
        Log.d(Tag.TAG, "subj: " + intent.getStringExtra(Intent.EXTRA_SUBJECT));
        Log.d(Tag.TAG, "text: " + intent.getCharSequenceExtra(Intent.EXTRA_TEXT));
    }

    String extractItemId(CharSequence text) {
        Matcher m = ID_PATTERN.matcher(text);
        if (m.find()) {
            return m.group(ID_GROUP);
        }
        return null;
    }

    String createUrl(String itemId) {
        return ESNIPE_URL + itemId;
    }

    void openInBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    void exit() {
        Toast.makeText(this, R.string.cannot_open, Toast.LENGTH_LONG).show();
        finish();
    }


}
