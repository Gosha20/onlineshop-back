package com.baggage.controllers;

import com.baggage.entity.CustomResponse;
import com.baggage.service.ClientService;
import com.baggage.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.baggage.utils.Constants.INTERNAL_ERROR;
import static com.baggage.utils.Constants.OK;

@RestController
public class ProductController {

    private final ClientService clientService;
    private final UserDetailsService customUserDetailsService;
    private final ProductService productService;

    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ClientService clientService,
                             @Qualifier("customUserDetailsService") UserDetailsService customUserDetailsService,
                             ProductService productService) {
        this.clientService = clientService;
        this.customUserDetailsService = customUserDetailsService;
        this.productService = productService;
    }


    @GetMapping(value = "/api/allProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProducts() {
        try {
                return new ResponseEntity<>(
                        new CustomResponse<>(
                                OK,
                                Optional.empty(),
                                Optional.of(productService.findAll())
                        ), HttpStatus.OK);

        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new CustomResponse<>(INTERNAL_ERROR,
                    Optional.of("Error while taking products"),
                    Optional.empty()), HttpStatus.OK);
        }
    }

}
