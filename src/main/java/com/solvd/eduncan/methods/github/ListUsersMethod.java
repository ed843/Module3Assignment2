package com.solvd.eduncan.methods.github;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${base_url}/users", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/github/users/_get/rs.json")
public class ListUsersMethod extends AbstractApiMethodV2 {
    public ListUsersMethod() {
        super(null, "api/github/users/_get/rs.json");
        replaceUrlPlaceholder("base_url", "https://api.github.com");
        setHeaders("Accept=application/vnd.github+json");
        setHeaders("X-GitHub-Api-Version=2022-11-28");
    }
}