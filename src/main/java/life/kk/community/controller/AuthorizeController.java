package life.kk.community.controller;

import life.kk.community.DTO.AccessToken;
import life.kk.community.DTO.GithubUser;
import life.kk.community.provider.GithubProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientID;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectID;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code" ) String code,
                           @RequestParam(name = "state") String state,
                           HttpSession session)
            throws IOException {
        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setState(state);
        accessToken.setClient_id(clientID);
        accessToken.setClient_secret(clientSecret);
        accessToken.setRedirect_uri(redirectID);
        String token = githubProvider.getAccessToken(accessToken);
        GithubUser user = githubProvider.getUser(token);
        //System.out.println(user.getName());
        System.out.println(user);
        if(user != null){
            // login success

            session.setAttribute("userinfo",user);
            return "redirect:/";
        }else {
            // login failed
            return "redirect:/";
        }
    }

}
