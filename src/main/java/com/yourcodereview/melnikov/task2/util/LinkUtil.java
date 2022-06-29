package com.yourcodereview.melnikov.task2.util;

import lombok.extern.slf4j.Slf4j;
import org.hashids.Hashids;

@Slf4j
public class LinkUtil {
    private static final int MIN_HASH_LENGTH = 8;
    private static final String SALT_FOR_HASH = "unique string for base";

    public static String encodeLink(long linkId) {
        Hashids hashids = new Hashids(SALT_FOR_HASH, MIN_HASH_LENGTH);
        return "/l/" + hashids.encode(linkId);
    }

    public static long decodeShortLink(String shortLink) {
        Hashids hashids = new Hashids(SALT_FOR_HASH, MIN_HASH_LENGTH);
        long[] decodedIds = hashids.decode(shortLink);
        if (decodedIds.length == 0) {
            log.info("The shortLink hasn't decoded.");
            return 0L;
        }
        return decodedIds[0];
    }
}
