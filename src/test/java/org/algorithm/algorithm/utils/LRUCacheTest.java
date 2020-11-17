package org.algorithm.algorithm.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LRUCacheTest {

    LRUCache cache;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void example1() {
        List<Integer> result = new ArrayList<>();
        cache = new LRUCache(2);
        cache.set(2, 1);
        cache.set(1, 1);
        result.add(cache.get(2));
        cache.set(4, 1);
        result.add(cache.get(1));
        result.add(cache.get(2));

        Assert.assertEquals(Arrays.asList(1, -1, 1), result);
    }

    @Test
    public void example2() {
        List<Integer> result = new ArrayList<>();
        cache = new LRUCache(1);

        cache.set(2, 1);
        result.add(cache.get(2));
        cache.set(3, 2);
        result.add(cache.get(2));
        result.add(cache.get(3));

        Assert.assertEquals(Arrays.asList(1, -1, 2), result);
    }
}