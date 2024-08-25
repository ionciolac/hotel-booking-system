package com.hotel.booking.system.bookingservice.adapters.in.rest.adapter;

import com.hotel.booking.system.bookingservice.adapters.in.rest.data.req.CreateBookingRequest;
import com.hotel.booking.system.bookingservice.adapters.in.rest.data.req.UpdateBookingRequest;
import com.hotel.booking.system.bookingservice.adapters.in.rest.data.res.BookingResponse;
import com.hotel.booking.system.bookingservice.adapters.in.rest.mapper.BookingRestMapper;
import com.hotel.booking.system.bookingservice.ports.in.rest.BookingInPort;
import com.hotel.booking.system.common.rest.data.res.DeletedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.BOOKING;
import static com.hotel.booking.system.common.domain.utils.AppCommonMessages.CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/booking")
@RestController
public class BookingRestAdapter {

    private final BookingInPort bookingInPort;
    private final BookingRestMapper bookingRestMapper;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody CreateBookingRequest req) {
        var booking = bookingInPort.createBooking(bookingRestMapper.toBooking(req));
        return ResponseEntity.status(CREATED).body(bookingRestMapper.toBookingResponse(booking));
    }

    @PatchMapping
    public ResponseEntity<BookingResponse> updateBooking(@RequestBody UpdateBookingRequest req) {
        var booking = bookingInPort.updateBooking(bookingRestMapper.toBooking(req));
        return ResponseEntity.status(OK).body(bookingRestMapper.toBookingResponse(booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedResponse> deleteBooking(@PathVariable("id") UUID id) {
        bookingInPort.deleteBooking(id);
        var msg = format(CONTROLLER_DELETE_MESSAGE_RESPONSE_MESSAGE, BOOKING, id);
        return ResponseEntity.status(OK).body(new DeletedResponse(TRUE, msg));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable("id") UUID id) {
        var booking = bookingInPort.getBooking(id);
        return ResponseEntity.status(OK).body(bookingRestMapper.toBookingResponse(booking));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<BookingResponse> payBooking(@PathVariable("id") UUID id) {
        var booking = bookingInPort.payBooking(id);
        return ResponseEntity.status(OK).body(bookingRestMapper.toBookingResponse(booking));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable("id") UUID id) {
        var booking = bookingInPort.cancelBooking(id);
        return ResponseEntity.status(OK).body(bookingRestMapper.toBookingResponse(booking));
    }
}
