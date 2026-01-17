package com.infoera.sportshub_inventory.utils;

import com.infoera.sportshub_inventory.enums.EntityType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeGenerator {

    private static final Map<EntityType, AtomicInteger> counters =
            new ConcurrentHashMap<>();

    public static String generateCode(EntityType type) {
        AtomicInteger counter =
                counters.computeIfAbsent(type, t -> new AtomicInteger(0));

        int next = counter.incrementAndGet();

        return String.format("%04d", next);
    }
}


//   CodeGenerator.generateCode(EntityType.CUSTOMER);
