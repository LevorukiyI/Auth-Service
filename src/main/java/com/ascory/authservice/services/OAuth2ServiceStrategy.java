package com.ascory.authservice.services;

import com.ascory.authservice.models.OAuth2ServiceName;
import com.ascory.authservice.models.User;

public interface OAuth2ServiceStrategy {
    String getAccessToken(String code);
    String getOAuth2Id(String accessToken);
    User getUserByOAuth2Id(String oAuth2Id);
    boolean existsByOAuth2Id(String oAuth2Id);
    OAuth2ServiceName getOAuth2ServiceName();
    void setOAuth2IdToUser(User user, String oAuth2Id);
}
