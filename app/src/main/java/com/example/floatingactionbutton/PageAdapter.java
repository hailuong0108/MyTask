package com.example.floatingactionbutton;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

public class PageAdapter extends FragmentPagerAdapter {
    private int numberTab;
    private Map<Integer,String > mFragment;
    private FragmentManager fragmentManager;
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
        {
            return  "Task";
        }
        else
            return  "Done";

    }

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numberTab =behavior;// behavior= hành vi
        mFragment= new HashMap<Integer, String>();
    }


//    public Fragment getFragment (int i){
//        String tag= mFragment.get(i);
//        if(tag==null)
//            return null;
//        return fragmentManager.findFragmentByTag(tag);
//    }

//    @Override
//    public int getItemPosition(@NonNull java.lang.Object object) {
//        return POSITION_NONE;
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return  new TaskTab();
            case 1:
                return  new DoneTab();
                default:return null;


        }

    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return POSITION_NONE;
//    }

    @Override
    public int getCount() {
        return 2;
    }
}
