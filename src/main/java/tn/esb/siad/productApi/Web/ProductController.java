package tn.esb.siad.productApi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esb.siad.productApi.Entities.Product;
import tn.esb.siad.productApi.Services.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/all")
    //or
    //@RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getProducts()
    {return service.findAllProducts();}
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id)
    {return service.getProduct(id);}
    @PostMapping("/add")
    public ResponseEntity<?> addOneProduct(@Valid @RequestBody Product product) //add @Valid
    {return service.addProduct(product);}
    @GetMapping("/expired")
    public ResponseEntity<?> getExpiredProducts()
    {return service.findExpiredProducts();}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable Long id)
    {return service.deleteProductById(id);}
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody Product newProduct)
    {
        return service.updateProductById(id,newProduct);
    }
}
