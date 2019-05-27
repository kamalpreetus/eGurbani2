package com.kamalpreetsingh.egurbani;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.widget.DividerItemDecoration;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kamalpreetsingh.gurbanikhoj.Model.DatabaseAccess;
import com.example.kamalpreetsingh.gurbanikhoj.Model.QueryResult;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemClickListener {
  private String GURBANI_THICK_FONTS = "fonts/gurbaniwebthick.ttf";
  
  int MIN_CHARS_REQUIRED = 2;
  
  private String TAG = "HomeFragment";
  
  SearchResultAdapter mAdapter;
  
  OnFragmentInteractionListener mCallback;
  
  RecyclerView recyclerView;
  
  private void performSearchViewActions(View paramView) {
    SearchView searchView = (SearchView)paramView.findViewById(2131230802);
    searchView.onActionViewExpanded();
    setFonts(searchView, this.GURBANI_THICK_FONTS);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          private boolean shouldQueryBeSearched(String param1String) { return (param1String.length() > HomeFragment.this.MIN_CHARS_REQUIRED); }
          
          public boolean onQueryTextChange(String param1String) {
            if (shouldQueryBeSearched(param1String))
              HomeFragment.this.mAdapter.getFilter().filter(param1String); 
            return true;
          }
          
          public boolean onQueryTextSubmit(String param1String) { return true; }
        });
  }
  
  private void setFonts(SearchView paramSearchView, String paramString) { ((TextView)paramSearchView.findViewById(2131230878)).setTypeface(Typeface.createFromAsset(getActivity().getAssets(), paramString)); }
  
  private void setUpResultRecyclerView(@NonNull View paramView) {
    this.recyclerView = (RecyclerView)paramView.findViewById(2131230869);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    this.recyclerView.setLayoutManager(linearLayoutManager);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.recyclerView.getContext(), linearLayoutManager.getOrientation());
    this.recyclerView.addItemDecoration(dividerItemDecoration);
    ArrayList arrayList = new ArrayList();
    arrayList.add(new QueryResult());
    this.mAdapter = new SearchResultAdapter(getActivity(), arrayList);
    this.mAdapter.setClickListener(this);
    this.recyclerView.setAdapter(this.mAdapter);
  }
  
  public void customOnItemClick(View paramView, int paramInt) {
    FragmentActivity fragmentActivity = getActivity();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("You clicked on row number ");
    stringBuilder.append(paramInt);
    Toast.makeText(fragmentActivity, stringBuilder.toString(), 0).show();
    String str = this.mAdapter.getItem(paramInt).getShabadID();
    Log.d(this.TAG, str);
    this.mCallback.sendShabadId(str);
  }
  
  public void onAttach(Context paramContext) {
    super.onAttach(paramContext);
    try {
      this.mCallback = (OnFragmentInteractionListener)getContext();
      return;
    } catch (ClassCastException classCastException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramContext.toString());
      stringBuilder.append(" must implement TextClicked");
      throw new ClassCastException(stringBuilder.toString());
    } 
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) { return paramLayoutInflater.inflate(2131427372, paramViewGroup, false); }
  
  public void onDetach() {
    this.mCallback = null;
    super.onDetach();
  }
  
  public void onViewCreated(@NonNull View paramView, @Nullable Bundle paramBundle) {
    super.onViewCreated(paramView, paramBundle);
    setUpResultRecyclerView(paramView);
    performSearchViewActions(paramView);
  }
  
  public static interface OnFragmentInteractionListener {
    void sendShabadId(String param1String);
  }
  
  private class SearchAsyncTask extends AsyncTask<String, String, List<QueryResult>> {
    ProgressDialog progressDialog;
    
    protected List<QueryResult> doInBackground(String... param1VarArgs) {
      publishProgress(new String[] { "Sleeping..." });
      DatabaseAccess databaseAccess = DatabaseAccess.getInstance(HomeFragment.this.getActivity());
      databaseAccess.open();
      return databaseAccess.getFirstLetterAnywhere(param1VarArgs[0], "");
    }
    
    protected void onPostExecute(List<QueryResult> param1List) {
      super.onPostExecute(param1List);
      String str = HomeFragment.this.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onPostExecute - done.");
      stringBuilder.append(param1List.toString());
      Log.d(str, stringBuilder.toString());
    }
    
    protected void onProgressUpdate(String... param1VarArgs) {}
  }
}


/* Location:              /Volumes/Jetdrive/Safari Downloads/app-debug_decoded_by_apktool/classes-dex2jar.jar!/com/example/kamalpreetsingh/gurbanikhoj/HomeFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.2
 */