package ru.gelin.android.esnipe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

abstract class BaseEbayActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent == null) {
            exit();
            return;
        }

        logIntent(intent);

        List<Uri> urls = extractUrls(intent);
        if (urls.isEmpty()) {
            exit();
            return;
        }

        String itemId = null;
        for (Uri url : urls) {
            itemId = EbayItemIdMatcher.findItemId(url);
            if (itemId != null) {
                Toast.makeText(this,
                        String.format(getString(R.string.opening_item), itemId), Toast.LENGTH_LONG).show();
                new EsnipeOpener(this).openInBrowser(itemId);
                finish();
                break;
            }
        }

        if (itemId == null) {
            Uri url = urls.get(0);
            Toast.makeText(this,
                    String.format(getString(R.string.opening_url), url.toString()), Toast.LENGTH_LONG).show();
            new EsnipeOpener(this).openInBrowser(url);
            finish();
        }

    }

    protected abstract List<Uri> extractUrls(Intent intent);

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
