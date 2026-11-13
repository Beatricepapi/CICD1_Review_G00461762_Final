package ie.atu.sample.dto;

public record BookingResponse(
        Long id,
        String organiserName,
        Integer attendeeCount,
        Long roomId,
        String status
) {
}
