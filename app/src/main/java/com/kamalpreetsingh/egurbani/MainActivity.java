package com.kamalpreetsingh.egurbani;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kamalpreetsingh.gurbanikhoj.Model.ShabadIDReciever;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {
  private static final String TAG = "MainActivity";
  
  final HomeFragment homeFragment = new HomeFragment();
  
  private FragmentManager mFragmentManager;
  
  private FragmentTransaction mFragmentTransaction;
  
  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
      public boolean onNavigationItemSelected(@NonNull MenuItem param1MenuItem) {
        int i = param1MenuItem.getItemId();
        if (i != 2131230835)
          return !(i != 2131230837);
        Toast.makeText(MainActivity.this, "This will take you to favorites, (not implemented yet).", 0).show();
        return true;
      }
    };
  
  private TextView mTextMessage;
  
  private BottomNavigationView navigation;
  
  final ShabadFragment shabadFragment = new ShabadFragment();
  
  ShabadIDReciever shabadIdReciever;
  
  public void onBackPressed() { super.onBackPressed(); }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427356);
    getSupportActionBar().setTitle("Shabad OS");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    this.mTextMessage = (TextView)findViewById(2131230828);
    this.navigation = (BottomNavigationView)findViewById(2131230834);
    this.navigation.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
    replaceFragment(this.homeFragment, false);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    if (paramMenuItem.getItemId() == 16908332)
      onBackPressed(); 
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void replaceFragment(Fragment paramFragment, boolean paramBoolean) {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(2131230823, paramFragment);
    if (paramBoolean)
      fragmentTransaction.addToBackStack(null); 
    fragmentTransaction.setTransition(4097);
    fragmentTransaction.commit();
  }
  
  public void sendShabadId(String paramString) {
    Bundle bundle = new Bundle();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("shabad ID ");
    stringBuilder2.append(paramString);
    Log.d("MainActivity", stringBuilder2.toString());
    bundle.putString("shabadID", paramString);
    this.shabadFragment.setArguments(bundle);
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("shabad id now is: ");
    stringBuilder1.append(this.shabadFragment.getArguments().get("shabadID"));
    Log.d("MainActivity", stringBuilder1.toString());
    getSupportFragmentManager().beginTransaction().replace(2131230823, this.shabadFragment).addToBackStack(null).commit();
  }
}


/* Location:              /Volumes/Jetdrive/Safari Downloads/app-debug_decoded_by_apktool/classes-dex2jar.jar!/com/example/kamalpreetsingh/gurbanikhoj/MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.2
 */