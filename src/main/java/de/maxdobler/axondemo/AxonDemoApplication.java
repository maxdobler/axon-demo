package de.maxdobler.axondemo;

import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AxonDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(AxonDemoApplication.class, args);
    }


    @Bean
    EventStore eventStore() {

        return EmbeddedEventStore.builder().storageEngine(new InMemoryEventStorageEngine()).build();
    }
}
