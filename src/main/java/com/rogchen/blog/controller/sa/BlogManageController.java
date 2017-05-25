package com.rogchen.blog.controller.sa;

import com.rogchen.admin.domain.AdminUser;
import com.rogchen.admin.service.AdminService;
import com.rogchen.blog.domain.BlogsContent;
import com.rogchen.common.Page;
import com.rogchen.common.utils.MapUtils;
import com.rogchen.common.utils.web.RenderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/7.
 * {tags}
 */
@Controller("blogManageController")
@RequestMapping(value = "admin/sa")
public class BlogManageController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("personalBlogList")
    public String PersonalBlogList(HttpServletRequest request, HttpServletResponse response, Model model){
//        String sb = getUrlFromBaidu();
//        model.addAttribute("sb",sb);
        return "blog/admin/blogManageList";
    }

    @RequestMapping(value = "gridJson1")
    @ResponseBody
    public void gridJson1(HttpServletRequest request, HttpServletResponse response){
        Map map = MapUtils.paramMap(request);
        map.put("a",123);
        List<BlogsContent>list = new ArrayList();
        BlogsContent blogsContent = new BlogsContent();
        for(int i=0;i<20;i++) {
            BlogsContent bc = new BlogsContent();
            bc.setId(Long.valueOf(i));
            bc.setBlogTitle("第"+i+"条数据");
            bc.setCreateTime(new Date());
            bc.setStatusCd(1l);
            bc.setBlogPicId(1l);
            list.add(bc);
        }
//        Page<BlogsContent> page = new Page<>();
//        page.setPageSize(10);
//        page.setPageNo(1);
//        blogsContent.setPage(page);
//        page.setContent(list);
//        System.out.println(page.getContent()+"\n:"+list.size());
//        return JSON.toString(page);
        RenderUtils.renderJson(response,list);
//        AjaxResponseUtils.renderSuccess(response,list);
    }

    @RequestMapping(value = "gridJson")
    @ResponseBody
    public void gridJson(HttpServletRequest request, HttpServletResponse response){

        Map map = MapUtils.paramMap(request);
        Page page = adminService.findPage(new Page<AdminUser>(request,response),new AdminUser(),map);
        System.out.println(page.getContent());
        RenderUtils.renderPageJson(response,page,"adminUserId,userName,adminUserStatusCd");
    }



    //百度发送请求查询
    public String getUrlFromBaidu(){
        try {
            String urlPath = new String("http://cn.bing.com/");
            //String urlPath = new String("http://localhost:8080/Test1/HelloWorld?name=丁丁".getBytes("UTF-8"));
            String param="/search?q=1&go=提交&qs=n&form=QBLH&sp=-1&pq=1&sc=8-1&sk=&cvid=701E3121CD5E4D58A615B984903E51F2";
            //建立连接
            URL url = new URL(urlPath);
            HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
            //设置参数
            httpConn.setDoOutput(true);   //需要输出
            httpConn.setDoInput(true);   //需要输入
            httpConn.setUseCaches(false);  //不允许缓存
            httpConn.setRequestMethod("GET");   //设置POST方式连接
            //设置请求属性
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");
            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            httpConn.connect();
            //建立输入流，向指向的URL传入参数
            DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
            dos.writeBytes(param);
            dos.flush();
            dos.close();
            //获得响应状态
            int resultCode=httpConn.getResponseCode();
                StringBuffer sb=new StringBuffer();
            if(HttpURLConnection.HTTP_OK==resultCode){
                String readLine=new String();
                BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
                while((readLine=responseReader.readLine())!=null){
                    sb.append(readLine).append("\n");
                }
                responseReader.close();
                System.out.println(sb.toString());
            }
                return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
