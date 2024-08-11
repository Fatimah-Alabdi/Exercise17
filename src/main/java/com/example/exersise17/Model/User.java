package com.example.exersise17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 6,message = "username must be more than 5 character")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @NotEmpty(message = "password cannot be empty")
    //@Column(columnDefinition = "varchar(50) not null check (LENGTH(password) >= 8")
    private String password;

    @NotEmpty(message = "email cannot be empty")
    @Email
    @Column(columnDefinition = "varchar(30) not null unique ")
    private String email;

    @NotEmpty(message = "role cannot be null")
    @Pattern(regexp = "Admin|Customer", message = "role must be either 'Admin' or 'Customer' only")
    //@Column(columnDefinition = "varchar(9) not null check (role='Admin' or role='Customer')")

    private String role;

    @NotNull(message = "balance cannot be null")
    @Positive(message = "balance must be positive number")
    @Column(columnDefinition = "int not null")
    private float balance;
}
