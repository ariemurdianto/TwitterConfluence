package net.ariemurdianto.confluence.socialnetworking.servlet;

import com.atlassian.confluence.setup.webwork.ConfluenceVelocityContext;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.confluence.plugin.webresource.ConfluenceWebResourceManager;
import com.atlassian.confluence.plugin.webresource.DefaultConfluenceWebResourceManager;
import com.atlassian.confluence.userstatus.UserStatus;
import com.atlassian.confluence.userstatus.UserStatusManager;
import com.atlassian.spring.container.ContainerManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import org.apache.velocity.Template;
import net.ariemurdianto.confluence.socialnetworking.Constant;

/**
 * Created by IntelliJ IDEA.
 * User: arie
 * Date: Aug 26, 2009
 * Time: 10:29:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserStatusUpdateServlet extends HttpServlet {    
    private UserStatusManager userStatusManager;
    public void service(HttpServletRequest req, HttpServletResponse resp)
    {
        String userStatusText = req.getParameter(Constant.USER_STATUS);
        String username = req.getParameter(Constant.USERNAME);
        ServletOutputStream outputStream = null;
        String successFlag = "success";
        try
        {
            UserStatus userStatus = new UserStatus(username, userStatusText);
            userStatusManager.saveUserStatus(userStatus);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            successFlag = e.getMessage();
        }
        
        try
        {
            outputStream = resp.getOutputStream();
            outputStream.print("{\"successFlag\":\""+successFlag+"\"}");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setUserStatusManager(UserStatusManager userStatusManager)
    {
        this.userStatusManager = userStatusManager;
    }
}
