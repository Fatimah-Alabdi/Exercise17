package com.example.exersise17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@Column(columnDefinition = "int not null")
@NotNull
    private Integer userId;
    @Column(columnDefinition = "int not null")
@NotNull
    private Integer productId;
}
