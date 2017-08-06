package ardnahcimor.orp.swiggyvariants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

import ardnahcimor.orp.swiggyvariants.dependencies.DaggerDependencies;
import ardnahcimor.orp.swiggyvariants.dependencies.Dependencies;
import ardnahcimor.orp.swiggyvariants.network.VariantsNetworkModule;

public class VariantsBaseActivity extends AppCompatActivity {
    public Dependencies mDependencies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        mDependencies = DaggerDependencies.builder().variantsNetworkModule(new VariantsNetworkModule(cacheFile)).build();
    }

    public Dependencies getDependencies() {
        return mDependencies;
    }
}
