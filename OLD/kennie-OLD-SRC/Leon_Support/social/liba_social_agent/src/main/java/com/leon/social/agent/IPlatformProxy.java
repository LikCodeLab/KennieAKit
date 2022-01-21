package com.leon.social.agent;


import com.leon.social.core.Configuration;
import com.leon.social.core.platform.IPlatform;

import java.util.List;

/**
 * <b>Project:</b> liba_social_agent<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public interface IPlatformProxy {

    List<Configuration> onCreateConfiguration();

    IPlatform onCreatePlatform(Configuration configuration);

}