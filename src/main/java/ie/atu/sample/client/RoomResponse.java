package ie.atu.sample.client;

public record RoomResponse(
        Long id,
        String name,
        Integer capacity
) {
}
