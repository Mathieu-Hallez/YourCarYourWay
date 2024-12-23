package me.yourcaryourway.YourCarYourWay.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.yourcaryourway.YourCarYourWay.dtos.authentication.AuthenticationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@Tag(name = "Authentication", description = "The Authentication API. Contains all the operations that can be performed for authentication.")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    @SecurityRequirements()
    @ApiResponse(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Connect to the API calls",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            scheme = @Schema(implementation =  TokenDto.class)
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<Object> getToken(@RequestBody AuthenticationDto authenticationDto) {

    }
}
