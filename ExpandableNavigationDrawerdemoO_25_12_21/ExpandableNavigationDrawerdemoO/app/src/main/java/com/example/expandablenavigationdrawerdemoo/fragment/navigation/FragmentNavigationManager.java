package com.example.expandablenavigationdrawerdemoo.fragment.navigation;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.expandablenavigationdrawerdemoo.BuildConfig;
import com.example.expandablenavigationdrawerdemoo.MainActivity;
import com.example.expandablenavigationdrawerdemoo.R;
import com.example.expandablenavigationdrawerdemoo.fragment.FragmentAction;
import com.example.expandablenavigationdrawerdemoo.fragment.FragmentComedy;
import com.example.expandablenavigationdrawerdemoo.fragment.FragmentDrama;
import com.example.expandablenavigationdrawerdemoo.fragment.FragmentMusical;
import com.example.expandablenavigationdrawerdemoo.fragment.FragmentThriller;


/**
 * @author msahakyan
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private MainActivity mActivity;

    public static FragmentNavigationManager obtain(MainActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragmentAction(String title) {
        showFragment(FragmentAction.newInstance(title), false);
    }

    @Override
    public void showFragmentComedy(String title) {
        showFragment(FragmentComedy.newInstance(title), false);
    }

    @Override
    public void showFragmentDrama(String title) {
        showFragment(FragmentDrama.newInstance(title), false);
    }

    @Override
    public void showFragmentMusical(String title) {
        showFragment(FragmentMusical.newInstance(title), false);
    }

    @Override
    public void showFragmentThriller(String title) {
        showFragment(FragmentThriller.newInstance(title), false);
    }

    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
            .replace(R.id.container, fragment);

        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }
}
