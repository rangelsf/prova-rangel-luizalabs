package com.prova.rangel.luizalabs.prova.infraestructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;

import com.prova.rangel.luizalabs.prova.domain.request.*;
import com.prova.rangel.luizalabs.prova.domain.response.*;
import com.prova.rangel.luizalabs.prova.domain.usecase.*;

@RequiredArgsConstructor
@RestController
public class WishListController {

	@Autowired
	private CreateWishList createWishList;
	
	@Autowired
	private FindWishListById findWishListById;
	
	@Autowired 
	private AddProductOnWishList addProductOnWishList;
	
	@Autowired
	private FindIfProductIsOnWishList findIfProductIsOnWishList;
	
	@Autowired
	private FindAllProductsOnWishList findAllProductsOnWishList;
	
	@Autowired
	private DeleteProductByIdFromWishList deleteProductByIdFromWishList;
	
	final static Logger log = LoggerFactory.getLogger(WishListController.class);
	
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateWishListResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Client Id and wish list name are mandatory",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Wish List or client not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))})
    })
	@Operation(summary = "Create a new Wish List")
	@PostMapping("/wishlist")
	public CreateWishListResponse createWishList(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description= "Request for create wish list", required = true, 
					content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = CreateWishListRequest.class)))  
			@RequestBody CreateWishListRequest request) {
		log.info("Request to create wish list: {}  ", request.toString());
		return createWishList.create(request.getClientId(), request.getName());
	}
	
	
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FindWishListByIdResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Wish list id is mandatory",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Wish list not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))})
    })
    @Operation(summary = "Search a Wish List by id")
	@GetMapping("/wishlist/{wishListId}")
	public FindWishListByIdResponse findWishListById(@Parameter(description = "Wish list Id") @PathVariable String wishListId) {
    	log.info("request to find wish list by id");
    	return findWishListById.findWishListById(wishListId);
		
	}
    
 
    
    @Operation(summary = "Add a product to a wish list")
    @PostMapping("/wishlist/product")
    public AddProductOnWishListResponse addProductOnWishList(@RequestBody AddProductOnWishListRequest request) {
    	log.info("Request to add product: {}  ", request.toString());
    	return addProductOnWishList.add(request);
    }
    
    @Operation(summary = "Search if a product is on a wish list")
    @GetMapping("/wishlist/{wishListId}/product/{productId}")
    public FindIfProductIsOnWishListResponse findIfProductIsOnWishList(@Parameter(description = "Wish list id.") @PathVariable String wishListId,
    		@Parameter(description = "Product id") @PathVariable String productId
            ) {
    	log.info("Request to find product on wish list");
    	return findIfProductIsOnWishList.findIfProductIsOnWishList(wishListId, productId);
    }
    
    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FindAllProductsOnWishListResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Wish list if is mandatory",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The wish list is empty",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))})
    })        
    @Operation(summary = "Search all products on a wish list")
    @GetMapping("/wishlist/{wishListId}/products/")
    public FindAllProductsOnWishListResponse findAllProductsOnWishList(@Parameter(description = "Wish list id.") @PathVariable String wishListId) {
    	log.info("Request to find all products on a wish list");
    	return findAllProductsOnWishList.findAllProductsOnWishList(wishListId);
    }
    
    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Product id and wish list id is mandatory",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))})
    })   
    @Operation(summary = "Delete a product from the wish list")
    @DeleteMapping("/wishlist/{wishListId}/product/{productId}")
    public void deleteProductByIdFromWishList(@Parameter(description = "Wish list id") @PathVariable String wishListId,
    @Parameter(description = "Product id") @PathVariable String productId){
    	deleteProductByIdFromWishList.deleteProductByIdFromWishList(productId, wishListId);
	}
    

}
