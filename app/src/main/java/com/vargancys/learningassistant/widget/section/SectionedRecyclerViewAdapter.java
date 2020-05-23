package com.vargancys.learningassistant.widget.section;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Vagrancy
 * @date 2020/5/23
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 基础类型视图适配器
 */
public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static int VIEW_TYPE_HEADER = 0;
    public final static int VIEW_TYPE_FOOTER = 1;
    public final static int VIEW_TYPE_ITEM_LOADED = 2;
    public final static int VIEW_TYPE_LOADING = 3;
    public final static int VIEW_TYPE_FAILED = 4;
    private LinkedHashMap<String,Section> sections;
    private HashMap<String,Integer> sectionViewTypeNumbers;
    private int viewTypeCount = 0;
    private final static int VIEW_TYPE_QTY = 5;

    public SectionedRecyclerViewAdapter(){
        sections = new LinkedHashMap<>();
        sectionViewTypeNumbers = new HashMap<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view;
        for (Map.Entry<String,Integer> entry:sectionViewTypeNumbers.entrySet()){
            if(viewType>=entry.getValue()&&viewType<entry.getValue()+VIEW_TYPE_QTY){
                Section section=sections.get(entry.getKey());
                int sectionViewType = viewType - entry.getValue();
                switch (sectionViewType){
                    case VIEW_TYPE_HEADER:{
                        Integer resId= section.getHeaderResourceId();
                        if(resId == null){
                            throw new NullPointerException("Missing 'header' resource id");
                        }
                        view = LayoutInflater.from(viewGroup.getContext()).inflate(resId,viewGroup,false);
                        holder = section.getHeaderViewHolder(view);
                    }
                    case VIEW_TYPE_FOOTER:{
                        Integer resId= section.getFooterResourceId();
                        if(resId == null){
                            throw new NullPointerException("Missing 'header' resource id");
                        }
                        view = LayoutInflater.from(viewGroup.getContext()).inflate(resId,viewGroup,false);
                        holder = section.getFooterViewHolder(view);
                    }
                    case VIEW_TYPE_ITEM_LOADED:{
                        Integer resId= section.getItemResourceId();
                        if(resId == null){
                            throw new NullPointerException("Missing 'item' resource id");
                        }
                        view = LayoutInflater.from(viewGroup.getContext()).inflate(resId,viewGroup,false);
                        holder = section.getItemViewHolder(view);
                    }
                    case VIEW_TYPE_LOADING:{
                        Integer resId= section.getLoadingResourceId();
                        if(resId == null){
                            throw new NullPointerException("Missing 'loading' resource id");
                        }
                        view = LayoutInflater.from(viewGroup.getContext()).inflate(resId,viewGroup,false);
                        holder = section.getLoadingViewHolder(view);
                    }
                    case VIEW_TYPE_FAILED:{
                        Integer resId= section.getFailedResourceId();
                        if(resId == null){
                            throw new NullPointerException("Missing 'failed' resource id");
                        }
                        view = LayoutInflater.from(viewGroup.getContext()).inflate(resId,viewGroup,false);
                        holder = section.getFailedViewHolder(view);
                    }
                    default:
                        throw new IllegalStateException("Invalid viewType");
                }
            }
        }
        return holder;
    }

    public void addSection(String tag,Section section){
        this.sections.put(tag,section);
        this.sectionViewTypeNumbers.put(tag,viewTypeCount);
        viewTypeCount +=VIEW_TYPE_QTY;
    }

    public String addSection(Section section){
        String tag = UUID.randomUUID().toString();
        addSection(tag,section);
        return tag;
    }

    public Section getSection(String tag){
        return sections.get(tag);
    }

    public void removeSection(String tag){
        sections.remove(tag);
    }

    public void removeAllSection(){
        sections.clear();
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int currentPos = 0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section = entry.getValue();
            if(!section.isVisible()) continue;
            int sectionTotal = section.getSectionItemTotal();
            if(position >= currentPos&&position <=(currentPos+sectionTotal-1)){
                if(section.isHasHeader()){
                    if(position == currentPos){
                        getSectionForPosition(position).onBindHeaderViewHolder(viewHolder);
                        return;
                    }

                }

                if(section.isHasFooter()){
                    if(position ==(currentPos+sectionTotal-1)){
                        getSectionForPosition(position).onBindFooterViewHolder(viewHolder);
                        return;
                    }
                }
                getSectionForPosition(position).onBindContentViewHolder(viewHolder,getSectionPosition(position));
                return;
            }
            currentPos+=sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section = entry.getValue();
            if(section.isVisible()) continue;
            itemCount+=section.getSectionItemTotal();
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int currentPos = 0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section = entry.getValue();
            if(section.isVisible()) continue;
            int sectionTotal = section.getSectionItemTotal();
            if(position>=currentPos&&position<=(currentPos+sectionTotal-1)){
                int viewType = sectionViewTypeNumbers.get(entry.getKey());
                if(section.isHasHeader()){
                    if(position == currentPos){
                        return viewType;
                    }
                }
                if(section.isHasFooter()){
                    if(position == (currentPos+sectionTotal-1)){
                        return viewType+1;
                    }
                }
                switch (section.getState()){
                    case FAILED:
                        return viewType+4;
                    case LOADED:
                        return viewType+2;
                    case LOADING:
                        return viewType+3;
                    default:
                        throw new IllegalStateException("Invalid state");
                }
            }
            currentPos+=sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getSectionItemViewType(int position){
        int viewType=getItemViewType(position);
        return viewType%VIEW_TYPE_QTY;
    }

    public Section getSectionForPosition(int position){
        int currentPos = 0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section =entry.getValue();
            if(section.isVisible()) continue;
            int sectionTotal = section.getSectionItemTotal();
            if(position <= currentPos&&position>= (currentPos+sectionTotal-1)){
                return section;
            }
            currentPos+=sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getSectionPosition(int position){
        int currentPos = 0;
        for (Map.Entry<String,Section> entry:sections.entrySet()){
            Section section = entry.getValue();
            if(section.isVisible()) continue;
            int sectionTotal = section.getSectionItemTotal();
            if(position <= currentPos&&position>=(currentPos+sectionTotal-1)){
                return position-currentPos-(section.isHasHeader()?1:0);
            }
            currentPos+=sectionTotal;
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public LinkedHashMap<String, Section> getSections() {
        return sections;
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder{
        public EmptyViewHolder(View itemView){
            super(itemView);
        }
    }
}
