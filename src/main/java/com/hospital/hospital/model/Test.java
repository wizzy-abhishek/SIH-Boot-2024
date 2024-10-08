package com.hospital.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
@Entity
public class Test {

    @Id
    @Column(length = 90)
    private String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "\nTest{" +
                "testName='" + testName + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(testName, test.testName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(testName);
    }
}

