package com.brainstation23.user.core.configuration;

import com.mongodb.client.MongoClient;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Configuration
public class AxonConfig {

  @Bean
  public TokenStore tokenStore(com.mongodb.client.MongoClient client, Serializer serializer) {

    return MongoTokenStore.builder()
        .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
        .serializer(serializer)
        .build();
  }

  @Bean
  public EventStorageEngine storageEngine(MongoClient client) {

    return MongoEventStorageEngine.builder()
        .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
        .build();
  }

  @Bean
  public EmbeddedEventStore eventStore(
      EventStorageEngine storageEngine, AxonConfiguration configuration) {

    return EmbeddedEventStore.builder()
        .storageEngine(storageEngine)
        .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
        .build();
  }
}
