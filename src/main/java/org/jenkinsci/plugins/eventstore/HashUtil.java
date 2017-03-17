package org.jenkinsci.plugins.eventstore;

import org.apache.commons.codec.digest.DigestUtils;

public final class HashUtil {
    public static String hash(String data) {
        return DigestUtils.md5Hex(data);
    }
}
