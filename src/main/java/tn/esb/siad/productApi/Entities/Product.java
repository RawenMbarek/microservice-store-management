package tn.esb.siad.productApi.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @EqualsAndHashCode.Include
    @Column(unique = true, nullable=false)
    private String name;
    @NonNull
    private BigDecimal price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fabricationDate;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @EqualsAndHashCode.Include
    private LocalDate expirationDate;
    private String description;
    private int stock;
}
