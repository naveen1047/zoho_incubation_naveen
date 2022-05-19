package com.naveen.jersey_db.user.models;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
@Getter
@Setter
public class User {
    @XmlAttribute(name = "user")
    private int id;

    @XmlAttribute(name = "uri")
    private String uri;

    @XmlAttribute(name = "name")
    private String name;
}
