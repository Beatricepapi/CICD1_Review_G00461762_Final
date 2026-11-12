package ie.atu.sample.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}