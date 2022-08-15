package com.mirae.smartfactory.domain.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirae.smartfactory.domain.furnace.Process;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long memberId;
    private String name;
    private TITLE title;
    private RoleType roleType;
    private String id;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Process> processes = new ArrayList<>();
}
