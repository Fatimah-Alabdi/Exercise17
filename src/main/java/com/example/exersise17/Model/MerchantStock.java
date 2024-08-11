package com.example.exersise17.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "productId cannot be null")
    @Column(columnDefinition = "int not null")
    private int productId;
    @NotNull(message = "merchantId cannot be null")
    @Column(columnDefinition = "int not null")
    private int merchantId;
    @NotNull(message = "stuck cannot be null")
    @Min(value = 11, message = "the minimum number of stuck must be more than 10")
    @Column(columnDefinition = "int not null")
    private int stuck;
}
