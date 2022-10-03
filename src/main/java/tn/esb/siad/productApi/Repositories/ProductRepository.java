package tn.esb.siad.productApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esb.siad.productApi.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
