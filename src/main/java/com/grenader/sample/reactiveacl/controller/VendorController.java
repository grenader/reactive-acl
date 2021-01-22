package com.grenader.sample.reactiveacl.controller;


import com.grenader.sample.reactiveacl.dto.ItemId;
import com.grenader.sample.reactiveacl.dto.VendorItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class VendorController {

    @GetMapping("/vendor-item/default")
    @ResponseBody
    public VendorItem getDefault() {
        return VendorItem.builder().id("11").name("Test Vendor Item").build();
    }

    @PostMapping("/vendor-item")
    public ResponseEntity<ItemId> postCreate(@RequestBody VendorItem item) {

        // generate Id
        item.setId(UUID.randomUUID().toString());

        // save it.
        final ItemId itemId = ItemId.builder().id(item.getId()).build();
        return new ResponseEntity<ItemId>(itemId, HttpStatus.CREATED);
    }

}