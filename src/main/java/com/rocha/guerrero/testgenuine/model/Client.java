package com.rocha.guerrero.testgenuine.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
    @Table(name = "clients") // Nombre de la tabla en la base de datos
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name", nullable = false, length = 255)
        private String firstName;

        @Column(name = "last_name", nullable = false, length = 255)
        private String lastName;

        @Column(name = "email", nullable = false, length = 255)
        private String email;

        @Column(name = "address", nullable = false, length = 1000)
        private String address;

        @Column(name = "phone", nullable = false)
        private long phone;

        @Column(name = "new_employee")
        private boolean newEmployee;

        @Column(name = "birthday", nullable = false)
        private Date birthday;

        @Column(name = "data", nullable = true, length = 10000)
        private String data;
}
