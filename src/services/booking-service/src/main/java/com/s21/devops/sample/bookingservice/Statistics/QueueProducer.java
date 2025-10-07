package com.s21.devops.sample.bookingservice.Statistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s21.devops.sample.bookingservice.Communication.BookingStatisticsMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class QueueProducer {
    @Value("${fanout.exchange}")
    private String fanoutExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final Counter rabbitinCounter;

    public QueueProducer(MeterRegistry registry) {
        this.rabbitinCounter = registry.counter("rabbit_in_total");
    }

    public void putStatistics(BookingStatisticsMessage bookingStatisticsMessage) throws JsonProcessingException {
        rabbitinCounter.increment(); 
        System.out.println("Sending message...");
        rabbitTemplate.setExchange(fanoutExchange);
        rabbitTemplate.convertAndSend(objectMapper.writeValueAsString(bookingStatisticsMessage));
        System.out.println("Message was sent successfully!");
    }
}
