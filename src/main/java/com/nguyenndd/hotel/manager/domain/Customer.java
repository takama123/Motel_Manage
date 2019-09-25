package com.nguyenndd.hotel.manager.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.nguyenndd.hotel.manager.domain.enumeration.Gender;

import com.nguyenndd.hotel.manager.domain.enumeration.Nation;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "extra_name")
    private String extraName;

    @Column(name = "origin_address")
    private String originAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "nation")
    private Nation nation;

    @Column(name = "religion")
    private String religion;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "indentity")
    private String indentity;

    @Column(name = "regularly_adress")
    private String regularlyAdress;

    @Column(name = "address")
    private String address;

    @Column(name = "academic_level")
    private String academicLevel;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "foreign_level")
    private String foreignLevel;

    @Column(name = "job")
    private String job;

    @Column(name = "email")
    private String email;

    @Column(name = "passsword")
    private String passsword;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "customer")
    private Set<Biography> biographies = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Contract> contracts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Customer gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public Customer birthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getExtraName() {
        return extraName;
    }

    public Customer extraName(String extraName) {
        this.extraName = extraName;
        return this;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public Customer originAddress(String originAddress) {
        this.originAddress = originAddress;
        return this;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public Nation getNation() {
        return nation;
    }

    public Customer nation(Nation nation) {
        this.nation = nation;
        return this;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public String getReligion() {
        return religion;
    }

    public Customer religion(String religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNationality() {
        return nationality;
    }

    public Customer nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIndentity() {
        return indentity;
    }

    public Customer indentity(String indentity) {
        this.indentity = indentity;
        return this;
    }

    public void setIndentity(String indentity) {
        this.indentity = indentity;
    }

    public String getRegularlyAdress() {
        return regularlyAdress;
    }

    public Customer regularlyAdress(String regularlyAdress) {
        this.regularlyAdress = regularlyAdress;
        return this;
    }

    public void setRegularlyAdress(String regularlyAdress) {
        this.regularlyAdress = regularlyAdress;
    }

    public String getAddress() {
        return address;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public Customer academicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
        return this;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getQualification() {
        return qualification;
    }

    public Customer qualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getForeignLevel() {
        return foreignLevel;
    }

    public Customer foreignLevel(String foreignLevel) {
        this.foreignLevel = foreignLevel;
        return this;
    }

    public void setForeignLevel(String foreignLevel) {
        this.foreignLevel = foreignLevel;
    }

    public String getJob() {
        return job;
    }

    public Customer job(String job) {
        this.job = job;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasssword() {
        return passsword;
    }

    public Customer passsword(String passsword) {
        this.passsword = passsword;
        return this;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public String getPhone() {
        return phone;
    }

    public Customer phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isStatus() {
        return status;
    }

    public Customer status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Biography> getBiographies() {
        return biographies;
    }

    public Customer biographies(Set<Biography> biographies) {
        this.biographies = biographies;
        return this;
    }

    public Customer addBiographies(Biography biography) {
        this.biographies.add(biography);
        biography.setCustomer(this);
        return this;
    }

    public Customer removeBiographies(Biography biography) {
        this.biographies.remove(biography);
        biography.setCustomer(null);
        return this;
    }

    public void setBiographies(Set<Biography> biographies) {
        this.biographies = biographies;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public Customer contracts(Set<Contract> contracts) {
        this.contracts = contracts;
        return this;
    }

    public Customer addContracts(Contract contract) {
        this.contracts.add(contract);
        contract.setCustomer(this);
        return this;
    }

    public Customer removeContracts(Contract contract) {
        this.contracts.remove(contract);
        contract.setCustomer(null);
        return this;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", gender='" + getGender() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", extraName='" + getExtraName() + "'" +
            ", originAddress='" + getOriginAddress() + "'" +
            ", nation='" + getNation() + "'" +
            ", religion='" + getReligion() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", indentity='" + getIndentity() + "'" +
            ", regularlyAdress='" + getRegularlyAdress() + "'" +
            ", address='" + getAddress() + "'" +
            ", academicLevel='" + getAcademicLevel() + "'" +
            ", qualification='" + getQualification() + "'" +
            ", foreignLevel='" + getForeignLevel() + "'" +
            ", job='" + getJob() + "'" +
            ", email='" + getEmail() + "'" +
            ", passsword='" + getPasssword() + "'" +
            ", phone='" + getPhone() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
