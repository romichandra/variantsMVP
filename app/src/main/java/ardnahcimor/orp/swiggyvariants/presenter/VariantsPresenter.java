package ardnahcimor.orp.swiggyvariants.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ardnahcimor.orp.swiggyvariants.View.VariantsView;
import ardnahcimor.orp.swiggyvariants.model.ApiResponse;
import ardnahcimor.orp.swiggyvariants.model.ExcludeList;
import ardnahcimor.orp.swiggyvariants.model.VariantGroup;
import ardnahcimor.orp.swiggyvariants.model.Variants;
import ardnahcimor.orp.swiggyvariants.model.Variation;
import ardnahcimor.orp.swiggyvariants.network.VariantsService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by romichandra on 02-08-2017.
 */

public class VariantsPresenter {

    public final VariantsService mVariantsService;
    public final VariantsView view;

    private CompositeSubscription subscriptions;

    public VariantsPresenter(VariantsService mVariantsService, VariantsView mVariantsView) {
        this.mVariantsService = mVariantsService;
        this.view = mVariantsView;
        this.subscriptions = new CompositeSubscription();
    }

    public void CallVariants() {

        if (view != null)
            view.showLoadingVariantsProgressBar();

        Subscription subscription = mVariantsService.getVariants(new VariantsService.GetVariantsCallback() {
            @Override
            public void onSuccess(ApiResponse variantsResponse) {
                if (view == null) return;
                view.hideLoadingVariantsProgressBar();
                view.getVariantsSuccessfully(variantsResponse);
            }

            @Override
            public void onError(Throwable t) {
                if (view == null) return;

                view.hideLoadingVariantsProgressBar();
                view.showVariantsNetworkError(t.getMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public List<VariantGroup> makeList(List<VariantGroup> variantGroupList){
        List<VariantGroup> newList = new ArrayList<>();
        for (VariantGroup group : variantGroupList){
            Variation mVariation;
            List<Variation> mList = new ArrayList<>();
            int i = 0;
            for (Variation variation : group.getVariations()){
                i++;
                mVariation = new Variation(false, variation.getInStock(), variation.getId(), variation.getPrice(), variation.getName());
                mList.add(mVariation);
            }
            newList.add(new VariantGroup(group.getName(), mList, group.getName(), group.getGroupId()));
        }
        return newList;
    }

    public HashMap<String, ArrayList<ArrayList<String>>> makeMap(List<List<ExcludeList>> eList){
        HashMap<String, ArrayList<ArrayList<String>>> mMap = new HashMap<>();

        for (int i = 0; i < eList.size(); i++){
            for (int j = 0; j < eList.get(i).size(); j++){
                ArrayList<ArrayList<String>> list;
                String key = "9a9b9c" + eList.get(i).get(j).getGroupId() + eList.get(i).get(j).getVariationId();
                for (int k = 0; k < eList.get(i).size(); k++){
                    if (k!=j){
                        if(mMap.containsKey(key)){
                            list = mMap.get(key);
                            ArrayList<String> sublist = new ArrayList<>();
                            sublist.add(eList.get(i).get(k).getGroupId());
                            sublist.add(eList.get(i).get(k).getVariationId());
                            list.add(sublist);
                        } else {
                            list = new ArrayList<ArrayList<String>>();
                            ArrayList<String> sublist = new ArrayList<>();
                            sublist.add(eList.get(i).get(k).getGroupId());
                            sublist.add(eList.get(i).get(k).getVariationId());
                            list.add(sublist);
                            mMap.put(key, list);
                        }
                    }
                }
            }
        }
        return mMap;
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
