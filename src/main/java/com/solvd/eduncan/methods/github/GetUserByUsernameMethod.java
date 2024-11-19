package com.solvd.eduncan.methods.github;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

// GetUserByUsernameMethod.java
@Endpoint(url = "${base_url}/users/${username}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/github/user/_get/rs.json")
public class GetUserByUsernameMethod extends AbstractApiMethodV2 {
    public GetUserByUsernameMethod(String username) {
        super(null, "api/github/user/_get/rs.json");
        replaceUrlPlaceholder("base_url", "https://api.github.com");
        replaceUrlPlaceholder("username", username);
        setHeaders("Accept=application/vnd.github+json");
        setHeaders("X-GitHub-Api-Version=2022-11-28");
    }
}