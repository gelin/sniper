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

    public void testFindUrls2() {
        String text = "http://item.mobileweb.ebay.com/viewitem;PdsSession=08f70fc113a0a5aa666209b2fffe604e?itemId=290781498457&index=0&nav=DEALS&nid=75263280032\n";
        List<Uri> urls = UrlMatcher.findUrls(text);
        assertEquals(1, urls.size());
        assertEquals(Uri.parse("http://item.mobileweb.ebay.com/viewitem;PdsSession=08f70fc113a0a5aa666209b2fffe604e?itemId=290781498457&index=0&nav=DEALS&nid=75263280032"), urls.get(0));
    }

    public void testFindUrls3() {
        String text = "Try this URL:\n" +
                "http://www.ebay.com/itm/Karaoke-Country-Duets-Vol-1-CD-/190719985808?forcev4exp=true&forceRpt=true\n";
        List<Uri> urls = UrlMatcher.findUrls(text);
        assertEquals(1, urls.size());
        assertEquals(Uri.parse("http://www.ebay.com/itm/Karaoke-Country-Duets-Vol-1-CD-/190719985808?forcev4exp=true&forceRpt=true"), urls.get(0));
    }

    public void testFindUrlsNoUrls() {
        String text = "Hey, there is no URLs here!";
        List<Uri> urls = UrlMatcher.findUrls(text);
        assertEquals(0, urls.size());
    }

}
