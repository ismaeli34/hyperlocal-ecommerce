package com.example.hyperlocalecommerce.hyperlocalecommerce.services;



import com.example.hyperlocalecommerce.hyperlocalecommerce.dtos.CreateOrderRequest;
import com.example.hyperlocalecommerce.hyperlocalecommerce.dtos.OrderDto;
import com.example.hyperlocalecommerce.hyperlocalecommerce.dtos.PageableResponse;

import java.util.List;

public interface OrderService {

    //create order
    OrderDto createOrder(CreateOrderRequest orderDto);

    //remove order
    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);

    //order methods(logic) related to order

}
