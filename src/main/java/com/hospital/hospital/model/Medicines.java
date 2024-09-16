package com.hospital.hospital.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Scope("prototype")
@Entity
public class Medicines {

    @Id
    @Column(length = 60)
    private String meds_name;

    private int quantity;

    public String getMeds_name() {
        return meds_name;
    }

    public void setMeds_name(String meds_name) {
        this.meds_name = meds_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\nMedicines{" +
                "meds_name='" + meds_name + '\'' +
                "\nquantity=" + quantity +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicines medicines = (Medicines) o;
        return quantity == medicines.quantity && Objects.equals(meds_name, medicines.meds_name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meds_name, quantity);
    }
}


