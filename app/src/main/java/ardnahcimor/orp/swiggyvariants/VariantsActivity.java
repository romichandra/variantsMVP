package ardnahcimor.orp.swiggyvariants;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import ardnahcimor.orp.swiggyvariants.View.VariantsView;
import ardnahcimor.orp.swiggyvariants.model.ApiResponse;
import ardnahcimor.orp.swiggyvariants.model.ExcludeList;
import ardnahcimor.orp.swiggyvariants.model.VariantGroup;
import ardnahcimor.orp.swiggyvariants.model.Variation;
import ardnahcimor.orp.swiggyvariants.network.VariantsService;
import ardnahcimor.orp.swiggyvariants.presenter.VariantsPresenter;
import ardnahcimor.orp.swiggyvariants.recycler.RecycleAdapter;


public class VariantsActivity extends VariantsBaseActivity implements VariantsView {

    @Inject
    public VariantsService variantsService;

    private VariantsPresenter mPresenter;

    private RecyclerView recyclerView;
    ProgressBar progressBar;
    Button btnClear;
    private RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDependencies().inject(this);

        initViews();
        mPresenter = new VariantsPresenter(variantsService, this);
        mPresenter.CallVariants();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoadingVariantsProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingVariantsProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showVariantsNetworkError(String mError) {
        Log.d("VariantsActivity Error ", mError);
    }

    @Override
    public void getVariantsSuccessfully(ApiResponse variants) {
        try{
            adapter = new RecycleAdapter( mPresenter.makeList(variants.getVariants().getVariantGroups()) , mPresenter.makeMap(variants.getVariants().getExcludeList()));
            recyclerView.setAdapter(adapter);
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.clearChoices();
                }
            });
        }catch (Exception e){}
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter!=null)
            adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (adapter!=null)
            adapter.onRestoreInstanceState(savedInstanceState);
    }

    private void initViews() {
        setContentView(R.layout.activity_variants);

        btnClear = (Button) findViewById(R.id.clear_button);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
