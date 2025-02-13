package com.buyogo.assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;
/**
 * Entity with jakarta Validations
 * */
@Entity
@Table(name="training_center_courses")
@Valid
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="training_center_id")
    private int id;
    
    @NotBlank(message = "CenterName is required")
    @Size(max = 40, message = "CenterName must be less than 40 characters")
    @Column(name = "center_name")
    private String centerName;
    
    @NotBlank(message = "CenterCode is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "CenterCode must be exactly 12 alphanumeric characters")
    @Column(name = "center_code")
    private String centerCode;

//    Embeddable class object
    @Embedded
    private Address address;

    @Column(name = "student_cap")
    private int studentCap;

    @Column(name = "course")
    private List<String> coursesOffered;

    @Column(name = "created_on")
    private Long createdOn;
    
    @Email(message = "ContactEmail should be a valid email address")
    @Column(name = "contact_email")
    private String contactEmail;
    
    @NotNull(message = "ContactPhone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "ContactPhone must be a valid 10-digit phone number")
    @Column(name = "contact_phone")
    private String contactPhone;

    public TrainingCenter() {
    }

    public TrainingCenter(int id, String centerName, String centerCode, Address address,
                          int studentCap, List<String> coursesOffered, Long createdOn,
                          String contactEmail, String contactPhone) {
        this.id = id;
        this.centerName = centerName;
        this.centerCode = centerCode;
        this.address = address;
        this.studentCap = studentCap;
        this.coursesOffered = coursesOffered;
        this.createdOn = createdOn;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "CenterName is required") @Size(max = 40, message = "CenterName must be less than 40 characters") String getCenterName() {
        return centerName;
    }

    public void setCenterName(@NotBlank(message = "CenterName is required") @Size(max = 40, message = "CenterName must be less than 40 characters") String centerName) {
        this.centerName = centerName;
    }

    public @NotBlank(message = "CenterCode is required") @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "CenterCode must be exactly 12 alphanumeric characters") String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(@NotBlank(message = "CenterCode is required") @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "CenterCode must be exactly 12 alphanumeric characters") String centerCode) {
        this.centerCode = centerCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getStudentCap() {
        return studentCap;
    }

    public void setStudentCap(int studentCap) {
        this.studentCap = studentCap;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public @Email(message = "ContactEmail should be a valid email address") String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(@Email(message = "ContactEmail should be a valid email address") String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public @NotBlank(message = "ContactPhone is required") @Pattern(regexp = "^[0-9]{10}$", message = "ContactPhone must be a valid 10-digit phone number") String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(@NotBlank(message = "ContactPhone is required") @Pattern(regexp = "^[0-9]{10}$", message = "ContactPhone must be a valid 10-digit phone number") String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
