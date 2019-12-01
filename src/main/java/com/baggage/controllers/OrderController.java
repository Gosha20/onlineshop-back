package com.baggage.controllers;

import com.baggage.entity.Basket;
import com.baggage.entity.CustomResponse;
import com.baggage.entity.dao.ClientDao;
import com.baggage.entity.dao.OrderDao;
import com.baggage.entity.dao.ProductDao;
import com.baggage.service.ClientService;
import com.baggage.service.OrderService;
import com.baggage.service.ProductService;
import com.baggage.utils.CustomError;
import com.baggage.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.baggage.utils.Constants.AUTH_HEADER_NAME;
import static com.baggage.utils.Constants.INTERNAL_ERROR;

@RestController
public class OrderController {

    private final ClientService clientService;
    private final UserDetailsService customUserDetailsService;
    private final ProductService productService;
    private final OrderService orderService;

    private Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(ClientService clientService,
                           @Qualifier("customUserDetailsService") UserDetailsService customUserDetailsService,
                           ProductService productService, OrderService orderService) {
        this.clientService = clientService;
        this.customUserDetailsService = customUserDetailsService;
        this.productService = productService;
        this.orderService = orderService;
    }


    @GetMapping(value = "/api/getBasket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsInOrder(@RequestHeader(value = AUTH_HEADER_NAME) String authHeader) {
        try {
            String userName = TokenUtil.getUserNameFromToken(authHeader);
            Optional<ClientDao> client = clientService.findByUsername(userName);
            if (client.isPresent()) {
                List<OrderDao> listOrders = orderService.findAllByUserId(client.get().getId());
                List<ProductDao> productDaoList = new ArrayList<>();
                for (OrderDao order:listOrders) {
                    Optional<ProductDao> product = productService.findById(order.getId());
                    if (product.isPresent()) {
                        productDaoList.add(product.get());
                    } else {
                        log.warn("not found product from basket. Id = " + order.getId());
                    }
                }
                return new ResponseEntity<>(
                        new CustomResponse<>(
                                "OK",
                                Optional.empty(),
                                Optional.of(new Basket(productDaoList))
                        ), HttpStatus.OK
                       );
            } else {
                throw new CustomError("user not found by session");
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new CustomResponse<>(INTERNAL_ERROR,
                    Optional.of("Error while taking products in order"),
                    Optional.empty()), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/api/addProductInOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProductsInOrder(@RequestHeader(value = AUTH_HEADER_NAME) String authHeader,
                                                @RequestParam(name = "productId") Integer productId) {
        try {
            String userName = TokenUtil.getUserNameFromToken(authHeader);
            Optional<ClientDao> client = clientService.findByUsername(userName);
            Optional<ProductDao> productDao = productService.findById(productId);

            if (client.isPresent() && productDao.isPresent()) {
                orderService.appendInOrder(client.get().getId(), productId);
                return new ResponseEntity<>(
                        new CustomResponse<>("OK", Optional.empty(), Optional.empty()), HttpStatus.OK
                );
            } else {
                throw new CustomError("user not found by session");
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new CustomResponse<>(INTERNAL_ERROR,
                    Optional.of("Error while adding products in order"),
                    Optional.empty()), HttpStatus.OK);
        }
    }


//    @GetMapping(value = "/api/deleteProductInBasket", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> deleteProductsInBasket(@RequestHeader(value = AUTH_HEADER_NAME) String authHeader,
//                                                    @RequestParam(name = "productId") Integer productId) {
//        try {
//            String userName = TokenUtil.getUserNameFromToken(authHeader);
//            Optional<ClientDao> client = clientService.findByUsername(userName);
//            if (client.isPresent()) {
//                List<OrderDao> orderDaos = orderService.findAllByUserId(productId);
//                Optional<OrderDao> orderDao = orderDaos.stream().findFirst((OrderDao od) -> od.getProductId().equals(productId));
//
//                if (orderDao.isPresent()) {
//                    orderService.deleteFromOrder(orderDao.get().getId());
//                    return new ResponseEntity<>(
//                            new CustomResponse<>("OK", Optional.empty(), Optional.empty()), HttpStatus.OK
//                    );
//                } else {
//                    throw new CustomError("product not found in order");
//                }
//            } else {
//                throw new CustomError("user not found by session");
//            }
//        } catch (Exception e) {
//            log.warn(e.getMessage());
//            return new ResponseEntity<>(new CustomResponse<>(INTERNAL_ERROR,
//                    Optional.of("Error while delete products in order"),
//                    Optional.empty()), HttpStatus.OK);
//        }
//    }
}
