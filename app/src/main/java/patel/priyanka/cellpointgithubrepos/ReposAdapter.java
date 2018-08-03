package patel.priyanka.cellpointgithubrepos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import patel.priyanka.cellpointgithubrepos.models.ReposModel;

public class ReposAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ReposModel> reposHeaderList;
    private HashMap<ReposModel, List<ReposModel>> reposChildList;

    public ReposAdapter(Context context, ArrayList<ReposModel> reposHeaderList, HashMap<ReposModel, List<ReposModel>> reposChildList) {
        this.context = context;
        this.reposHeaderList = reposHeaderList;
        this.reposChildList = reposChildList;
    }

    @Override
    public int getGroupCount() {
        if (!reposHeaderList.isEmpty()) {
            return this.reposHeaderList.size();
        } else {
            Toast.makeText(context.getApplicationContext(), R.string.repos_unavailable, Toast.LENGTH_LONG).show();
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (!reposChildList.isEmpty()) {
            return this.reposChildList.get(this.reposHeaderList.get(groupPosition)).size();
        } else {
            Toast.makeText(context.getApplicationContext(), R.string.repos_unavailable, Toast.LENGTH_LONG).show();
            return 0;

        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.reposHeaderList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.reposChildList.get(this.reposHeaderList.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_group_items, null);
        }

        TextView textViewHeaderLanguage = convertView.findViewById(R.id.text_view_list_header);
        for (int i =0; i<reposHeaderList.size(); i++) {
            String title = reposHeaderList.get(i).getLanguage();
            textViewHeaderLanguage.setText(title);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childRepoName = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_child_item, null);
        }

        TextView textViewChildRepoName = convertView.findViewById(R.id.text_view_group_child);
        ReposModel reposModel = new ReposModel();
        String childRepo = reposModel.getName();
        textViewChildRepoName.setText(childRepo);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
