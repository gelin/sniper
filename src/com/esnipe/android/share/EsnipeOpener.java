package com.esnipe.android.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *  Opens the item with specified ID on the eSnipe site.
 */
public class EsnipeOpener {

    static final String ESNIPE_URL;
    static {
        try {
            ESNIPE_URL = "http://esnipe.com/snipe-it?url=" +
                    URLEncoder.encode("http://www.ebay.com/itm/", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);  //should never happen
        }
    }

    Context context;

    public EsnipeOpener(Context context) {
        this.context = context;
    }

    public void openInBrowser(String itemId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, createUri(itemId));
        //startActivity(Intent.createChooser(intent, getString(R.string.open_esnipe)));
        this.context.startActivity(intent);
    }

    Uri createUri(String itemId) {
        return Uri.parse(ESNIPE_URL + itemId);
    }


}
