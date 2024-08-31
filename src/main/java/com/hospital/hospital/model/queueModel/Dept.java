package com.hospital.hospital.model.queueModel;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@Entity
public class Dept {

    @Id
    @Column(name = "name" , length = 35)
    private String name;


    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QueueEntity> queues;

    public Dept() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QueueEntity> getQueues() {
        return queues;
    }

    public void setQueues(List<QueueEntity> queues) {
        this.queues = queues;
    }
}

