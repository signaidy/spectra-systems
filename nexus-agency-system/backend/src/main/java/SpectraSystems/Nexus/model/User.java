package SpectraSystems.Nexus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
    private Long id;

    private String username;
    private String password;
}
