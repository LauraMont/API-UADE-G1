package com.uade.tpo.marketplace.controllers.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.tpo.marketplace.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    private Long userId;
}
