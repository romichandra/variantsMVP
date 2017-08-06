package ardnahcimor.orp.swiggyvariants.recycler;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import ardnahcimor.orp.swiggyvariants.R;

/**
 * Created by romichandra on 03-08-2017.
 */

public class ExcludedVariationViewHolder extends ChildViewHolder{
    private TextView childTextView;
    private TextView textStock, textPrice;

    public ExcludedVariationViewHolder(View itemView) {
        super(itemView);
        childTextView = (TextView) itemView.findViewById(R.id.list_item_excluded_variation_name);
        textPrice = (TextView) itemView.findViewById(R.id.list_item_excluded_variation_price);
        textStock = (TextView) itemView.findViewById(R.id.list_item_excluded_variation_stock);
    }

    public void setVariantName(String name) {
        childTextView.setText(name + " (Unavailable with this combo)");
    }

    public void setVariantPrice(String price) {
        textPrice.setText("Price : " + price);
    }

    public void setVariantStock(String stock) {
        textStock.setText("Stock : " + stock);
    }
}
