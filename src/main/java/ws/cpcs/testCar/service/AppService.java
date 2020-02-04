package ws.cpcs.testCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.stream.IntStream;

@Service
public class AppService {

    private AsyncService async;

    @Value("${app.counter.min}")
    private Integer minCounter;

    @Value("${app.counter.max}")
    private Integer maxCounter;

    @Autowired
    public AppService(AsyncService async) {
        this.async = async;
    }

    public void changeCounter(Integer producers, Integer customers) {

        IntStream.range(0,producers).forEach(i -> async.startProducer());
        IntStream.range(0,customers).forEach(i -> async.startCustomer());
    }

    public void resetCounter(Integer val) {
        async.reset(val);
    }
}
