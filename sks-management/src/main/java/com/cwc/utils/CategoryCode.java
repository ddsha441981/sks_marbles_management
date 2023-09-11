package com.cwc.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class CategoryCode {
	
//	private static final String CATEGORY_PREFIX = "CAT";
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static String generateCategoryCode(String categoryName) {
        int codeNumber = counter.getAndIncrement();
        return categoryName.substring(0,4) + String.format("%04d", codeNumber);
    }

}
