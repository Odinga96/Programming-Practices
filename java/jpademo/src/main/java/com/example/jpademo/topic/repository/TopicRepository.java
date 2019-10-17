package com.example.jpademo.topic.repository;

import com.example.jpademo.topic.topics.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic,String> {
}
