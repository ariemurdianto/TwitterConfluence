package net.ariemurdianto.confluence.socialnetworking.settings.actions;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.user.actions.AbstractUserProfileAction;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.labels.Label;
import com.atlassian.confluence.security.Permission;
import com.atlassian.user.User;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

import bucket.core.actions.PaginationSupport;
import net.ariemurdianto.confluence.socialnetworking.settings.UserTwitterSetting;
import net.ariemurdianto.confluence.socialnetworking.manager.SocialnetworkingUserManager;

/**
 * Created by IntelliJ IDEA.
 * User: arie
 * Date: Aug 26, 2009
 * Time: 12:37:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserSettingsAction extends AbstractUserProfileAction
{
    private SocialnetworkingUserManager socialnetworkingUserManager;

    public void validate()
    {
        if (hasErrors())
        {
            return;
        }

        // this action does not make sense in the context of no logged in user.
        if (!isMyProfile())
        {
            addActionError(getText("cannot.view.another.users.labels"));
        }
    }

    public String execute() throws Exception
    {
        if(!usernameTwitter.equals(""))
        {
            UserTwitterSetting userSetting = new UserTwitterSetting();
            userSetting.setPassword(passwordTwitter);
            userSetting.setUsername(usernameTwitter);
            userSetting.setTwitterSetting(twitterSetting);
            userSetting.setNumberToDisplay(numberToDisplay);
            socialnetworkingUserManager.storeTwitterSetting(userSetting);
        }
        else
        {
            User user = AuthenticatedUserThreadLocal.getUser();
            UserTwitterSetting twitterSetting = socialnetworkingUserManager.getTwitterSetting(user.getName());
            if(twitterSetting == null)
                return super.execute();
            this.usernameTwitter = twitterSetting.getUsername();
            this.passwordTwitter = twitterSetting.getPassword();
            this.twitterSetting = twitterSetting.getTwitterSetting();
            this.numberToDisplay = twitterSetting.getNumberToDisplay();
        }

        return super.execute();
    }
    private String usernameTwitter="";
    public void setUsernameTwitter(String usernameTwitter)
    {
        this.usernameTwitter = usernameTwitter;
    }
    public String getUsernameTwitter()
    {
        return this.usernameTwitter;
    }
    private String passwordTwitter="";
    public void setPasswordTwitter(String passwordTwitter)
    {
        this.passwordTwitter = passwordTwitter;
    }
    public String getPasswordTwitter()
    {
        return this.passwordTwitter;
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
    private String numberToDisplay="";
    public void setNumberToDisplay(String numberToDisplay)
    {
        this.numberToDisplay = numberToDisplay;
    }
    public String getNumberToDisplay()
    {
        return this.numberToDisplay;
    }

    public void setSocialnetworkingUserManager(SocialnetworkingUserManager socialneworkingUserManager)
    {
        this.socialnetworkingUserManager = socialneworkingUserManager;
    }
}
