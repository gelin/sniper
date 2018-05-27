package ru.gelin.android.esnipe;

import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class OpenActivity extends BaseEbayActivity {

    @Override
    protected List<Uri> extractUrls(Intent intent) {
        List<Uri> result = new ArrayList<Uri>();
        Uri data = intent.getData();
        if (data != null) {
            result.add(data);
        }
        return result;
    }
}
