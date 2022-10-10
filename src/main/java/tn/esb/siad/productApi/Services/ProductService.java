package tn.esb.siad.productApi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esb.siad.productApi.Entities.Product;
import tn.esb.siad.productApi.Repositories.ProductRepository;

import java.time.LocalDate;
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
    public ResponseEntity<?> findAllProducts()
    {
        List<Product> lstProducts=repository.findAll();
        if(lstProducts.isEmpty())
            return new ResponseEntity<>("There are no products in the stock", HttpStatus.NO_CONTENT);
       // return new ResponseEntity<>(lstProducts, HttpStatus.OK);
        //or
       // return ResponseEntity.ok(lstProducts);
        return ResponseEntity.status(HttpStatus.OK).body(lstProducts);
    }
    //find expired Products
    public ResponseEntity<?> findExpiredProducts()
    {
        List<Product> lstExpiredProducts=repository.findAll().stream()
                .filter(p->p.getExpirationDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        if(lstExpiredProducts.isEmpty())
            return new ResponseEntity<>("There are no expired products", HttpStatus.NO_CONTENT);

        return ResponseEntity.status(HttpStatus.OK).body(lstExpiredProducts);

    }
    //delete a product by id
    public ResponseEntity<?> deleteProductById(Long productId)
    {
        Optional<Product> res=repository.findById(productId);
        if(res.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no product with id = " + productId);
        repository.deleteById(productId);
        return ResponseEntity.ok("The product with id = " + productId+" was deleted successfully");
    }
    //update a product by id
    public ResponseEntity<?> updateProductById(Long productId, Product newProduct)
    {
        Optional<Product> res=repository.findById(productId);
        if(res.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no product with id = " + productId);
        return ResponseEntity.ok(repository.save(newProduct));
    }
}
