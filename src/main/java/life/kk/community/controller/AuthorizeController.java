package life.kk.community.controller;

import life.kk.community.DTO.AccessToken;
import life.kk.community.DTO.GithubUser;
import life.kk.community.provider.GithubProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code" ) String code, @RequestParam(name = "state") String state) throws IOException {
        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setState(state);
        accessToken.setClient_id("f282bb9de7ee51f1ca92");
        accessToken.setClient_secret("7a66076aa333ba6d236dfdffa32c104b6afaaafb");
        accessToken.setRedirect_uri("http://localhost:8887/callback");
        String token = githubProvider.getAccessToken(accessToken);
        GithubUser user = githubProvider.getUser(token);

        System.out.println(user.getName());

        return "index";
    }

}
