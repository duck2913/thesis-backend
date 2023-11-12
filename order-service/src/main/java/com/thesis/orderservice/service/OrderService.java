package com.thesis.orderservice.service;

import com.thesis.orderservice.entity.Order;
import com.thesis.orderservice.entity.Status;
import com.thesis.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public void createNewOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }

    public List<Order> getOrdersByUserId(Integer userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Order> getAllOrdersForShipper() {
        return orderRepository.findByUseDeliveryTrueAndStatus(Status.DELIVERY);
    }

    public List<Order> getActiveOrdersForVendor() {
        return orderRepository.findByStatusNot(Status.DONE);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateOrderStatus(UUID orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order toBeUpdatedOrder = optionalOrder.get();
            toBeUpdatedOrder.setStatus(getNewStatusForOrder(toBeUpdatedOrder.getStatus(), toBeUpdatedOrder.getUseDelivery()));
            orderRepository.save(toBeUpdatedOrder);
        } else {
            throw new EntityNotFoundException("Entity with ID " + orderId + " not found");
        }
    }

    public Status getNewStatusForOrder(Status previousStatus, Boolean useDelivery) {
        return switch (previousStatus) {
            case NEW -> Status.COOKING;
            case COOKING -> {
                if (useDelivery) yield Status.DELIVERY;
                yield Status.DONE;
            }
            case DELIVERY -> Status.DONE;
            default -> throw new IllegalArgumentException("Unknown previousStatus: " + previousStatus);
        };
    }

    public void setShipperIdForOrder(Integer shipperId, UUID orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order toBeUpdatedOrder = optionalOrder.get();
            toBeUpdatedOrder.setShipperId(shipperId);
            orderRepository.save(toBeUpdatedOrder);
        } else {
            throw new EntityNotFoundException("Entity with ID " + orderId + " not found");
        }
    }


    public void testKafka() {
        Order testOrder = new Order();
        kafkaTemplate.send("test", testOrder);
    }
}