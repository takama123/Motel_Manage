package com.nguyenndd.hotel.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A BillDetails.
 */
@Entity
@Table(name = "bill_details")
public class BillDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "old_kwh")
    private Double oldKwh;

    @Column(name = "old_water")
    private Double oldWater;

    @Column(name = "new_kwh")
    private Double newKwh;

    @Column(name = "new_water")
    private Double newWater;

    @Column(name = "room_price")
    private Double roomPrice;

    @Column(name = "electricity_price")
    private Double electricityPrice;

    @Column(name = "water_price")
    private Double waterPrice;

    @OneToMany(mappedBy = "billDetails")
    private Set<ExtraPaymentData> extraPaymentDatas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "paymentDatas", allowSetters = true)
    private Contract contract;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public BillDetails startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BillDetails endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getOldKwh() {
        return oldKwh;
    }

    public BillDetails oldKwh(Double oldKwh) {
        this.oldKwh = oldKwh;
        return this;
    }

    public void setOldKwh(Double oldKwh) {
        this.oldKwh = oldKwh;
    }

    public Double getOldWater() {
        return oldWater;
    }

    public BillDetails oldWater(Double oldWater) {
        this.oldWater = oldWater;
        return this;
    }

    public void setOldWater(Double oldWater) {
        this.oldWater = oldWater;
    }

    public Double getNewKwh() {
        return newKwh;
    }

    public BillDetails newKwh(Double newKwh) {
        this.newKwh = newKwh;
        return this;
    }

    public void setNewKwh(Double newKwh) {
        this.newKwh = newKwh;
    }

    public Double getNewWater() {
        return newWater;
    }

    public BillDetails newWater(Double newWater) {
        this.newWater = newWater;
        return this;
    }

    public void setNewWater(Double newWater) {
        this.newWater = newWater;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public BillDetails roomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
        return this;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Double getElectricityPrice() {
        return electricityPrice;
    }

    public BillDetails electricityPrice(Double electricityPrice) {
        this.electricityPrice = electricityPrice;
        return this;
    }

    public void setElectricityPrice(Double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public Double getWaterPrice() {
        return waterPrice;
    }

    public BillDetails waterPrice(Double waterPrice) {
        this.waterPrice = waterPrice;
        return this;
    }

    public void setWaterPrice(Double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public Set<ExtraPaymentData> getExtraPaymentDatas() {
        return extraPaymentDatas;
    }

    public BillDetails extraPaymentDatas(Set<ExtraPaymentData> extraPaymentData) {
        this.extraPaymentDatas = extraPaymentData;
        return this;
    }

    public BillDetails addExtraPaymentDatas(ExtraPaymentData extraPaymentData) {
        this.extraPaymentDatas.add(extraPaymentData);
        extraPaymentData.setBillDetails(this);
        return this;
    }

    public BillDetails removeExtraPaymentDatas(ExtraPaymentData extraPaymentData) {
        this.extraPaymentDatas.remove(extraPaymentData);
        extraPaymentData.setBillDetails(null);
        return this;
    }

    public void setExtraPaymentDatas(Set<ExtraPaymentData> extraPaymentData) {
        this.extraPaymentDatas = extraPaymentData;
    }

    public Contract getContract() {
        return contract;
    }

    public BillDetails contract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillDetails)) {
            return false;
        }
        return id != null && id.equals(((BillDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillDetails{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", oldKwh=" + getOldKwh() +
            ", oldWater=" + getOldWater() +
            ", newKwh=" + getNewKwh() +
            ", newWater=" + getNewWater() +
            ", roomPrice=" + getRoomPrice() +
            ", electricityPrice=" + getElectricityPrice() +
            ", waterPrice=" + getWaterPrice() +
            "}";
    }
}
