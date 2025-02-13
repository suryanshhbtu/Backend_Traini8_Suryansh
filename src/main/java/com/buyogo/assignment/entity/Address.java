package com.buyogo.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Embeddable
@Valid
public class Address {

    @NotBlank(message = "Detailed Address is required")
    @Column(name = "detailed_address")
    private String detailedAddress;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Pincode is required")
    private String pincode;

    public Address() {
    }

    public Address(String detailedAddress, String city, String state, String pincode) {
        this.detailedAddress = detailedAddress;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public @NotBlank(message = "Detailed Address is required") String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(@NotBlank(message = "Detailed Address is required") String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public @NotBlank(message = "City is required") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "City is required") String city) {
        this.city = city;
    }

    public @NotBlank(message = "Pincode is required") String getPincode() {
        return pincode;
    }

    public void setPincode(@NotBlank(message = "Pincode is required") String pincode) {
        this.pincode = pincode;
    }

    public @NotBlank(message = "State is required") String getState() {
        return state;
    }

    public void setState(@NotBlank(message = "State is required") String state) {
        this.state = state;
    }
}