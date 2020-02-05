package ws.cpcs.testCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ws.cpcs.testCar.domain.Trans;
import ws.cpcs.testCar.repository.TransactionsRepo;

import java.util.Random;

@Service
public class AsyncService {

    private TransactionsRepo repo;
    private SafeCounter counter;

    @Value("${app.counter.min}")
    private Integer minCounter;

    @Value("${app.counter.max}")
    private Integer maxCounter;

    @Autowired
    public AsyncService(TransactionsRepo repo, SafeCounter counter) {
        this.repo = repo;
        this.counter = counter;
    }

    private static final String TYPE_CONSUMER = "CONSUMER";
    private static final String TYPE_PRODUCER = "PRODUCER";

    @Async("taskExecutor")
    public void startProducer() {
        process(TYPE_PRODUCER);
    }

    @Async("taskExecutor")
    public void startConsumer() {
        process(TYPE_CONSUMER);
    }

    @Async("taskExecutor")
    public void reset(Integer val) {
        counter.setValue(val);
    }

    private void process(String type) {
        String threadName = String.format("%s %d", type, new Random().nextInt(1000));
        System.out.println(threadName + " started ... ");
        while (counter.getValue() > minCounter && counter.getValue() < maxCounter) {
            try {
                Thread.sleep(100);
                if (type.equals(TYPE_CONSUMER)) {
                    if (counter.decrement(threadName)) {
                        writeTimestamp(threadName);
                        return;
                    }
                } else {
                    if (counter.increment(threadName)) {
                        writeTimestamp(threadName);
                        return;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    public void writeTimestamp(String name) {
        Trans tr = new Trans(name);
        repo.save(tr);
        System.out.println(String.format("DONE: %s %s", tr.getThreadName(), tr.getCreatedAt()));
    }
}
