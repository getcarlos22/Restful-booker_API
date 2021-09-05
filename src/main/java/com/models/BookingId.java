package com.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor


public class BookingId {
        private int bookingid;
        private BookingModel bookingModel;

        @Override
                public String toString(){
            return "BookingId{" +
                    "bookingid=" + bookingid +
                    ", booking=" + bookingModel +
                    '}';
        }
    }

