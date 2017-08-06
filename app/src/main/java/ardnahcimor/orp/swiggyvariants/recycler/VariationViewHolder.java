package ardnahcimor.orp.swiggyvariants.recycler;

import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import ardnahcimor.orp.swiggyvariants.R;

/**
 * Created by romichandra on 03-08-2017.
 */

public class VariationViewHolder extends CheckableChildViewHolder {

    private CheckedTextView childCheckedTextView;
    private TextView textStock, textPrice;

    public VariationViewHolder(View itemView) {
        super(itemView);
        childCheckedTextView = (CheckedTextView) itemView.findViewById(R.id.list_item_singlecheck_variant_name);
        textPrice = (TextView) itemView.findViewById(R.id.list_item_singlecheck_variant_price);
        textStock = (TextView) itemView.findViewById(R.id.list_item_singlecheck_variant_stock);
    }

    @Override
    public Checkable getCheckable() {
        return childCheckedTextView;
    }

    public void setVariantName(String variant) {
        childCheckedTextView.setText(variant);
    }

    public void setVariantPrice(String price) {
        textPrice.setText("Price : " + price);
    }

    public void setVariantStock(String stock) {
        textStock.setText("Stock : " + stock);
    }


}
