package com.ecommerce.controller;

import com.ecommerce.dto.response.ApiResponse;
import com.ecommerce.dto.response.ProductResponseDTO;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import com.ecommerce.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getWishlist() {
        User currentUser = userService.getCurrentUser();
        List<ProductResponseDTO> wishlistItems = wishlistService.getWishlist(currentUser);
        return ResponseEntity.ok(new ApiResponse<>(
                "Wishlist retrieved successfully",
                wishlistItems,
                true
        ));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addToWishlist(@RequestBody AddToWishlistRequest request) {
        User currentUser = userService.getCurrentUser();
        wishlistService.addToWishlist(currentUser, request.getProductId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "Product added to wishlist",
                "success",
                true
        ));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<ApiResponse<String>> removeFromWishlist(@PathVariable String productId) {
        User currentUser = userService.getCurrentUser();
        wishlistService.removeFromWishlist(currentUser, productId);
        return ResponseEntity.ok(new ApiResponse<>(
                "Product removed from wishlist",
                "success",
                true
        ));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<String>> clearWishlist() {
        User currentUser = userService.getCurrentUser();
        wishlistService.clearWishlist(currentUser);
        return ResponseEntity.ok(new ApiResponse<>(
                "Wishlist cleared",
                "success",
                true
        ));
    }

    @PostMapping("/{productId}/move-to-cart")
    public ResponseEntity<ApiResponse<String>> moveToCart(@PathVariable String productId) {
        User currentUser = userService.getCurrentUser();
        wishlistService.moveToCart(currentUser, productId);
        return ResponseEntity.ok(new ApiResponse<>(
                "Product moved to cart",
                "success",
                true
        ));
    }

    // Request DTO
    public static class AddToWishlistRequest {
        private String productId;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }
}
