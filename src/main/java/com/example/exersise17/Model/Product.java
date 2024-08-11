package com.example.exersise17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4,message = "name must be more than 3 character")
    @Column(columnDefinition = " varchar(15) not null")
    private String name;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price must be positive number")
    @Column(columnDefinition = " int not null")

    private float price;

    @NotNull(message = "category id cannot be null")
    @Column(columnDefinition = " int not null")
    private int categoryID;

}
