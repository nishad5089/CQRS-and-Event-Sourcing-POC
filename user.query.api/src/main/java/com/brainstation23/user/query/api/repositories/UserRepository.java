package com.brainstation23.user.query.api.repositories;

import java.util.*;
import com.brainstation23.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public interface UserRepository extends MongoRepository<User, String> {

  @Query(
      "{'$or' : [{'firstname': {$regex : ?0, $options: '1'}}, {'lastname': {$regex : ?0, $options: '1'}}, {'emailAddress': {$regex : ?0, $options: '1'}}, {'account.username': {$regex : ?0, $options: '1'}}]}")
  List<User> findByFilterRegex(String filter);
}
