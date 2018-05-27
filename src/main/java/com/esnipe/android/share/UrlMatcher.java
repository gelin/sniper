package com.esnipe.android.share;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Extracts URLs from the text.
 */
public class UrlMatcher {

    /**
     *  Regexp to find URLs from here: http://www.codinghorror.com/blog/2008/10/the-problem-with-urls.html
     */
    static final Pattern URL_PATTERN = Pattern.compile(
            "\\(?\\bhttps?://[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]");

    /**
     *  Tries to find the URLs in the text.
     *  Returns list of found URLs.
     */
    public static List<Uri> findUrls(CharSequence text) {
        List<Uri> result = new ArrayList<Uri>();
        Matcher m = URL_PATTERN.matcher(text);
        while (m.find()) {
            String url = m.group();
            if (url.startsWith("(") && url.endsWith(")")) {
                url = url.substring(1, url.length() - 2);
            }
            Uri uri = Uri.parse(url);
            result.add(uri);
        }
        return result;
    }

}
