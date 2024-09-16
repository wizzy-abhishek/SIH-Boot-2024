package com.hospital.hospital.model.queueModel;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return Objects.equals(name, dept.name) && Objects.equals(queues, dept.queues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, queues);
    }
}

