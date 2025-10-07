package com.s21.devops.sample.reportservice.Statistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s21.devops.sample.reportservice.Communication.BookingStatisticsMessage;
import com.s21.devops.sample.reportservice.Service.BookingStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class QueueConsumer {
    @Autowired
    private BookingStatsService bookingStatsService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final Counter rabbitoutCounter;

    public QueueConsumer(MeterRegistry registry) {
        this.rabbitoutCounter = registry.counter("rabbit_out_total");
    }

    public void receiveMessage(String message) throws JsonProcessingException {
        rabbitoutCounter.increment(); 
        System.out.println("Received !!! (String) " + message);
        processMessage(message);
    }

    public void receiveMessage(byte[] message) throws JsonProcessingException {
        String strMessage = new String(message);
        rabbitoutCounter.increment(); 
        System.out.println("Received !!! (No String) " + strMessage);
        processMessage(strMessage);
    }

    private void processMessage(String message) throws JsonProcessingException {
        BookingStatisticsMessage bsm = objectMapper.readValue(message, BookingStatisticsMessage.class);
        bookingStatsService.postBookingStatsMessage(bsm);
    }
}