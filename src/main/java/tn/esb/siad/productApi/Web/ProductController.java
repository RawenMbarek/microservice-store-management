package tn.esb.siad.productApi.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esb.siad.productApi.Services.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;
}
