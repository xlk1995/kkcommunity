package life.kk.community.provider;

import com.alibaba.fastjson.JSON;
import life.kk.community.DTO.AccessToken;
import life.kk.community.DTO.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;

@Component
public class GithubProvider {
    /**
     * 从GitHub中获取access_token
     * @return
     */
    public  String getAccessToken(AccessToken AccessToken){

        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String json = com.alibaba.fastjson.JSON.toJSONString(AccessToken);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String token = response.body().string();
            //String token = response.body().string().split("&")[0].split("=")[1];
            //System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accesstoken) {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.github.com/user?" + accesstoken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            //System.out.println("返回的东西是"+str);
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
