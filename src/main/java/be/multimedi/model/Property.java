package be.multimedi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Property")
@Getter
@Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private double price;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Enumerated(EnumType.STRING)
    private EPC epc;

    private int interiorArea;
    private int plotArea;
    private int nrBedrooms;
    private int nrBathrooms;

    public Property() {}

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", epc=" + epc +
                ", interiorArea=" + interiorArea +
                ", plotArea=" + plotArea +
                ", nrBedrooms=" + nrBedrooms +
                ", nrBathrooms=" + nrBathrooms +
                '}';
    }
}

