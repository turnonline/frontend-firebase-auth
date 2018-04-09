package biz.turnonline.ecosystem.origin.frontend;

import com.google.appengine.api.utils.SystemProperty;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class GuiceServletConfig
        extends GuiceServletContextListener
{
    private Injector getTestInjector()
    {
        return Guice.createInjector( new FrontendModule(),
                new FrontendServletModule() );
    }

    private Injector getProductionInjector()
    {
        return Guice.createInjector( Stage.PRODUCTION,
                new FrontendModule(),
                new FrontendServletModule() );
    }

    @Override
    protected final Injector getInjector()
    {
        if ( SystemProperty.environment.value() == SystemProperty.Environment.Value.Production )
        {
            // The app is running on App Engine...
            return getProductionInjector();
        }
        else
        {
            return getTestInjector();
        }
    }
}