package it.net.ariemurdianto.confluence.socialnetworking.servlet;

import com.atlassian.confluence.plugin.functest.AbstractConfluencePluginWebTestCase;
import com.atlassian.confluence.plugin.functest.ConfluenceWebTester;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import it.net.ariemurdianto.confluence.socialnetworking.TestConstant;
import junit.framework.Assert;

/**
 * Created by IntelliJ IDEA.
 * User: arie
 * Date: 05/09/2009
 * Time: 4:59:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserStatusUpdateServletTest extends AbstractConfluencePluginWebTestCase {
    private ConfluenceWebTester confluenceWebTester;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        confluenceWebTester = this.getConfluenceWebTester();
    }
    public void testUserStatusUpdateServlet()
    {
        String baseUrl = confluenceWebTester.getBaseUrl();
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(baseUrl+ TestConstant.FORM_SERVLET_URL);
        String result = "";
        try
        {
            client.executeMethod(getMethod);
            result = getMethod.getResponseBodyAsString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Assert.assertEquals("{\"successFlag\":\"success\"}", result);
    }
}
