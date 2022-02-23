package com.example.kasqu.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kasqu.activity.fragment.MitraFragment;
import com.example.kasqu.activity.fragment.SendFragment;
import com.example.kasqu.activity.fragment.UserFragment;

public class AdapterTabLayout extends FragmentStateAdapter {
    public AdapterTabLayout(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1 :
                return new UserFragment();
            case 2 :
                return new MitraFragment();
            case 3:
                return new SendFragment();
        }
        return new MitraFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
