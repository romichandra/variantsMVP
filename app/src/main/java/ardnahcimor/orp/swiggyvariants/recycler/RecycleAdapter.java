package ardnahcimor.orp.swiggyvariants.recycler;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablecheckrecyclerview.ChildCheckController;
import com.thoughtbot.expandablecheckrecyclerview.listeners.OnCheckChildClickListener;
import com.thoughtbot.expandablecheckrecyclerview.listeners.OnChildCheckChangedListener;
import com.thoughtbot.expandablecheckrecyclerview.listeners.OnChildrenCheckStateChangedListener;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import ardnahcimor.orp.swiggyvariants.R;
import ardnahcimor.orp.swiggyvariants.model.ExcludeList;
import ardnahcimor.orp.swiggyvariants.model.VariantGroup;
import ardnahcimor.orp.swiggyvariants.model.Variation;

import static android.view.LayoutInflater.from;

/**
 * Created by romichandra on 03-08-2017.
 */

public class RecycleAdapter extends MultiTypeExpandableRecyclerViewAdapter<VariantGroupViewHolder, ChildViewHolder>
        implements OnChildCheckChangedListener, OnChildrenCheckStateChangedListener {

    private static final String CHECKED_STATE_MAP = "child_check_controller_checked_state_map";

    public static final int EXCLUDED_VIEW_TYPE = 3;
    public static final int ACTIVE_VIEW_TYPE = 4;

    private ChildCheckController childCheckController;
    private OnCheckChildClickListener childClickListener;

    HashMap<String, ArrayList<ArrayList<String>>> mExcludeMap;
    private ArrayList<ArrayList<String>> mExcludeArrayList = new ArrayList<>();

    public RecycleAdapter(List<? extends ExpandableGroup> groups, HashMap<String, ArrayList<ArrayList<String>>> mExcludeMap) {
        super(groups);
        childCheckController = new ChildCheckController(expandableList, this);
        this.mExcludeMap = mExcludeMap;
    }

    @Override
    public boolean onGroupClick(int flatPos) {
        return super.onGroupClick(flatPos);
    }

    @Override
    public VariantGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext())
                .inflate(R.layout.list_item_variant_group, parent, false);
        return new VariantGroupViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EXCLUDED_VIEW_TYPE:
                View view_excluded = from(parent.getContext()).inflate(R.layout.list_item_child_excluded_variation, parent, false);
                return new ExcludedVariationViewHolder(view_excluded);
            case ACTIVE_VIEW_TYPE:
                View view_active = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_active_variation, parent, false);
                VariationViewHolder holder = new VariationViewHolder(view_active);
                holder.setOnChildCheckedListener(this);
                return holder;
            default:
                throw new IllegalArgumentException(viewType + " is an Invalid viewType");
        }    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        int viewType = getItemViewType(flatPosition);
        Variation variation = (Variation) group.getItems().get(childIndex);
        switch (viewType) {
            case EXCLUDED_VIEW_TYPE:
                ((ExcludedVariationViewHolder) holder).setVariantName(variation.getName());
                ((ExcludedVariationViewHolder) holder).setVariantPrice(Integer.toString( variation.getPrice()));
                ((ExcludedVariationViewHolder) holder).setVariantStock(Integer.toString( variation.getInStock()));
                break;
            case ACTIVE_VIEW_TYPE:
                ExpandableListPosition listPosition = expandableList.getUnflattenedPosition(flatPosition);
                ((VariationViewHolder) holder)
                        .onBindViewHolder(flatPosition, childCheckController.isChildChecked(listPosition));
                ((VariationViewHolder) holder).setVariantName(variation.getName());
                ((VariationViewHolder) holder).setVariantPrice(Integer.toString( variation.getPrice()));
                ((VariationViewHolder) holder).setVariantStock(Integer.toString( variation.getInStock()));
        }
    }

    @Override
    public void onBindGroupViewHolder(VariantGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupTitle(group);
    }

    @Override
    public void onChildCheckChanged(View view, boolean checked, int flatPos) {
        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(flatPos);
        childCheckController.onChildCheckChanged(checked, listPos);
        if (childClickListener != null) {
            childClickListener.onCheckChildCLick(view, checked,
                    (CheckedExpandableGroup) expandableList.getExpandableGroup(listPos), listPos.childPos);
        }

        mExcludeArrayList = new ArrayList<>();
        for (Integer n : childCheckController.getCheckedPositions()){

            ExpandableListPosition listPosChecked = expandableList.getUnflattenedPosition(n);

            String checkedKey = "9a9b9c" + ((VariantGroup) expandableList.getExpandableGroup(listPosChecked)).getGroupId()
                    + ((VariantGroup) expandableList.getExpandableGroup(listPosChecked)).getVariations().get(listPosChecked.childPos).getId();

            if (mExcludeMap.containsKey(checkedKey)){
                mExcludeArrayList = mExcludeMap.get(checkedKey);
            }
        }

        for (int m = 0; m < getGroups().size(); m++){
            ExpandableGroup g = getGroups().get(m);
            for (int n = 0; n < ((VariantGroup) g).getVariations().size(); n++){
                Variation v = ((Variation) ((VariantGroup) g).getVariations().get(n));
                ArrayList<String> checkList = new ArrayList<>();
                checkList.add(((VariantGroup) g).getGroupId());
                checkList.add(v.getId());
                if (mExcludeArrayList.contains(checkList)){
                    v.setExcluded(true);
                }
                else{
                    v.setExcluded(false);
                }
            }
        }

        for (int i = 0; i < getGroups().size(); i++) {
            ExpandableGroup group = getGroups().get(i);
            if (isGroupExpanded(group)) {
                notifyItemRangeChanged(expandableList.getFlattenedFirstChildIndex(i), group.getItemCount());
            }
        }
    }

    @Override
    public void updateChildrenCheckState(int firstChildFlattenedIndex, int numChildren) {
        notifyItemRangeChanged(firstChildFlattenedIndex, numChildren);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(CHECKED_STATE_MAP, new ArrayList(expandableList.groups));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null || !savedInstanceState.containsKey(CHECKED_STATE_MAP)) {
            return;
        }
        expandableList.groups = savedInstanceState.getParcelableArrayList(CHECKED_STATE_MAP);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void clearChoices() {
        childCheckController.clearCheckStates();

        for (int i = 0; i < getGroups().size(); i++) {
            ExpandableGroup group = getGroups().get(i);
            if (isGroupExpanded(group)) {
                notifyItemRangeChanged(expandableList.getFlattenedFirstChildIndex(i), group.getItemCount());
            }
        }
    }

    @Override
    public boolean isChild(int viewType) {
        return viewType == ACTIVE_VIEW_TYPE || viewType == EXCLUDED_VIEW_TYPE;
    }

    @Override
    public int getChildViewType(int position, ExpandableGroup group, int childIndex) {
        try{
            if (((Variation) (group).getItems().get(childIndex)).isExcluded()) {
                return EXCLUDED_VIEW_TYPE;
            } else {
                return ACTIVE_VIEW_TYPE;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ACTIVE_VIEW_TYPE;
        }
    }
}
