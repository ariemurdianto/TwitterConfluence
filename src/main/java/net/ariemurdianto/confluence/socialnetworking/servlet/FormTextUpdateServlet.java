package net.ariemurdianto.confluence.socialnetworking.servlet;

import com.atlassian.user.UserManager;
import com.atlassian.user.User;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;
import com.atlassian.bandana.BandanaManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import net.ariemurdianto.confluence.socialnetworking.settings.UserTwitterSetting;
import net.ariemurdianto.confluence.socialnetworking.manager.SocialnetworkingUserManager;
import net.ariemurdianto.confluence.socialnetworking.Constant;

/**
 * Created by IntelliJ IDEA.
 * User: arie
 * Date: Aug 25, 2009
 * Time: 8:21:14 AM
 * To change this template use File | Settings | File Templates.
 */
/**
 * This servlet is to provide text update of social networking of twitter
*/
public class FormTextUpdateServlet extends HttpServlet {
    private BandanaManager bandanaManager;
    private SocialnetworkingUserManager socialnetworkingUserManager;
    
    public void service(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            User user = AuthenticatedUserThreadLocal.getUser();
            ServletOutputStream output = response.getOutputStream();
// user has not logged in yet           
            if(user == null)
            {
                output.print(Constant.MESSAGE_NOT_LOGIN);
                return;
            }
            String username = user.getName();
            UserTwitterSetting userTwitterSetting = socialnetworkingUserManager.getTwitterSetting(username);
// a bit ugly indeed :(. Let's think later           
            String additionalParameterSetUpdate = ",'',0";
            if(userTwitterSetting.getTwitterSetting().equals(Constant.UPDATE_TWITTER_CONFLUENCE))
            {
                additionalParameterSetUpdate = ",'"+username+"',1";
            }

            String htmlOutput = "<html>\n" +
                    "<script>\n" +
                    "jQuery(document).ready(function(){\n" +
                    "getTwitterUpdates(1, '"+userTwitterSetting.getUsername()+"', '"+userTwitterSetting.getPassword()+"', "+userTwitterSetting.getNumberToDisplay()+", 'grabbedHtml'"+additionalParameterSetUpdate+");\n" +
                    "});\n" +
                    "</script>\n" +
                    "<body>\n" +
                    "<table>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<div id=\"boxy-twitter\">\n" +
                    "<form name=\"popupForm\">\n" +
                    "<table>\n" +
                    "<tr>\n" +
                    "<td>"+"<input type=\"text\" name=\"textUpdate\" style=\"width: 285px; height: 30px; font-size: 18\"/>\n"+"</td>\n" +
                    "<td>"+"<input type=\"button\" style=\"height: 38px;width: 85px;font-weight:bold; font-size: 20; font-family:Verdana\" onclick=\"setTwitterUpdates('"+userTwitterSetting.getUsername()+"', '"+userTwitterSetting.getPassword()+"', this.form.textUpdate.value, 'grabbedHtml'"+additionalParameterSetUpdate+")\" value=\"Update\"/>\n"+"</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</form>\n" +
                    "</div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>\n" +
                    "<div id=\"grabbedHtml\"></div>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</body>\n" +
                    "</html>";
            output.print(htmlOutput);
        }
        catch(Exception e)
        {
            
        }
    }
    public void setBandanaManager(BandanaManager bandanaManager)
    {
        this.bandanaManager = bandanaManager;
    }
    public void setSocialnetworkingUserManager(SocialnetworkingUserManager socialnetworkingUserManager)
    {
        this.socialnetworkingUserManager = socialnetworkingUserManager;
    }
}
