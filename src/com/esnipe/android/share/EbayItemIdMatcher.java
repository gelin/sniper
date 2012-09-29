package com.esnipe.android.share;

import android.net.Uri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Extracts the eBay item ID from the shared text or URL.
 */
public class EbayItemIdMatcher {

    /** Pattern for item ID */
    static final Pattern ID_PATTERN = Pattern.compile("\\d+");

    /**
     *  Extracts the eBay item ID from the URL.
     *  Returns null if ID is not found.
     */
    public static String findItemId(Uri url) {
        String itemId = url.getQueryParameter("itemId");
        if (isItemId(itemId)) {
            return itemId;
        }
        String id = url.getQueryParameter("id");
        if (isItemId(id)) {
            return id;
        }
        String lastPath = url.getLastPathSegment();
        if (isItemId(lastPath)) {
            return lastPath;
        }
        return null;
    }

    static boolean isItemId(String itemId) {
        if (itemId == null) {
            return false;
        }
        Matcher m = ID_PATTERN.matcher(itemId);
        return m.matches();
    }

}
