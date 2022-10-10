package tn.esb.siad.productApi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esb.siad.productApi.Entities.Product;
import tn.esb.siad.productApi.Services.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/all")
    public ResponseEntity<?> getProducts()
    {return service.findAllProducts();}
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id)
    {return service.getProduct(id);}
    @PostMapping("/add")
    public Product addOneProduct(@RequestBody Product product) //add @Valid
    {return service.addProduct(product);}
}
