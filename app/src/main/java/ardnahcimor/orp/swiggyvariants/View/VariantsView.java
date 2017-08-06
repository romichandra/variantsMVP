package ardnahcimor.orp.swiggyvariants.View;

import ardnahcimor.orp.swiggyvariants.model.ApiResponse;
import ardnahcimor.orp.swiggyvariants.model.Variants;

/**
 * Created by romichandra on 02-08-2017.
 */


public interface VariantsView {

    void showLoadingVariantsProgressBar();

    void hideLoadingVariantsProgressBar();

    void getVariantsSuccessfully(ApiResponse variants);

    void showVariantsNetworkError(String mError);

}
