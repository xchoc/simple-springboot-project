package com.example.simple.springboot.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    private String id;

    @Column
    private String name;

    @Column(name = "agree_to_terms")
    private boolean agreeToTerms;

    @ManyToMany
    @JoinTable(
            name="UserSectorMap",
            joinColumns=@JoinColumn(name="\"user_id\"", referencedColumnName="id", insertable = false, updatable = false),
            inverseJoinColumns=@JoinColumn(name="sector_value", referencedColumnName="value", insertable = false, updatable = false))
    private List<Sector> sectors;

    public User() {
    }

    public User(String id, String name, boolean agreeToTerms, List<Sector> sectors) {
        this.id = id;
        this.name = name;
        this.agreeToTerms = agreeToTerms;
        this.sectors = sectors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAgreeToTerms() {
        return agreeToTerms;
    }

    public void setAgreeToTerms(boolean agreeToTerms) {
        this.agreeToTerms = agreeToTerms;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

}
