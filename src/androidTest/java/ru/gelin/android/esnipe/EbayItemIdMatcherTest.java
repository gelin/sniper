package ru.gelin.android.esnipe;

import android.net.Uri;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class EbayItemIdMatcherTest {

    @Test
    public void testFindItemId1() {
        Uri uri = Uri.parse("http://pages.ebay.com/link/?nav=item.view&id=261096429532");
        String id = EbayItemIdMatcher.findItemId(uri);
        assertEquals("261096429532", id);
    }

    @Test
    public void testFindItemId2() {
        Uri uri = Uri.parse("http://www.ebay.com/itm/Vintage-Transformer-G1-Octane-Complete-Very-Good-Original-parts-/261096429532");
        String id = EbayItemIdMatcher.findItemId(uri);
        assertEquals("261096429532", id);
    }

    @Test
    public void testFindItemId3() {
        Uri uri = Uri.parse("http://item.mobileweb.ebay.com/viewitem;PdsSession=08f70fc113a0a5aa666209b2fffe604e?itemId=290781498457&index=0&nav=DEALS&nid=75263280032");
        String id = EbayItemIdMatcher.findItemId(uri);
        assertEquals("290781498457", id);
    }

    @Test
    public void testFindItemId4() {
        Uri uri = Uri.parse("http://www.ebay.com/itm/Karaoke-Country-Duets-Vol-1-CD-/190719985808?forcev4exp=true&forceRpt=true");
        String id = EbayItemIdMatcher.findItemId(uri);
        assertEquals("190719985808", id);
    }

    @Test
    public void testFindItemIdNoId() {
        Uri uri = Uri.parse("http://www.ebay.com/itm/noItemInThisURL");
        String id = EbayItemIdMatcher.findItemId(uri);
        assertNull(id);
    }

    @Test
    public void testFindItemIdNotId() {
        Uri uri = Uri.parse("http://www.ebay.com/itm/?id=notID");
        String id = EbayItemIdMatcher.findItemId(uri);
        assertNull(id);
    }

    @Test
    public void testFindItemId5() {
        Uri uri = Uri.parse("https://m.ebay.com/itm/Karaoke-Country-Duets-Vol-1-CD-/190719985808?forcev4exp=true&forceRpt=true");
        String id = EbayItemIdMatcher.findItemId(uri);
        assertEquals("190719985808", id);
    }

}
