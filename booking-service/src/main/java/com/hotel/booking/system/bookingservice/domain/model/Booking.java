package com.hotel.booking.system.bookingservice.domain.model;

import com.hotel.booking.system.common.common.BookingStatus;
import com.hotel.booking.system.common.domain.exception.BadRequestException;
import com.hotel.booking.system.common.domain.model.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.hotel.booking.system.common.common.BookingStatus.*;
import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.*;
import static java.lang.String.format;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseId {

    private UUID userId;
    private UUID hotelId;
    private UUID roomId;
    private UUID roomBookingId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private BookingStatus status;

    public void validateIfRoomIsBookedThenThrowException(boolean isRoomBookedInHotelService, Booking booking) {
        var roomID = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        if (isRoomBookedInHotelService) {
            throw new BadRequestException(format(SERVICE_ROOM_ALREADY_IS_BOOKED_FROM_TO_DATE_MESSAGE, roomID, fromDate, toDate));
        }
    }

    public void validateIfRoomIsBookedThenThenStatusToROOM_RESERVED(boolean isRoomBookedInHotelService, Booking booking) {
        if (isRoomBookedInHotelService) {
            booking.setStatus(ROOM_IS_ALREADY_BOOKED);
        }
    }

    public void validateIfRoomIsNotInRESERVEDStatus(Booking booking) {
        if (!RESERVED.equals(booking.getStatus())) {
            var msg = format(SERVICE_BOOKING_MUST_BE_IN_RESERVED_STATUS_MESSAGE, booking.getId(), RESERVED);
            throw new BadRequestException(msg);
        }
    }

    public void validateIfCanCancelBooking(Booking booking) {
        if (!BOOKED.equals(booking.getStatus()))
            throw new BadRequestException(format(SERVICE_BOOKING_CANNOT_BE_CANCELED_WRONG_STATUS_MESSAGE, BOOKED));
        if (ChronoUnit.HOURS.between(LocalDateTime.now(), booking.getFromDate()) <= 24)
            throw new BadRequestException(SERVICE_BOOKING_CANNOT_BE_CANCELED_LESS_24H_BEFORE_CHECKIN_MESSAGE);
    }
}
