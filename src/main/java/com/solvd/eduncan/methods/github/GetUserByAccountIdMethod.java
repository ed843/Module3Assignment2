package com.solvd.eduncan.methods.github;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

import static io.netty.handler.codec.http.HttpHeaders.addHeader;


@Endpoint(url = "${base_url}/user/${account_id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/github/user/_get/rs.json")
public class GetUserByAccountIdMethod extends AbstractApiMethodV2 {
    public GetUserByAccountIdMethod(long accountId) {
        super(null, "api/github/user/_get/rs.json");

        replaceUrlPlaceholder("base_url", "https://api.github.com");
        replaceUrlPlaceholder("account_id", String.valueOf(accountId));

        setHeaders("Accept=application/vnd.github+json");
        setHeaders("X-GitHub-Api-Version=2022-11-28");
    }
}
