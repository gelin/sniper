package com.esnipe.android.share;

import android.content.Intent;

public class OpenActivity extends BaseEbayActivity {

    @Override
    protected CharSequence extractText(Intent intent) {
        return intent.getDataString();
    }
}
