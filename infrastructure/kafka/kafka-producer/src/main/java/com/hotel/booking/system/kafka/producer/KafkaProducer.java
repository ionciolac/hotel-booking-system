package com.hotel.booking.system.kafka.producer;

public interface KafkaProducer<K, V> {

    void send(String topicName, K key, V message);
}
