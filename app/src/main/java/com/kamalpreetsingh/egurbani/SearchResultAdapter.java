package com.kamalpreetsingh.egurbani;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.kamalpreetsingh.gurbanikhoj.Model.DatabaseAccess;
import com.example.kamalpreetsingh.gurbanikhoj.Model.QueryResult;
import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> implements Filterable {
  private String GURBANI_THICK_FONTS = "fonts/gurbaniwebthick.ttf";
  
  private ItemClickListener mClickListener;
  
  private Context mContext;
  
  private List<QueryResult> mData;
  
  private LayoutInflater mInflater;
  
  SearchResultAdapter(Context paramContext, List<QueryResult> paramList) {
    this.mInflater = LayoutInflater.from(paramContext);
    this.mData = paramList;
    this.mContext = paramContext;
  }
  
  public Filter getFilter() { return new Filter() {
        protected FilterResults performFiltering(CharSequence param1CharSequence) {
          param1CharSequence = param1CharSequence.toString();
          DatabaseAccess databaseAccess = DatabaseAccess.getInstance(SearchResultAdapter.this.mContext);
          databaseAccess.open();
          List list = databaseAccess.getFirstLetterAnywhere(param1CharSequence, "");
          FilterResults filterResults = new FilterResults();
          filterResults.values = list;
          return filterResults;
        }
        
        protected void publishResults(CharSequence param1CharSequence, FilterResults param1FilterResults) {
          SearchResultAdapter.access$102(SearchResultAdapter.this, (ArrayList)param1FilterResults.values);
          SearchResultAdapter.this.notifyDataSetChanged();
        }
      }; }
  
  public QueryResult getItem(int paramInt) { return (QueryResult)this.mData.get(paramInt); }
  
  public int getItemCount() { return (this.mData != null) ? this.mData.size() : 0; }
  
  public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
    String str = ((QueryResult)this.mData.get(paramInt)).getGurmukhi();
    paramInt = ((QueryResult)this.mData.get(paramInt)).getSourcePage();
    Typeface typeface = Typeface.createFromAsset(this.mContext.getAssets(), this.GURBANI_THICK_FONTS);
    paramViewHolder.gurmukhiTextView.setTypeface(typeface);
    paramViewHolder.gurmukhiTextView.setText(str);
    paramViewHolder.sourcePage.setTypeface(typeface);
    paramViewHolder.sourcePage.setText(String.valueOf(paramInt));
  }
  
  public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) { return new ViewHolder(this.mInflater.inflate(2131427391, paramViewGroup, false)); }
  
  void setClickListener(ItemClickListener paramItemClickListener) { this.mClickListener = paramItemClickListener; }
  
  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView gurmukhiTextView;
    
    TextView sourcePage;
    
    ViewHolder(View param1View) {
      super(param1View);
      this.gurmukhiTextView = (TextView)param1View.findViewById(2131230804);
      this.sourcePage = (TextView)param1View.findViewById(2131230894);
      param1View.setOnClickListener(this);
    }
    
    public void onClick(View param1View) {
      if (SearchResultAdapter.this.mClickListener != null)
        SearchResultAdapter.this.mClickListener.customOnItemClick(param1View, getAdapterPosition()); 
    }
  }
}


/* Location:              /Volumes/Jetdrive/Safari Downloads/app-debug_decoded_by_apktool/classes-dex2jar.jar!/com/example/kamalpreetsingh/gurbanikhoj/SearchResultAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.2
 */