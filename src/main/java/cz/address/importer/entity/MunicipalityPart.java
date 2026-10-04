package cz.address.importer.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class MunicipalityPart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Long code;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "municipality_code", referencedColumnName = "code", nullable = false)
    private Municipality municipality;

    public void setCode(Long code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }
}
