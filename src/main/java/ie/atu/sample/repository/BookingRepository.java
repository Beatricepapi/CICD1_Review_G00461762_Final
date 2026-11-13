package ie.atu.sample.repository;

import ie.atu.sample.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {
}
