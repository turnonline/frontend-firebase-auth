package biz.turnonline.ecosystem.origin.frontend.myaccount.page;

import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.ctoolkit.wicket.standard.identity.FirebaseConfig;
import org.ctoolkit.wicket.standard.identity.behavior.FirebaseAppInit;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;
import org.ctoolkit.wicket.turnonline.markup.html.page.DecoratedPage;

import javax.inject.Inject;

/**
 * The page dedicated for advanced account settings.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@AuthorizeInstantiation( Role.STANDARD )
public class AccountSettings<T>
        extends DecoratedPage<T>
{
    private static final long serialVersionUID = 8259598575896609246L;

    private I18NResourceModel titleModel = new I18NResourceModel( "title.my-account" );

    @Inject
    private FirebaseConfig firebaseConfig;

    public AccountSettings()
    {
        add( new FirebaseAppInit( firebaseConfig ) );
    }

    @Override
    public IModel<String> getPageTitle()
    {
        return titleModel;
    }
}
