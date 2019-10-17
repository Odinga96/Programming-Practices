package com.example.jpademo.topic.service;


import com.example.jpademo.topic.repository.TopicRepository;
import com.example.jpademo.topic.topics.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;


    private List<Topic> topics=new ArrayList<>( Arrays.asList(
            new Topic("php","PHP for web","learn php for web"),
            new Topic("java","Core java","learn java "),
            new Topic("spring","Spring boot framework","learn spring boot for web"),
            new Topic("python","Core python","learn python"),
            new Topic("C","C programming","We take you through C")
    ));


    public List<Topic> getAllTopics(){
        List<Topic> topicList=new ArrayList<>();

        topicRepository.findAll()
                .forEach(topicList::add);

        return topicList;
    }

    public Topic getTopic(String id) {
        return topics.stream().filter(t->t.getId().equalsIgnoreCase(id)).findFirst().get();
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
        topics.remove(topics.stream().filter(t->t.getId().equalsIgnoreCase(id)).findFirst().get());
    }

    public void updateTopic(String id, Topic topic) {
        Topic topic1=topics.stream().filter(t->t.getId().equalsIgnoreCase(id)).findFirst().get();
        int index= topics.indexOf(topic1);

        topics.set(index, topic);
    }

    public void updateTopicName(String id, String name) {
        topics.stream().filter(t->t.getId().equalsIgnoreCase(id)).findFirst().get().setName(name);
    }
}
