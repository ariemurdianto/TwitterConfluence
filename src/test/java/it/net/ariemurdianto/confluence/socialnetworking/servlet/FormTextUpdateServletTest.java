package it.net.ariemurdianto.confluence.socialnetworking.servlet;

import com.atlassian.confluence.plugin.functest.AbstractConfluencePluginWebTestCase;
import com.atlassian.confluence.plugin.functest.ConfluenceWebTester;
import it.net.ariemurdianto.confluence.socialnetworking.TestConstant;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.junit.Test;
import junit.framework.Assert;
import net.ariemurdianto.confluence.socialnetworking.Constant;

/**
 * Created by IntelliJ IDEA.
 * User: arie
 * Date: 05/09/2009
 * Time: 4:34:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormTextUpdateServletTest extends AbstractConfluencePluginWebTestCase {
    protected ConfluenceWebTester confluenceWebTester;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        confluenceWebTester = this.getConfluenceWebTester();
    }
    @Test
    public void testFormTextUpdateServletBeforeLogin()
    {
        String baseUrl = confluenceWebTester.getBaseUrl();
        HttpClient httpClient = new HttpClient(new HttpClientParams());
        GetMethod getMethod = new GetMethod(baseUrl + TestConstant.FORM_SERVLET_URL);
        String result = "";
        try
        {
            httpClient.executeMethod(getMethod);
            result = getMethod.getResponseBodyAsString();            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Assert.assertEquals(result, Constant.MESSAGE_NOT_LOGIN);
    }

    @Test
    public void testFormTextUpdateServletAfterLogin()
    {
        confluenceWebTester.login(TestConstant.USERNAME, TestConstant.PASSWORD);
        String baseUrl = confluenceWebTester.getBaseUrl();
        HttpClient httpClient = new HttpClient(new HttpClientParams());
        GetMethod getMethod = new GetMethod(TestConstant.FORM_SERVLET_URL);
        String result = "";
        try
        {
            httpClient.executeMethod(getMethod);
            result = getMethod.getResponseBodyAsString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(result);
        Assert.assertNotSame(result, Constant.MESSAGE_NOT_LOGIN);
    }
}
