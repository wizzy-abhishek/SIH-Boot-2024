package com.hospital.hospital.model.queueModel;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("prototype")
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer patientNumber;

    @ManyToOne
    @JoinColumn(name = "dept_name", referencedColumnName = "name")
    private Dept dept;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(Integer patientNumber) {
        this.patientNumber = patientNumber;
    }

    public Dept getDepartment() {
        return dept;
    }

    public void setDepartment(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "QueueEntity{" +
                "id=" + id +
                ", patientNumber=" + patientNumber +
                ", department=" + (dept != null ? dept.getName() : "null") +
                '}';
    }
}

