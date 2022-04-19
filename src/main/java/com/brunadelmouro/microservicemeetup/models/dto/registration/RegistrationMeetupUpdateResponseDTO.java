package com.brunadelmouro.microservicemeetup.models.dto.registration;

public class RegistrationMeetupUpdateResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private String number;

    public RegistrationMeetupUpdateResponseDTO() {
    }

    public RegistrationMeetupUpdateResponseDTO(Integer id, String name, String email, String number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
