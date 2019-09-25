package com.nguyenndd.hotel.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ExtraPaymentData.
 */
@Entity
@Table(name = "extra_payment_data")
public class ExtraPaymentData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "decription")
    private String decription;

    @ManyToOne
    @JsonIgnoreProperties(value = "extraPaymentDatas", allowSetters = true)
    private BillDetails billDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public ExtraPaymentData cost(Double cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDecription() {
        return decription;
    }

    public ExtraPaymentData decription(String decription) {
        this.decription = decription;
        return this;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public BillDetails getBillDetails() {
        return billDetails;
    }

    public ExtraPaymentData billDetails(BillDetails billDetails) {
        this.billDetails = billDetails;
        return this;
    }

    public void setBillDetails(BillDetails billDetails) {
        this.billDetails = billDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtraPaymentData)) {
            return false;
        }
        return id != null && id.equals(((ExtraPaymentData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExtraPaymentData{" +
            "id=" + getId() +
            ", cost=" + getCost() +
            ", decription='" + getDecription() + "'" +
            "}";
    }
}
