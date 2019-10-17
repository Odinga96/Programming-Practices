package com.example.jpademo.topic.controller;

import com.example.jpademo.topic.service.TopicService;
import com.example.jpademo.topic.topics.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {


    @Autowired
    private TopicService topicService;



    @RequestMapping("/topics")
    public List<Topic> getAll(){ return topicService.getAllTopics(); }


    @RequestMapping("/topics/{id}")
    public Topic getTopic(@PathVariable String id){ return topicService.getTopic(id); }

    @RequestMapping(method = RequestMethod.POST ,value = "/topics")
    public void addTopic(@RequestBody  Topic topic){
        topicService.addTopic(topic);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public void deleteTopic(@PathVariable String id){ topicService.deleteTopic(id); }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
    public void updateTopic(@PathVariable String id,  @RequestBody Topic topic) { topicService.updateTopic(id, topic); }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}/{name}")
    public void updateName(@PathVariable String id, @PathVariable String name){
        topicService.updateTopicName(id,name);
    }
}
