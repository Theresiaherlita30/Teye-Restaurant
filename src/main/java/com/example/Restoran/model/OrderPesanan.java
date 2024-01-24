package com.example.Restoran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class OrderPesanan {

	@Id
    @Column(name = "id")
    private int id;
    private String menuItem;
    private int quantity;
    public OrderPesanan(int id, String menuItem, int quantity) {
        super();
        this.id = id;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }
    public OrderPesanan() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
