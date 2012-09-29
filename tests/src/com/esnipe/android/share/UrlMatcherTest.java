package com.esnipe.android.share;

import android.net.Uri;
import android.test.AndroidTestCase;

import java.util.List;

public class UrlMatcherTest extends AndroidTestCase {

    public void testFindUrls() {
        String text = "Check out this item I found on eBay:\n" +
                "\n" +
                "End time: 17.09.2012 20:23:26\n" +
                "\n" +
                "Item: Vintage Transformer G1 Octane Complete Very Good Original parts\n" +
                "\n" +
                "URL: http://pages.ebay.com/link/?nav=item.view&id=261096429532\n" +
                "\n" +
                "Alt URL: http://www.ebay.com/itm/Vintage-Transformer-G1-Octane-Complete-Very-Good-Original-parts-/261096429532\n" +
                "\n" +
                "(Sent from eBay Mobile for Android)\n";
        List<Uri> urls = UrlMatcher.findUrls(text);
        assertEquals(2, urls.size());
        assertEquals(Uri.parse("http://pages.ebay.com/link/?nav=item.view&id=261096429532"), urls.get(0));
        assertEquals(Uri.parse("http://www.ebay.com/itm/Vintage-Transformer-G1-Octane-Complete-Very-Good-Original-parts-/261096429532"),
                urls.get(1));
    }

}
