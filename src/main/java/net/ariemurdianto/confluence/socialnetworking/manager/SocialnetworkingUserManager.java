package net.ariemurdianto.confluence.socialnetworking.manager;

import net.ariemurdianto.confluence.socialnetworking.settings.UserTwitterSetting;
import net.ariemurdianto.confluence.socialnetworking.Constant;
import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.user.User;

/**
 * Created by IntelliJ IDEA.
 * User: arie
 * Date: Aug 26, 2009
 * Time: 12:05:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocialnetworkingUserManager {
    BandanaManager bandanaManager;
    public void storeTwitterSetting(UserTwitterSetting userSetting)
    {
        User user = AuthenticatedUserThreadLocal.getUser();
        bandanaManager.setValue(new ConfluenceBandanaContext(), Constant.KEY+user.getName(), userSetting);
    }
    public UserTwitterSetting getTwitterSetting(String username)
    {
        Object setting = bandanaManager.getValue(new ConfluenceBandanaContext(), Constant.KEY+username);
        if(setting != null)
            return (UserTwitterSetting)setting;
        return null;
    }
    public void setBandanaManager(BandanaManager bandanaManager)
    {
        this.bandanaManager = bandanaManager;
    }
}
