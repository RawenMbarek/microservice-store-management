package tn.esb.siad.productApi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esb.siad.productApi.Entities.Product;
import tn.esb.siad.productApi.Repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product product) {

        List<Product> allProducts=repository.findAll();
        //verify if the product already exists
        List<Product> lstRes=allProducts.stream() //convert from List<Product> to Stream<Product>
                .filter(p->p.equals(product)) //returns the product p if it exists, if not return nothing
                .collect(Collectors.toList());
        if(!lstRes.isEmpty()) //the product already exists
        {
            Product p=lstRes.get(0);//the attribute name is unique
            //update the stock
            p.setStock(p.getStock()+product.getStock());
           return repository.save(p);
        }

        return repository.save(product);
    }

    public ResponseEntity<Product> getProduct(Long id)
    {
         Optional<Product> res=repository.findById(id);
         if(res.isEmpty())
             return new ResponseEntity("The product with id = " + id + " does not exist", HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(res.get(), HttpStatus.OK);

    }
    //findAllProducts
    //find expired Products
    //delete and update a product by id
}
