package com.esnipe.android.share;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Extracts the eBay item ID from the shared text or URL.
 */
public class EbayItemIdMatcher {

    private static class IdTest {
        Pattern pattern;
        int group;
        public IdTest(String pattern, int group) {
            this.pattern = Pattern.compile(pattern);
            this.group = group;
        }

        public String findId(CharSequence text) {
            Matcher m = this.pattern.matcher(text);
            if (m.find()) {
                return m.group(this.group);
            }
            return null;
        }
    }

    static final IdTest[] TESTS = {
            new IdTest("[^a-z]itemId=(\\d+)", 1),
            new IdTest("[^a-z]id=(\\d+)", 1),
            new IdTest("/(\\d+)$", 1),
    };

    /**
     *  Extracts the eBay item ID from the text.
     *  Returns null if ID is not found.
     */
    public static String extractItemId(CharSequence text) {
        for (IdTest test : TESTS) {
            String id = test.findId(text);
            if (id != null) {
                return id;
            }
        }
        return null;
    }

}
