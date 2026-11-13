package ie.atu.sample.service;

import ie.atu.sample.client.RoomClient;
import ie.atu.sample.client.RoomResponse;
import ie.atu.sample.dto.BookingResponse;
import ie.atu.sample.dto.CreateBookingRequest;
import ie.atu.sample.model.Booking;
import ie.atu.sample.repository.BookingRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomClient roomClient;

    public BookingService(BookingRepository bookingRepository,
                          RoomClient roomClient) {
        this.bookingRepository = bookingRepository;
        this.roomClient = roomClient;
    }

    public BookingResponse createBooking(CreateBookingRequest request) {

        RoomResponse room;

        try {
            room = roomClient.getRoomById(request.roomId());
        } catch (feign.FeignException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Room not found");
        }

        if (room == null || room.capacity() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid room");
        }

        if (request.attendeeCount() > room.capacity()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Attendee count exceeds room capacity");
        }

        Booking booking = new Booking();
        booking.setOrganiserName(request.organiserName());
        booking.setAttendeeCount(request.attendeeCount());
        booking.setRoomId(request.roomId());
        booking.setStatus("ACTIVE");

        Booking saved = bookingRepository.save(booking);

        return toResponse(saved);
    }

    public BookingResponse getBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Booking not found with ID: " + id));

        return toResponse(booking);
    }

    private BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getOrganiserName(),
                booking.getAttendeeCount(),
                booking.getRoomId(),
                booking.getStatus()
        );
    }
}
