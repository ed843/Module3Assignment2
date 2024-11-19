package com.solvd.eduncan.methods.github;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${base_url}/user", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/github/user/_get/rs_auth.json")
public class GetAuthenticatedUserMethod extends AbstractApiMethodV2 {
    public GetAuthenticatedUserMethod() {
        super(null, "api/github/user/_get/rs_auth.json");
        replaceUrlPlaceholder("base_url", "https://api.github.com");
        setHeaders("Accept=application/vnd.github+json");
        String token = System.getenv("GITHUB_TOKEN");

        setHeaders("X-GitHub-Api-Version=2022-11-28");
    }
}
