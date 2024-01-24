package com.example.Restoran.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurant")
public class ViewController {

 
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    
    @RequestMapping("/")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/beranda")
    public String beranda() {
        return "dashboard";
    }
    
    @RequestMapping("/menuRegister")
    public String menuRegister() {
    	return "menuRegister";
    }
    
    @RequestMapping("/order_pesanan")
    public String order_pesanan() {
    	return "order_pesanan";
    }
    
    @RequestMapping("/editMenu")
    public String editmenu() {
    	return "editMenu";
    }
    @RequestMapping("/editKaryawan")
    public String editkaryawan() {
    	return "editKaryawan";
    }
    
    @RequestMapping("/menuList")
    public String menuList() {
    	return "menuList";
    }
    @RequestMapping("/karyawan")
    public String karyawan() {
    	return "karyawan";
    }
}