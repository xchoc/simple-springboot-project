package com.example.simple.springboot.project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Sector.getSortedSectors",
                query = "SELECT * FROM get_sorted_sectors()",
                resultClass = Sector.class
        )
})
public class Sector {

    @Id
    private Integer value;

    @Column
    private String name;

    @Column(name = "parent_value")
    private Integer parentValue;

    @ManyToMany(mappedBy = "sectors")
    private List<User> users;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentValue() {
        return parentValue;
    }

    public void setParentValue(Integer parentValue) {
        this.parentValue = parentValue;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "value=" + value +
                ", name='" + name + '\'' +
                ", parentValue=" + parentValue +
                '}';
    }
}
