package com.luisz.simpleclicker.Util;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class formateoDeNumeros {

    public static DecimalFormat formatterV1 = new DecimalFormat("###,###,###,###,###,###,###,###,###");
    public static DecimalFormat formatterDecimales = new DecimalFormat("###,###.###");

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000L, " K");
        suffixes.put(1_000_000L, " M");
        suffixes.put(1_000_000_000L, " B");
        suffixes.put(1_000_000_000_000L, " T");
        suffixes.put(1_000_000_000_000_000L, " q");
        suffixes.put(1_000_000_000_000_000_000L, " Q");
    }

    public static String formatterV2(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return formatterV2(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + formatterV2(-value);
        if (value < 100000) return formatterV1.format(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10d) + suffix;
    }

}
