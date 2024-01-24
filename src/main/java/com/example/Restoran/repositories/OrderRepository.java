package com.example.Restoran.repositories;

import com.example.Restoran.model.OrderPesanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderPesanan, Integer> {
    List<OrderPesanan> findByMenuItemContainingIgnoreCase(String keyword);

	List<OrderPesanan> findByIdOrMenuItemContainingIgnoreCaseOrQuantity(int parseInt, String keyword, int parseInt2);
}
