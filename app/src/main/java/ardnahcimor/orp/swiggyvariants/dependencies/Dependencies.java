package ardnahcimor.orp.swiggyvariants.dependencies;

import javax.inject.Singleton;

import ardnahcimor.orp.swiggyvariants.VariantsActivity;
import ardnahcimor.orp.swiggyvariants.network.VariantsNetworkModule;
import dagger.Component;

/**
 * Created by romichandra on 02-08-2017.
 */

@Singleton
@Component(modules={VariantsNetworkModule.class,})
public interface Dependencies {
    public void inject(VariantsActivity variantsActivity);
}
