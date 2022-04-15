package com.exampleJWT.practice.entity;

import com.exampleJWT.practice.enums.Roles;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE user SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name= "uuid", strategy = "uuid2")
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @CreationTimestamp
    @Column(name = "TIMESTAMPS", nullable = false, updatable = false)
    private LocalDateTime timestamps;

    @Column(name = "SOFT_DELETE", nullable = false)
    private Boolean softDelete = false;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public UserEntity(String firstName, String lastName, String email, String password, Roles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password =password;
        this.role= role;
    }


}
