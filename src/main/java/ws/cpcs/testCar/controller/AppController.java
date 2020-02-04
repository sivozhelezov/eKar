package ws.cpcs.testCar.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ws.cpcs.testCar.service.AppService;

@Api(tags = "Test")
@RestController
public class AppController {

    private final AppService srv;

    @Autowired
    public AppController(AppService srv) {
        this.srv = srv;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public void send(@RequestParam("producers") Integer producers,
                     @RequestParam("customers") Integer customers) {
        srv.changeCounter(producers, customers);
    }

    @PostMapping("/set")
    @ResponseStatus(HttpStatus.OK)
    public void send(@RequestParam("counterVal") Integer counterVal) {
        srv.resetCounter(counterVal);
    }
}
