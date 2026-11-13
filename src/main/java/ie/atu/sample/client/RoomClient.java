package ie.atu.sample.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "room-service", url = "http://localhost:8082")
public interface RoomClient {

    @GetMapping("/api/rooms/{id}")
    RoomResponse getRoomById(@PathVariable("id") Long id);
}
