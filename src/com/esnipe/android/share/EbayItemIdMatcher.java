package com.esnipe.android.share;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Extracts the eBay item ID from the shared text or URL.
 */
public class EbayItemIdMatcher {

    static final Pattern ID_PATTERN = Pattern.compile("id=(\\d+)");
    static final int ID_GROUP = 1;

    public static String extractItemId(CharSequence text) {
        Matcher m = ID_PATTERN.matcher(text);
        if (m.find()) {
            return m.group(ID_GROUP);
        }
        return null;
    }

}
