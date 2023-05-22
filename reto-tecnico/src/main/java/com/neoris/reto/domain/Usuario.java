package com.neoris.reto.domain;

import javax.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(	name = "tb_usuario",uniqueConstraints = @UniqueConstraint(name = "uq_email",columnNames ={"email"})
)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            foreignKey = @ForeignKey(name = "fk_usuario",value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(name = "fk_rol",value = ConstraintMode.NO_CONSTRAINT))
    private Set<Rol> roles = new HashSet<>();

}
