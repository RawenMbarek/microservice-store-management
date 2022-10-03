package tn.esb.siad.productApi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esb.siad.productApi.Entities.Product;
import tn.esb.siad.productApi.Repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product product) {

        List<Product> allProducts=repository.findAll();
        return repository.save(product);
    }
}
