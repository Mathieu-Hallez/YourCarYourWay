package me.yourcaryourway.YourCarYourWay_WebAPI.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.ApiResponseDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.api.ErrorDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.authentication.TokenDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.dtos.authentication.AuthenticationDto;
import me.yourcaryourway.YourCarYourWay_WebAPI.models.User;
import me.yourcaryourway.YourCarYourWay_WebAPI.services.UserService;
import me.yourcaryourway.YourCarYourWay_WebAPI.services.configurations.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@Tag(name = "Authentication", description = "The Authentication API. Contains all the operations that can be performed for authentication.")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    @SecurityRequirements()
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Connect to the API calls",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation =  TokenDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    }
            )
    })
    public ResponseEntity<ApiResponseDto> getToken(@RequestBody AuthenticationDto authenticationDto) {
        User user = this.userService.getUser(authenticationDto.getEmail());

        if(user == null) {
            return new ResponseEntity<>(new ErrorDto("Unknown user login."), HttpStatus.UNAUTHORIZED);
        }

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok(new TokenDto(jwtService.generateToken(authentication)));
        } catch (BadCredentialsException badCredentialsException) {
            return new ResponseEntity<>(new ErrorDto("Error: " + badCredentialsException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
