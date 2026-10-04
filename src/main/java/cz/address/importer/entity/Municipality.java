package cz.address.importer.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Long code;

    @Column(nullable = false)
    private String name;

    public void setCode(Long code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
