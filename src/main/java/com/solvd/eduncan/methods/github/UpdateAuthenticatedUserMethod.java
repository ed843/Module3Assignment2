package com.solvd.eduncan.methods.github;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${base_url}/user", methodType = HttpMethodType.PATCH)
@ResponseTemplatePath(path = "api/github/user/_patch/rs.json")
public class UpdateAuthenticatedUserMethod extends AbstractApiMethodV2 {
    public UpdateAuthenticatedUserMethod(String bio) {
        super("api/github/user/_patch/rq.json", "api/github/user/_patch/rs.json");
        replaceUrlPlaceholder("base_url", "https://api.github.com");
        setHeaders("Accept=application/vnd.github+json");
        setHeaders("X-GitHub-Api-Version=2022-11-28");
        addProperty("bio", bio);
    }

    public static void main(String[] args) {
        System.out.println(System.getenv("GITHUB_TOKEN"));
    }
}