package ardnahcimor.orp.swiggyvariants.recycler;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import ardnahcimor.orp.swiggyvariants.R;
import ardnahcimor.orp.swiggyvariants.model.VariantGroup;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by romichandra on 03-08-2017.
 */

public class VariantGroupViewHolder extends GroupViewHolder {

    private TextView variantName;
    private ImageView arrow;
    private ImageView icon;

    public VariantGroupViewHolder(View itemView) {
        super(itemView);
        variantName = (TextView) itemView.findViewById(R.id.list_item_group_name);
        arrow = (ImageView) itemView.findViewById(R.id.list_item_group_arrow);
        icon = (ImageView) itemView.findViewById(R.id.list_item_group_icon);
    }

    public void setGroupTitle(ExpandableGroup variant) {
        if (variant instanceof VariantGroup) {
            variantName.setText(((VariantGroup) variant).getName());
        }
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
