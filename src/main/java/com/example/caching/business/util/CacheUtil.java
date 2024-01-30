package com.example.caching.business.util;

public abstract class CacheUtil {
    public static void slow(long numMillisecondsToSleep) {
        try {
            Thread.sleep(numMillisecondsToSleep);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}