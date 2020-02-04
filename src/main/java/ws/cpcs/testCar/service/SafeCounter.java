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
        while(true) {
            int existingValue = getValue();
            if (existingValue == 100 || existingValue == 0) {
                return false;
            }
            int newValue = existingValue + 1;
            if(APP_COUNTER.compareAndSet(existingValue, newValue)) {
                System.out.println("NEW VALUE INCREASED: " + thread + " - " + newValue);
                return newValue == 100;
            }
        }
    }

    Boolean decrement(String thread) {
        while(true) {
            int existingValue = getValue();
            if (existingValue == 100 || existingValue == 0) {
                return false;
            }
            int newValue = existingValue - 1;
            if(APP_COUNTER.compareAndSet(existingValue, newValue)) {
                System.out.println("NEW VALUE DECREASED: " + thread + " - " + newValue);
                return newValue == 0;
            }
        }
    }
}