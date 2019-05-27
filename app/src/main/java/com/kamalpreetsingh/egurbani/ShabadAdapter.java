package com.kamalpreetsingh.egurbani;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.appcompat.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.kamalpreetsingh.gurbanikhoj.Model.DatabaseAccess;
import com.example.kamalpreetsingh.gurbanikhoj.Model.FullShabad;
import com.example.kamalpreetsingh.gurbanikhoj.Model.ShabadLine;
import java.util.ArrayList;
import java.util.List;

public class ShabadAdapter extends RecyclerView.Adapter<ShabadAdapter.ViewHolder> implements Filterable {
  private String GURBANI_THICK_FONTS = "fonts/gurbaniwebthick.ttf";
  
  private String TAG = "ShabadAdapter";
  
  private ItemClickListener mClickListener;
  
  private Context mContext;
  
  private List<ShabadLine> mData;
  
  private LayoutInflater mInflater;
  
  ShabadAdapter(Context paramContext, FullShabad paramFullShabad) {
    this.mInflater = LayoutInflater.from(paramContext);
    this.mData = paramFullShabad.getShabadLines();
    this.mContext = paramContext;
  }
  
  public Filter getFilter() { return new Filter() {
        protected FilterResults performFiltering(CharSequence param1CharSequence) {
          param1CharSequence = param1CharSequence.toString();
          DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ShabadAdapter.this.mContext);
          databaseAccess.open();
          List list = databaseAccess.getFirstLetterAnywhere(param1CharSequence, "");
          FilterResults filterResults = new FilterResults();
          filterResults.values = list;
          return filterResults;
        }
        
        protected void publishResults(CharSequence param1CharSequence, FilterResults param1FilterResults) {
          ShabadAdapter.access$102(ShabadAdapter.this, (ArrayList)param1FilterResults.values);
          ShabadAdapter.this.notifyDataSetChanged();
        }
      }; }
  
  public int getItemCount() { return (this.mData != null) ? this.mData.size() : 0; }
  
  public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
    String str1 = ((ShabadLine)this.mData.get(paramInt)).getGurmukhi_shabad();
    String str2 = ((ShabadLine)this.mData.get(paramInt)).getSantSinghEnglish();
    ((ShabadLine)this.mData.get(paramInt)).getManmohamSinghEnglish();
    ((ShabadLine)this.mData.get(paramInt)).getManmohamSinghPunjabi();
    ((ShabadLine)this.mData.get(paramInt)).getSikhnetSpanish();
    String str3 = ((ShabadLine)this.mData.get(paramInt)).getFareedkotPunjabi();
    String str4 = ((ShabadLine)this.mData.get(paramInt)).getProfSahibSinghPunjabi();
    Typeface typeface = Typeface.createFromAsset(this.mContext.getAssets(), this.GURBANI_THICK_FONTS);
    paramViewHolder.gurmukhiTextView.setTypeface(typeface);
    paramViewHolder.profSahibSingh.setTypeface(typeface);
    paramViewHolder.fareedkot.setTypeface(typeface);
    paramViewHolder.gurmukhiTextView.setText(str1);
    paramViewHolder.profSahibSingh.setText(str4);
    paramViewHolder.santSinghEnglish.setText(str2);
    paramViewHolder.fareedkot.setText(str3);
    paramViewHolder.manmohanSinghEnglish.setVisibility(8);
    paramViewHolder.manmohanSinghPunjabi.setVisibility(8);
    paramViewHolder.sikhnetSpanish.setVisibility(8);
  }
  
  public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) { return new ViewHolder(this.mInflater.inflate(2131427392, paramViewGroup, false)); }
  
  void setClickListener(ItemClickListener paramItemClickListener) { this.mClickListener = paramItemClickListener; }
  
  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "ViewHolderr";
    
    TextView fareedkot;
    
    TextView gurmukhiTextView;
    
    TextView manmohanSinghEnglish;
    
    TextView manmohanSinghPunjabi;
    
    TextView profSahibSingh;
    
    TextView santSinghEnglish;
    
    TextView sikhnetSpanish;
    
    ViewHolder(View param1View) {
      super(param1View);
      this.gurmukhiTextView = (TextView)param1View.findViewById(2131230803);
      this.gurmukhiTextView.setTextColor(-16777216);
      this.santSinghEnglish = (TextView)param1View.findViewById(2131230859);
      this.santSinghEnglish.setTextColor(Color.rgb(11, 40, 157));
      this.manmohanSinghEnglish = (TextView)param1View.findViewById(2131230824);
      this.manmohanSinghPunjabi = (TextView)param1View.findViewById(2131230825);
      this.sikhnetSpanish = (TextView)param1View.findViewById(2131230888);
      this.fareedkot = (TextView)param1View.findViewById(2131230791);
      this.fareedkot.setTextColor(-16777216);
      this.profSahibSingh = (TextView)param1View.findViewById(2131230852);
      this.profSahibSingh.setTextColor(Color.rgb(61, 93, 166));
      param1View.setOnClickListener(this);
    }
    
    public void onClick(View param1View) {
      if (ShabadAdapter.this.mClickListener != null)
        ShabadAdapter.this.mClickListener.customOnItemClick(param1View, getAdapterPosition()); 
    }
  }
}


/* Location:              /Volumes/Jetdrive/Safari Downloads/app-debug_decoded_by_apktool/classes-dex2jar.jar!/com/example/kamalpreetsingh/gurbanikhoj/ShabadAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.2
 */