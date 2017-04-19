package com.example.navi;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

class ExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Elemento> mainElements;
    private HashMap<Integer, List<String>> childElements;
    private LayoutInflater vi;

    public ExpandableAdapter(Context context, List<Elemento> mainElements, HashMap<Integer, List<String>> childElements) {
        this.context = context;
        this.mainElements = mainElements;
        this.childElements = childElements;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return this.mainElements.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(this.childElements.get(groupPosition) == null)
            return 0;
        return this.childElements.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mainElements.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.childElements.get(groupPosition).get(childPosition);
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
        View v = convertView;

        final Elemento i = mainElements.get(groupPosition);
        if (i != null) {
            if(i.isGroupSection()){
                final TitoloGruppo si = (TitoloGruppo)i;
                v = vi.inflate(android.R.layout.simple_list_item_1, null);
                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);
                final TextView sectionView = (TextView) v.findViewById(android.R.id.text1);
                sectionView.setTextColor(Color.parseColor("#FFC800"));
                sectionView.setText(si.getTitle());
            }else if(i.isAction()){
                Azione ei = (Azione)i;
                v = vi.inflate(android.R.layout.simple_list_item_2, null);
               
                final TextView title = (TextView)v.findViewById(android.R.id.text1);
                /*if(groupPosition==2){
                	 v = vi.inflate(android.R.layout.simple_list_item_2, null);
                Drawable img =v.getResources().getDrawable(R.id.expanded_menu);
                title.setCompoundDrawablesWithIntrinsicBounds( 
                img,// left 
                null,//top 
                null,// right 
                null);
                }*/
                final TextView subtitle = (TextView)v.findViewById(android.R.id.text2);

                if (title != null)
                    title.setText(ei.title);
                /*if(subtitle != null)
                    subtitle.setText("count: " + getChildrenCount(groupPosition));*/
            }
        }
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(android.R.id.text1);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}   
 class Azione implements Elemento {
    public final String title;
   

    public Azione(String title) {
        this.title = title;
        
    }

    public String getTitle() {
        return this.title;
    }

    

    public boolean isGroupSection() {
        return false;
    }

    public boolean isAction() {
        return true;
    }
}
 class TitoloGruppo implements Elemento {

	    private final String titolo;

	    public TitoloGruppo(String titolo) {
	        this.titolo = titolo;
	    }

	    public String getTitle(){
	        return titolo;
	    }

	    public boolean isGroupSection() {
	        return true;
	    }

	    public boolean isAction() {
	        return false;
	    }
	}
 interface Elemento {
	    public boolean isGroupSection();
	    public boolean isAction();
	}

    
   