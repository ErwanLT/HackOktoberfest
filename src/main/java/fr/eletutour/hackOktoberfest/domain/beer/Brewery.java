package fr.eletutour.hackOktoberfest.domain.beer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Brewery {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToOne
    @JoinColumn(name = "adress_id")
    @NotNull
    private Address address;

    public Brewery() {
    }

    public Brewery(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Brewery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress=" + address +
                '}';
    }
}
