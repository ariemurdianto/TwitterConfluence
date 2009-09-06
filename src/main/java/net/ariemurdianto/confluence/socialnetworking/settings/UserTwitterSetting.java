package net.ariemurdianto.confluence.socialnetworking.settings;

/**
 * Created by IntelliJ IDEA.
 * User: arie
 * Date: Aug 27, 2009
 * Time: 1:17:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTwitterSetting {
    
//    private String accountOwner;
//    public void setAccountOwner(String accountOwner)
//    {
//        this.accountOwner = accountOwner;
//    }
//    public String getAccountOwner()
//    {
//        return this.accountOwner;
//    }
    private String username;
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }
    private String password;
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }
    private String twitterSetting;
    public void setTwitterSetting(String twitterSetting)
    {
        this.twitterSetting = twitterSetting;
    }
    public String getTwitterSetting()
    {
        return this.twitterSetting;
    }
    private String numberToDisplay="5";
    public void setNumberToDisplay(String numberToDisplay)
    {
        this.numberToDisplay = numberToDisplay;
    }
    public String getNumberToDisplay()
    {
        return this.numberToDisplay;
    }
}
