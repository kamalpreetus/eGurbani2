package com.kamalpreetsingh.egurbani;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.kamalpreetsingh.gurbanikhoj.Model.DatabaseAccess;
import com.example.kamalpreetsingh.gurbanikhoj.Model.FullShabad;

public class ShabadFragment extends Fragment {
  static final String SHABAD_ID_RECIVER_KEY = "shabadID";
  
  static final String TAG = "ShabadFragment";
  
  ShabadAdapter mAdapter;
  
  RecyclerView recyclerView;
  
  private String shabadID;
  
  private void setShabadID() {}
  
  private void setShabadId() {
    Bundle bundle = getArguments();
    if (bundle != null)
      this.shabadID = bundle.getString("shabadID"); 
  }
  
  public void onAttach(Context paramContext) { super.onAttach(paramContext); }
  
  public void onCreate(Bundle paramBundle) { super.onCreate(paramBundle); }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) { return paramLayoutInflater.inflate(2131427373, paramViewGroup, false); }
  
  public void onDetach() { super.onDetach(); }
  
  public void onStart() {
    super.onStart();
    Bundle bundle = getArguments();
    if (bundle != null) {
      String str;
      if (bundle.getString("shabadID") == null) {
        str = "nulls";
      } else {
        str = "not null";
      } 
      this.shabadID = str;
    } 
  }
  
  public void onViewCreated(@NonNull View paramView, @Nullable Bundle paramBundle) {
    super.onViewCreated(paramView, paramBundle);
    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
    databaseAccess.open();
    setShabadId();
    FullShabad fullShabad = databaseAccess.getShabadByID(this.shabadID);
    this.recyclerView = (RecyclerView)paramView.findViewById(2131230882);
    this.mAdapter = new ShabadAdapter(getActivity(), fullShabad);
    this.recyclerView.setAdapter(this.mAdapter);
    this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
  }
}


/* Location:              /Volumes/Jetdrive/Safari Downloads/app-debug_decoded_by_apktool/classes-dex2jar.jar!/com/example/kamalpreetsingh/gurbanikhoj/ShabadFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.2
 */