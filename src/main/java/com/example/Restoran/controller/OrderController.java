package com.example.Restoran.controller;

import com.example.Restoran.model.OrderPesanan;
import com.example.Restoran.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    public ResponseEntity<List<OrderPesanan>> getAllMenuItems() {
        List<OrderPesanan> menuItems = orderRepository.findAll();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderPesanan> getMenuItemById(@PathVariable int id) {
        Optional<OrderPesanan> menuItem = orderRepository.findById(id);
        return menuItem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<OrderPesanan> createMenuItem(@RequestBody OrderPesanan order) {
        OrderPesanan savedOrder = orderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    
 // Controller
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<OrderPesanan>> searchMenuItems(@PathVariable String keyword) {
        List<OrderPesanan> menuItems = orderRepository.findByMenuItemContainingIgnoreCase(keyword);
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }    

    @PutMapping("/edit/{id}")
    public ResponseEntity<OrderPesanan> updateMenuItem(@PathVariable int id, @RequestBody OrderPesanan newOrder) {
        Optional<OrderPesanan> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            OrderPesanan existingOrder = optionalOrder.get();
            existingOrder.setMenuItem(newOrder.getMenuItem());
            existingOrder.setQuantity(newOrder.getQuantity());
            // set properti lainnya sesuai kebutuhan

            OrderPesanan updatedOrder = orderRepository.save(existingOrder);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	/*
	 * @PutMapping("/edit/{id}") public ResponseEntity<OrderPesanan>
	 * updateMenuItem(@PathVariable int id, @RequestBody OrderPesanan newOrder) {
	 * Optional<OrderPesanan> optionalOrder = orderRepository.findById(id); if
	 * (optionalOrder.isPresent()) { OrderPesanan existingOrder =
	 * optionalOrder.get(); existingOrder.setMenuItem(newOrder.getMenuItem());
	 * existingOrder.setQuantity(newOrder.getQuantity()); // set properti lainnya
	 * sesuai kebutuhan
	 * 
	 * OrderPesanan updatedOrder = orderRepository.save(existingOrder); return new
	 * ResponseEntity<>(updatedOrder, HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 */
    @DeleteMapping("/destroy/{id}")
    public ResponseEntity<HttpStatus> deleteMenuItem(@PathVariable int id) {
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
