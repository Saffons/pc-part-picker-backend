package com.zti.partpicker.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import com.zti.partpicker.model.AccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling token-related operations and endpoints.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/token")
public class TokenController {

    private final JwtEncoder encoder;

    public TokenController(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * Function for building JSON Web Token
     *
     * @param authentication Authentication object that has information about current user's
     *                       login information and authorities
     * @return JWT that is used for authentication for other endpoints
     */

    @PostMapping()
    public String token(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .id(((AccountDetails) authentication.getPrincipal()).getId().toString())
                .claim("scope", scope)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}