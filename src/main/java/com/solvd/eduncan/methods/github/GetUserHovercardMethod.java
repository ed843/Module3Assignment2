package com.solvd.eduncan.methods.github;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.QueryParam;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${base_url}/users/${username}/hovercard", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/github/user/_get/rs.json")
public class GetUserHovercardMethod extends AbstractApiMethodV2 {
    public GetUserHovercardMethod(String username, String subjectType, String subjectId) {
        super(null, "api/github/user/_get/rs.json");
        replaceUrlPlaceholder("base_url", "https://api.github.com");
        replaceUrlPlaceholder("username", username);
        addUrlParameter("subject_type", subjectType);
        addUrlParameter("subject_id", subjectId);
        setHeaders("Accept=application/vnd.github+json");
        setHeaders("X-GitHub-Api-Version=2022-11-28");
    }
}