package com.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor


public class BookingId {
        private int bookingid;
        private BookingModel bookingModel;

        public BookingId(){

        }
        @Override
                public String toString(){
            return "BookingId{" +
                    "bookingid=" + bookingid +
                    ", booking=" + bookingModel +
                    '}';
        }
    }

