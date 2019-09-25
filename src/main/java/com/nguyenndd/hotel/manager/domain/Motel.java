package com.nguyenndd.hotel.manager.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Motel.
 */
@Entity
@Table(name = "motel")
public class Motel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "decription")
    private String decription;

    @Column(name = "electricity_price")
    private Double electricityPrice;

    @Column(name = "water_price")
    private Double waterPrice;

    @OneToMany(mappedBy = "motel")
    private Set<File> images = new HashSet<>();

    @OneToMany(mappedBy = "motel")
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "motel")
    private Set<Convenient> convenients = new HashSet<>();

    @OneToMany(mappedBy = "motel")
    private Set<Services> services = new HashSet<>();

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

    public Motel title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public Motel address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public Motel phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDecription() {
        return decription;
    }

    public Motel decription(String decription) {
        this.decription = decription;
        return this;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Double getElectricityPrice() {
        return electricityPrice;
    }

    public Motel electricityPrice(Double electricityPrice) {
        this.electricityPrice = electricityPrice;
        return this;
    }

    public void setElectricityPrice(Double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public Double getWaterPrice() {
        return waterPrice;
    }

    public Motel waterPrice(Double waterPrice) {
        this.waterPrice = waterPrice;
        return this;
    }

    public void setWaterPrice(Double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public Set<File> getImages() {
        return images;
    }

    public Motel images(Set<File> files) {
        this.images = files;
        return this;
    }

    public Motel addImages(File file) {
        this.images.add(file);
        file.setMotel(this);
        return this;
    }

    public Motel removeImages(File file) {
        this.images.remove(file);
        file.setMotel(null);
        return this;
    }

    public void setImages(Set<File> files) {
        this.images = files;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public Motel rooms(Set<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public Motel addRooms(Room room) {
        this.rooms.add(room);
        room.setMotel(this);
        return this;
    }

    public Motel removeRooms(Room room) {
        this.rooms.remove(room);
        room.setMotel(null);
        return this;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<Convenient> getConvenients() {
        return convenients;
    }

    public Motel convenients(Set<Convenient> convenients) {
        this.convenients = convenients;
        return this;
    }

    public Motel addConvenients(Convenient convenient) {
        this.convenients.add(convenient);
        convenient.setMotel(this);
        return this;
    }

    public Motel removeConvenients(Convenient convenient) {
        this.convenients.remove(convenient);
        convenient.setMotel(null);
        return this;
    }

    public void setConvenients(Set<Convenient> convenients) {
        this.convenients = convenients;
    }

    public Set<Services> getServices() {
        return services;
    }

    public Motel services(Set<Services> services) {
        this.services = services;
        return this;
    }

    public Motel addServices(Services services) {
        this.services.add(services);
        services.setMotel(this);
        return this;
    }

    public Motel removeServices(Services services) {
        this.services.remove(services);
        services.setMotel(null);
        return this;
    }

    public void setServices(Set<Services> services) {
        this.services = services;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Motel)) {
            return false;
        }
        return id != null && id.equals(((Motel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Motel{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", decription='" + getDecription() + "'" +
            ", electricityPrice=" + getElectricityPrice() +
            ", waterPrice=" + getWaterPrice() +
            "}";
    }
}
