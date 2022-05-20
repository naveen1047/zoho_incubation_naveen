package com.naveen.jersey_db.user.models;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
@Getter
@Setter
public class User {
    @XmlAttribute(name = "id")
    private int id;

    @XmlAttribute(name = "password")
    private String password;

    @XmlAttribute(name = "uri")
    private String uri;

    @XmlAttribute(name = "roles")
    private Set<Role> roles;

    @XmlAttribute(name = "name")
    private String name;
}
