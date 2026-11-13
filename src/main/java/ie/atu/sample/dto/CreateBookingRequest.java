package ie.atu.sample.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateBookingRequest(

        @NotNull(message = "Room ID is required")
        Long roomId,

        @NotBlank(message = "Organiser name is required")
        @Size(min = 2, max = 60,
                message = "Organiser name must be between 2 and 60 characters")
        String organiserName,

        @NotNull(message = "Attendee count is required")
        @Min(value = 1, message = "Attendee count must be positive")
        Integer attendeeCount

) {
}
