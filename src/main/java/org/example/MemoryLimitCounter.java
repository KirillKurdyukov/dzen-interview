package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class MemoryLimitCounter {

    private final AtomicInteger atomicInteger;

    public MemoryLimitCounter(
            int memory
    ) {
        atomicInteger = new AtomicInteger(memory);
    }

    public boolean reserve(int memory) {
        while (true) {
            int curMemory = atomicInteger.get();

            if (curMemory > memory) {
                if (atomicInteger.compareAndSet(curMemory, curMemory - memory)) {
                    return true;
                }
            } else {
                return false;
            }
        }
    }
}
