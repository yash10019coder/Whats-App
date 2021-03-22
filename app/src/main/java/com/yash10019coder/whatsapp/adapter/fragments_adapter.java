package com.yash10019coder.whatsapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yash10019coder.whatsapp.fragments.calls;
import com.yash10019coder.whatsapp.fragments.chats;
import com.yash10019coder.whatsapp.fragments.status;

public class fragments_adapter extends FragmentPagerAdapter {

    public fragments_adapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new chats();
            case 2:
                return new status();
            case 3:
                return new calls();
            default:
                return new chats();
        }

//        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String temp = null;
        if (position == 0)
            temp = "CHATS";
        else if (position == 1)
            temp = "STATUS";
        else if (position == 2)
            temp = "CALLS";
        return temp;
    }
}

