package com.example.springbootcrudh2swagger.controller;

import com.example.springbootcrudh2swagger.entity.Item;
import com.example.springbootcrudh2swagger.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/items")
@Tag(name = "Demo Item API", description = "CRUD operations for Items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    @Operation(summary = "Get all items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found")
    })
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item created successfully")
    })
    public Item createItem(@Valid @RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item updated successfully")
    })
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @Valid @RequestBody Item itemDetails) {
        try {
            Item updated = itemService.updateItem(id, itemDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch existing item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item patched successfully")
    })
    public ResponseEntity<Item> patchItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        try {
            Item patched = itemService.patchItem(id, itemDetails);
            return ResponseEntity.ok(patched);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item deleted successfully")
    })
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
