package com.hotel.booking.system.booking.service.domain.model;

import com.hotel.booking.system.common.common.enums.BookingStatus;
import com.hotel.booking.system.common.common.exception.BadRequestException;
import com.hotel.booking.system.common.domain.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static com.hotel.booking.system.common.common.enums.BookingStatus.*;
import static com.hotel.booking.system.common.common.utils.AppCommonMessages.*;
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

    public void checkIfRoomIsBookedThenThrowException(boolean isRoomBooked, Booking booking) {
        var roomID = booking.getRoomId();
        var fromDate = booking.getFromDate();
        var toDate = booking.getToDate();
        if (isRoomBooked) {
            var msg = format(SERVICE_ROOM_ALREADY_IS_BOOKED_FROM_TO_DATE_MESSAGE, roomID, fromDate, toDate);
            throw new BadRequestException(msg);
        }
    }

    public void checkIfRoomIsBookedThenSetStatusToROOM_RESERVED(boolean isRoomBooked, Booking booking) {
        if (isRoomBooked)
            booking.setStatus(ROOM_IS_ALREADY_BOOKED);
    }

    public void checkIfRoomIsNotInRESERVEDStatus(Booking booking) {
        if (!RESERVED.equals(booking.getStatus())) {
            var msg = format(SERVICE_BOOKING_MUST_BE_IN_RESERVED_STATUS_MESSAGE, booking.getId(), RESERVED);
            throw new BadRequestException(msg);
        }
    }

    public void checkIfCanCancelBooking(Booking booking) {
        if (!BOOKED.equals(booking.getStatus()))
            throw new BadRequestException(format(SERVICE_BOOKING_CANNOT_BE_CANCELED_WRONG_STATUS_MESSAGE, BOOKED));
        if (ChronoUnit.HOURS.between(LocalDateTime.now(), booking.getFromDate()) <= 24)
            throw new BadRequestException(SERVICE_BOOKING_CANNOT_BE_CANCELED_LESS_24H_BEFORE_CHECKIN_MESSAGE);
    }

    public void checkIfAtLeastOneDateWasChanged(Booking originalObject, Booking newObject) {
        var fromDate = originalObject.getFromDate().toLocalDate();
        var toDate = originalObject.getToDate().toLocalDate();
        var newFromDate = newObject.getFromDate().toLocalDate();
        var newToDate = newObject.getToDate().toLocalDate();
        if (fromDate.equals(newFromDate) && toDate.equals(newToDate))
            throw new BadRequestException(SERVICE_VALIDATE_IF_DATES_WAS_CHANGED_ON_UPDATE_MESSAGE);
    }
}
