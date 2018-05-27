package ru.gelin.android.esnipe;

import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class ShareActivity extends BaseEbayActivity {

    @Override
    protected List<Uri> extractUrls(Intent intent) {
        List<Uri> result = new ArrayList<Uri>();
        CharSequence text = intent.getCharSequenceExtra(Intent.EXTRA_TEXT);
        if (text != null) {
            result.addAll(UrlMatcher.findUrls(text));
        }
        return result;
    }

}
