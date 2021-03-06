/*
 * Copyright 2018 Comvai, s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.identity.AccountProfile;
import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.ctoolkit.wicket.standard.util.WicketUtil;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * TODO look at {@link AuthenticatedWebSession#isSignedIn()} and try to employ.
 *
 * @author <a href="mailto:jozef.pohorelec@ctoolkit.org">Jozef Pohorelec</a>
 */
public class FrontendSession
        extends AuthenticatedWebSession
{
    static final String AUTH_USER_ATTRIBUTE = "__session_auth_user_attribute";

    static final Locale DEFAULT_SESSION_LOCALE = Locale.ENGLISH;

    private static final long serialVersionUID = -5908999362458425557L;

    public FrontendSession( Request request )
    {
        super( request );

        setLocale( DEFAULT_SESSION_LOCALE );
    }

    public static FrontendSession get()
    {
        return ( FrontendSession ) Session.get();
    }

    @Override
    public boolean authenticate( String s, String s1 )
    {
        return false;
    }

    @Override
    public Roles getRoles()
    {
        Roles roles = new Roles();

        if ( isLoggedIn() )
        {
            roles.add( Role.USER );
        }

        return roles;
    }

    public boolean isLoggedIn()
    {
        return getLoggedInUser() != null;
    }

    public boolean isLoggedIn( AccountProfile account )
    {
        AccountProfile loggedInUser = getLoggedInUser();
        return loggedInUser != null && loggedInUser.equals( account );
    }

    public AccountProfile getLoggedInUser()
    {
        HttpSession session = WicketUtil.getHttpServletRequest().getSession();
        return ( AccountProfile ) session.getAttribute( AUTH_USER_ATTRIBUTE );
    }

    public void updateLoggedInUser( AccountProfile account )
    {
        HttpSession session = WicketUtil.getHttpServletRequest().getSession();
        session.setAttribute( AUTH_USER_ATTRIBUTE, account );
    }
}