package com.esnipe.android.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 *  Opens the item with specified ID on the eSnipe site.
 */
public class EsnipeOpener {

    static final Uri ESNIPE_URL = Uri.parse("http://esnipe.com/snipe-it");
    static final String ESNIPE_URL_PARAM = "url";
    static final String EBAY_ITEM_URL = "http://www.ebay.com/itm/";

    Context context;

    public EsnipeOpener(Context context) {
        this.context = context;
    }

    public void openInBrowser(String itemId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, createUri(itemId));
        //startActivity(Intent.createChooser(intent, getString(R.string.open_esnipe)));
        this.context.startActivity(intent);
    }

    public void openInBrowser(Uri url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, createUri(url));
        //startActivity(Intent.createChooser(intent, getString(R.string.open_esnipe)));
        this.context.startActivity(intent);
    }

    Uri createUri(String itemId) {
        Uri.Builder builder = ESNIPE_URL.buildUpon();
        builder.appendQueryParameter(ESNIPE_URL_PARAM, EBAY_ITEM_URL + itemId);
        return builder.build();
    }

    Uri createUri(Uri url) {
        Uri.Builder builder = ESNIPE_URL.buildUpon();
        builder.appendQueryParameter(ESNIPE_URL_PARAM, url.toString());
        return builder.build();
    }


}
