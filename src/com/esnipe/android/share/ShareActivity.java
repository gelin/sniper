package com.esnipe.android.share;

import android.content.Intent;

public class ShareActivity extends BaseEbayActivity {

    @Override
    protected CharSequence extractText(Intent intent) {
        return intent.getCharSequenceExtra(Intent.EXTRA_TEXT);
    }
}
