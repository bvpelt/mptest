package com.bsoft.mptest.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "adres", schema = "public", catalog = "adres")
public class AdresDAO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "housenumber")
    private String housenumber;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "city")
    private String city;
    @Column(name = "hash")
    private Integer hash;

    public AdresDAO(String street, String housenumber, String zipcode, String city) {
        this.street = street;
        this.housenumber = housenumber;
        this.zipcode = zipcode;
        this.city = city;
        this.genHash();
    }

    public void genHash() {
        this.hash = hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AdresDAO adresDAO = (AdresDAO) o;
        return Objects.equals(street, adresDAO.street) && Objects.equals(housenumber, adresDAO.housenumber) && Objects.equals(zipcode, adresDAO.zipcode) && Objects.equals(city, adresDAO.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, housenumber, zipcode, city);
    }

    @Override
    public String toString() {
        return "AdresDAO{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", housenumber='" + housenumber + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", hash=" + hash +
                '}';
    }
}
