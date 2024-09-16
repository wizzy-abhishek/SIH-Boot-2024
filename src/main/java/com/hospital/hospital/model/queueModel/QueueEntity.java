package com.hospital.hospital.model.queueModel;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueEntity that = (QueueEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(patientNumber, that.patientNumber) && Objects.equals(dept, that.dept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientNumber, dept);
    }
}

