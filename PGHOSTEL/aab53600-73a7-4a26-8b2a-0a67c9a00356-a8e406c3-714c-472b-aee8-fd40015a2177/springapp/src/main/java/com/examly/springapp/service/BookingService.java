package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Booking;

public interface BookingService {

    Booking createBooking(Booking booking);

    List<Booking> getAllBookings();

    Booking getBookingById(int bookingId);

    Booking updateBooking(int bookingId, Booking booking);

    void deleteBooking(int bookingId);
}
