package com.hotel.booking.system.hotel.service.adapters.out.persistence.adapter;

import com.hotel.booking.system.hotel.service.adapters.out.persistence.mapper.BookingPersistenceMapper;
import com.hotel.booking.system.hotel.service.adapters.out.persistence.repository.RoomBookingRepository;
import com.hotel.booking.system.hotel.service.domain.model.RoomBooking;
import com.hotel.booking.system.hotel.service.ports.out.BookingOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.hotel.booking.system.common.domain.utils.DateTimeUtils.addHourAndMinutesToYYYYmmDD;

@RequiredArgsConstructor
@Service
public class BookingPersistenceAdapter implements BookingOutPort {

    private final RoomBookingRepository roomBookingRepository;
    private final BookingPersistenceMapper bookingPersistenceMapper;

    @Override
    public RoomBooking insertRoomBooking(RoomBooking roomBooking) {
        var roomBookingEntity = roomBookingRepository
                .save(bookingPersistenceMapper.toRoomBookingEntity(roomBooking));
        return bookingPersistenceMapper.toRoomBooking(roomBookingEntity);
    }

    @Override
    public boolean checkIfRoomIsBooked(UUID roomId, LocalDateTime fromDate, LocalDateTime toDate) {
        var searchFromDate = addHourAndMinutesToYYYYmmDD(fromDate, 14, 0).toString();
        var searchToDate = addHourAndMinutesToYYYYmmDD(toDate, 10, 0).toString();
        return roomBookingRepository
                .findByRoomIdAndFromDateTimeToDateTime(roomId, searchFromDate, searchToDate).isPresent();
    }
}
