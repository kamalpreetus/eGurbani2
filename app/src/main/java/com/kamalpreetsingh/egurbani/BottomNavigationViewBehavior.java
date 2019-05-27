package com.kamalpreetsingh.egurbani;

import androidx.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.view.View;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {
  private int height;
  
  private void slideDown(BottomNavigationView paramBottomNavigationView) {
    paramBottomNavigationView.clearAnimation();
    paramBottomNavigationView.animate().translationY(this.height).setDuration(200L);
  }
  
  private void slideUp(BottomNavigationView paramBottomNavigationView) {
    paramBottomNavigationView.clearAnimation();
    paramBottomNavigationView.animate().translationY(0.0F).setDuration(200L);
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, BottomNavigationView paramBottomNavigationView, int paramInt) {
    this.height = paramBottomNavigationView.getHeight();
    return super.onLayoutChild(paramCoordinatorLayout, paramBottomNavigationView, paramInt);
  }
  
  public void onNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, @NonNull BottomNavigationView paramBottomNavigationView, @NonNull View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt2 > 0) {
      slideDown(paramBottomNavigationView);
      return;
    } 
    if (paramInt2 < 0)
      slideUp(paramBottomNavigationView); 
  }
  
  public boolean onStartNestedScroll(@NonNull CoordinatorLayout paramCoordinatorLayout, BottomNavigationView paramBottomNavigationView, @NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2) { return (paramInt1 == 2); }
}


/* Location:              /Volumes/Jetdrive/Safari Downloads/app-debug_decoded_by_apktool/classes-dex2jar.jar!/com/example/kamalpreetsingh/gurbanikhoj/BottomNavigationViewBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.2
 */