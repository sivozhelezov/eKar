package ws.cpcs.testCar.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SafeCounter {

    private final AtomicInteger APP_COUNTER = new AtomicInteger(50);

    int getValue() {
        return APP_COUNTER.get();
    }

    void setValue(Integer val) {
        APP_COUNTER.set(val);
    }

    Boolean increment(String thread) {
        int existingValue = getValue();
        if (existingValue == 100 || existingValue == 0) {
            return false;
        }
        int newValue = APP_COUNTER.incrementAndGet();
        if (newValue != existingValue) {
            System.out.println("NEW VALUE INCREASED: " + thread + " - " + newValue);
        }
        return newValue == 100;
    }

    Boolean decrement(String thread) {
        int existingValue = getValue();
        if (existingValue == 100 || existingValue == 0) {
            return false;
        }
        int newValue = APP_COUNTER.decrementAndGet();
        if (newValue != existingValue) {
            System.out.println("NEW VALUE DECREASED: " + thread + " - " + newValue);
        }
        return newValue == 0;
    }
}