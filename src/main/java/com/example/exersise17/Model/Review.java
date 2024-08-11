package com.example.exersise17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(columnDefinition = " int not null")

    private int userId;
    @Column(columnDefinition = " int not null")
@NotNull
    private int productId;

    @Column(columnDefinition = " int not null")
@NotNull
    @PositiveOrZero
    private int rating;

    @Column(columnDefinition = " varchar(100) not null")
@NotEmpty
    private String comment;
}
