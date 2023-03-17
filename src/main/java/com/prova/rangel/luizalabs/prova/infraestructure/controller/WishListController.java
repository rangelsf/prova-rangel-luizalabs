package com.prova.rangel.luizalabs.prova.infraestructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController("/wishlist")
public class WishListController {

	@Autowired
	private CreateWishList createWishList;
	
	@Autowired
	private FindWishListById findWishListById;
	
	final static Logger logging = LoggerFactory.getLogger(WishListController.class);
	
	@PostMapping
	public CreateWishListResponse createWishList(@io.swagger.v3.oas.annotations.parameters.RequestBody(description= "a", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateWishListRequest.class)))  @RequestBody CreateWishListRequest request) {
		logging.info("request: {}  ", request.toString());
		return createWishList.create(request.getClientId(), request.getName());
	}
	
	
	@Operation(summary = "Search a Wish List by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FindWishListByIdResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Wish List id is mandatory.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Wish List not found.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class))})
    })
	@GetMapping("/find-by-id/{id}")
	public FindWishListByIdResponse findWishListById(@Parameter(description = "Wish List Id") @PathVariable String id) {
		return findWishListById.findWishListByIdResponse(id);
		
	}

}
