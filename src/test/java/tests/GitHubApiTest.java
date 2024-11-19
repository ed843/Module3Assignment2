package tests;

import com.solvd.eduncan.methods.github.*;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.IAbstractTest;
import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.Test;

public class GitHubApiTest implements IAbstractTest {
    Dotenv dotenv = Dotenv.load();
    @Test
    public void getUserByAccountIdTest() {
        GetUserByAccountIdMethod getUserMethod = new GetUserByAccountIdMethod(583231);
        getUserMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        getUserMethod.callAPI();
        getUserMethod.validateResponse();
    }

    @Test
    public void getUserByUsernameTest() {
        GetUserByUsernameMethod getUserMethod = new GetUserByUsernameMethod("octocat");
        getUserMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        getUserMethod.callAPI();
        getUserMethod.validateResponse();
    }

    @Test
    public void getAuthenticatedUserTest() {
        GetAuthenticatedUserMethod getAuthUserMethod = new GetAuthenticatedUserMethod();
        getAuthUserMethod.setHeaders("Authorization=Bearer " + dotenv.get("GITHUB_TOKEN"));
        getAuthUserMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        getAuthUserMethod.callAPI();
        getAuthUserMethod.validateResponse();
    }

    @Test
    public void updateAuthenticatedUserTest() {
        UpdateAuthenticatedUserMethod updateUserMethod = new UpdateAuthenticatedUserMethod(
                "This was written using Zebrunner tests and Java for testing GitHub's API"
        );

        updateUserMethod.setHeaders("Authorization=Bearer " + dotenv.get("GITHUB_TOKEN"));
        updateUserMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        updateUserMethod.callAPI();
        updateUserMethod.validateResponse();
    }

    @Test
    public void getUserUnauthorizedTest() {
        GetAuthenticatedUserMethod getAuthUserMethod = new GetAuthenticatedUserMethod();
        // Remove authorization header to test unauthorized access
        getAuthUserMethod.expectResponseStatus(HttpResponseStatusType.UNAUTHORIZED_401);
        getAuthUserMethod.callAPI();
    }

    @Test
    public void getUserNotFoundTest() {
        GetUserByAccountIdMethod getUserMethod = new GetUserByAccountIdMethod(999999999);
        getUserMethod.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        getUserMethod.callAPI();
    }

    @Test
    public void listUsersWithPaginationTest() {
        ListUsersMethod listUsersMethod = new ListUsersMethod();
        listUsersMethod.addParameter("per_page", "5");
        listUsersMethod.addParameter("since", "1");
        listUsersMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        listUsersMethod.callAPI();
        listUsersMethod.validateResponse();
    }

    @Test
    public void getAuthenticatedUserInvalidTokenTest() {
        GetAuthenticatedUserMethod getAuthUserMethod = new GetAuthenticatedUserMethod();
        // Set an invalid token to test error handling
        getAuthUserMethod.setHeaders("Authorization=Bearer invalid_token");
        getAuthUserMethod.expectResponseStatus(HttpResponseStatusType.UNAUTHORIZED_401);
        getAuthUserMethod.callAPI();
    }

    @Test
    public void getUserHovercardTest() {
        GetUserHovercardMethod hovercardMethod = new GetUserHovercardMethod(
                "octocat", "repository", "1300192"
        );
        hovercardMethod.setHeaders("Authorization=Bearer " + dotenv.get("GITHUB_TOKEN"));
        hovercardMethod.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        hovercardMethod.callAPI();
    }

    @Test
    public void testRateLimitHandling() {
        // Make multiple requests in quick succession to trigger rate limiting
        for (int i = 0; i < 10; i++) {
            GetUserByAccountIdMethod getUserMethod = new GetUserByAccountIdMethod(583231);

            // For the last request, we expect it might hit rate limit
            if (i == 9) {
                getUserMethod.expectResponseStatus(HttpResponseStatusType.FORBIDDEN_403);
            } else {
                getUserMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
            }

            getUserMethod.callAPI();
        }
    }

}