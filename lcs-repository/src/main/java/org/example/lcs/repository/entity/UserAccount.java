package org.example.lcs.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "USER_ACCOUNT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class UserAccount extends TimestampedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="SUR_NAME")
    private String surName;

    @Column(name="MOBILE_NUMBER" , unique = true)
    private String mobile;

    @Column(name="ID_CARD_NUMBER", unique = true)
    private String idCardNumber;

}
