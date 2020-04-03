package com.group3.util;

import com.alibaba.fastjson.JSONObject;
import com.group3.entity.Music;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack
 * @Date: 2020/4/2 10:05
 * @Description:
 */
public class SpilderMusic {
    private static final Integer SUCCESS = 200;

    public static void main(String[] args) {
        getContent();
    }

    public static List<Music> getContent(){
        List<Music> musicList = new ArrayList<>();
        //网易云音乐链接
        String[] allUrl = {"https://music.163.com/m/discover/toplist?id=991319590",
                "https://music.163.com/m/discover/toplist"
        };
        for(int n=0;n<allUrl.length;n++){
            String url = allUrl[n];
            //网易云音乐链接请求头
            String userAgent = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36";
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            //设置请求头
            httpPost.setHeader("User-Agent", userAgent);
            HttpClientContext context = HttpClientContext.create();
            try {
                CloseableHttpResponse response = httpClient.execute(httpPost, context);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode==SUCCESS){
                    HttpEntity entity = response.getEntity();
                    String res= null;
                    res = EntityUtils.toString(entity, "UTF-8");
                    Document document = Jsoup.parse(res);
                    Elements elements = document.select(".m-sglst");
                    for(Element element :elements){
                        for(int i=0;i<element.childNodeSize();i++){
                            Element item = element.child(i);
                            String musicurl = item.attr("href");
                            String musicImage = null;
                            //二次请求界面获取图片
                            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                            HttpGet get = new HttpGet("https:"+musicurl);
                            HttpClientContext httpClientContext = HttpClientContext.create();
                            CloseableHttpResponse closeableHttpResponse = null;
                            try {
                                closeableHttpResponse = closeableHttpClient.execute(get,httpClientContext);
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            if(closeableHttpResponse.getStatusLine().getStatusCode() == SUCCESS){
                                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                                String re = null;
                                try{
                                    re = EntityUtils.toString(httpEntity,"UTF-8");
                                }catch (IOException e){
                                    e.printStackTrace();
                                }
                                Document doc = Jsoup.parse(re);
                                Elements it = doc.select(".cvrwrap img");
                                musicImage = doc.select(".cvrwrap img").attr("data-src");
                            }
                            //获取每首音乐的id
                            String musicId =musicurl.replace("//music.163.com/m/song?id=","");
                            //获取每首音乐的名字
                            String musicName = item.select(".sgtl").text();
                            //获取每首音乐的作者
                            String author =item.select(".sginfo").text();
                            //获取MKOnlinePlayer的对应json链接
                            String musicUrl = "http://music.uixsj.cn/api.php?callback=jQuery1113034466587161790607_1585798563762&types=url&id="+musicId+"&source=netease";
                            //获取音乐的下载链接
                            String downloadUrl=getUrl(musicUrl);
                            Music music = Music.builder().id(musicId).name(musicName).author(author).src(downloadUrl).img(musicImage).build();
                            musicList.add(music);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return musicList;
    }

    public static String getUrl(String url){
        HttpGet get = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.getStatusLine().getStatusCode()==SUCCESS){
            HttpEntity entity = response.getEntity();
            String res = null;
            try {
                res = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[]  str=res.replace(")","(").split("\\(");
            String jsonContent = str[1];
            JSONObject jsonObject = JSONObject.parseObject(jsonContent);
            return jsonObject.get("url").toString();
        }
        System.out.println("获取失败");
        return null;
    }
}
