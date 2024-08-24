package com.hotel.booking.system.kafka.consumer;

import java.util.List;

public interface KafkaConsumer<T> {

    void receive(List<T> messages, List<String> keys, List<Integer> partitions, List<String> offsets);
}
