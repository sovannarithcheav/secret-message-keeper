package com.sovannarith.secretmessagekeeper.service;

import com.sovannarith.secretmessagekeeper.util.Utils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.entity.mime.MIME.UTF8_CHARSET;

@Service
public class MessageUploadService {
    private static String CHANNEL_ID = "CQSU5UQ5A";
    private static String TOKEN = "Bearer xoxp-683056309702-680913572720-848870719028-5df2100850e60f02ddbbf96f9106563d";

    public String retrieve(String ts) throws IOException, URISyntaxException {
        ts = Utils.toCharInt(ts);
        String RETRIEVE_URL = "https://slack.com/api/channels.history";
        URIBuilder builder = new URIBuilder(RETRIEVE_URL);
        builder.addParameter("inclusive", "1")
                .addParameter("count", "1")
                .addParameter("channel", CHANNEL_ID)
                .addParameter("latest", ts)
                .addParameter("pretty", "1");
        Header header = new BasicHeader("Authorization", TOKEN);
        HttpGet get = new HttpGet(builder.build());
        get.setHeader(header);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        String responseJSON = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
        JSONObject message = new JSONObject(responseJSON).getJSONArray("messages").getJSONObject(0);
        if (ts.equals(message.getString("ts")))
            return new JSONObject(responseJSON).getJSONArray("messages").getJSONObject(0).getString("text");
        return "";
    }

    public String post(String message) throws IOException {
        String POST_URL = "https://slack.com/api/chat.postMessage";
        HttpPost post = new HttpPost(POST_URL);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("channel", CHANNEL_ID));
        params.add(new BasicNameValuePair("text", message));
        params.add(new BasicNameValuePair("username", "Secret Message Sender ii"));
        params.add(new BasicNameValuePair("icon_url", "https://i.imgur.com/IarwFXz.png"));
        post.setEntity(new UrlEncodedFormEntity(params));

        Header header = new BasicHeader("Authorization", TOKEN);
        post.setHeader(header);

        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
        String responseJSON = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);

        return Utils.toCharStr(new JSONObject(responseJSON).getString("ts"));
    }


}
