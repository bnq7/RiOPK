package com.webresources.model;

import com.hrdepartment.model.enums.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Tertiary {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Marital marital;
    @Enumerated(EnumType.STRING)
    private Origin origin;
    @Enumerated(EnumType.STRING)
    private Citizenship citizenship;
    @Enumerated(EnumType.STRING)
    private YesNo retiree;
    @Enumerated(EnumType.STRING)
    private YesNo conscripted;
    @Enumerated(EnumType.STRING)
    private Disability disability;

    public Tertiary() {
        marital = Marital.SINGLE;
        origin = Origin.MINSK;
        citizenship = Citizenship.BELARUS;
        retiree = YesNo.NO;
        conscripted = YesNo.NO;
        disability = Disability.NO;
    }

    public void set(Marital marital, Origin origin, Citizenship citizenship, YesNo retiree, YesNo conscripted, Disability disability) {
        this.marital = marital;
        this.origin = origin;
        this.citizenship = citizenship;
        this.retiree = retiree;
        this.conscripted = conscripted;
        this.disability = disability;
    }
}
