package com.nguyenndd.hotel.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.nguyenndd.hotel.manager.domain.enumeration.RoomStatus;

/**
 * A Room.
 */
@Entity
@Table(name = "room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoomStatus status;

    @Column(name = "acreage")
    private Double acreage;

    @Column(name = "decription")
    private String decription;

    @OneToMany(mappedBy = "room")
    private Set<File> images = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<Services> services = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<Convenient> convenients = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<Contract> contracts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "rooms", allowSetters = true)
    private Motel motel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Room title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public Room price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public Room status(RoomStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Double getAcreage() {
        return acreage;
    }

    public Room acreage(Double acreage) {
        this.acreage = acreage;
        return this;
    }

    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }

    public String getDecription() {
        return decription;
    }

    public Room decription(String decription) {
        this.decription = decription;
        return this;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Set<File> getImages() {
        return images;
    }

    public Room images(Set<File> files) {
        this.images = files;
        return this;
    }

    public Room addImages(File file) {
        this.images.add(file);
        file.setRoom(this);
        return this;
    }

    public Room removeImages(File file) {
        this.images.remove(file);
        file.setRoom(null);
        return this;
    }

    public void setImages(Set<File> files) {
        this.images = files;
    }

    public Set<Services> getServices() {
        return services;
    }

    public Room services(Set<Services> services) {
        this.services = services;
        return this;
    }

    public Room addServices(Services services) {
        this.services.add(services);
        services.setRoom(this);
        return this;
    }

    public Room removeServices(Services services) {
        this.services.remove(services);
        services.setRoom(null);
        return this;
    }

    public void setServices(Set<Services> services) {
        this.services = services;
    }

    public Set<Convenient> getConvenients() {
        return convenients;
    }

    public Room convenients(Set<Convenient> convenients) {
        this.convenients = convenients;
        return this;
    }

    public Room addConvenients(Convenient convenient) {
        this.convenients.add(convenient);
        convenient.setRoom(this);
        return this;
    }

    public Room removeConvenients(Convenient convenient) {
        this.convenients.remove(convenient);
        convenient.setRoom(null);
        return this;
    }

    public void setConvenients(Set<Convenient> convenients) {
        this.convenients = convenients;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public Room contracts(Set<Contract> contracts) {
        this.contracts = contracts;
        return this;
    }

    public Room addContracts(Contract contract) {
        this.contracts.add(contract);
        contract.setRoom(this);
        return this;
    }

    public Room removeContracts(Contract contract) {
        this.contracts.remove(contract);
        contract.setRoom(null);
        return this;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Motel getMotel() {
        return motel;
    }

    public Room motel(Motel motel) {
        this.motel = motel;
        return this;
    }

    public void setMotel(Motel motel) {
        this.motel = motel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        return id != null && id.equals(((Room) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Room{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", price=" + getPrice() +
            ", status='" + getStatus() + "'" +
            ", acreage=" + getAcreage() +
            ", decription='" + getDecription() + "'" +
            "}";
    }
}
