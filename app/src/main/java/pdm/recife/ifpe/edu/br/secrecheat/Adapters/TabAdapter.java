package pdm.recife.ifpe.edu.br.secrecheat.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pdm.recife.ifpe.edu.br.secrecheat.Fragments.ContactsFragment;
import pdm.recife.ifpe.edu.br.secrecheat.Fragments.ConversationsFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tabsTitle = {"CONVERSAS", "CONTATOS"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0) fragment = new ConversationsFragment();
        if(position == 1) fragment = new ContactsFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return tabsTitle.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabsTitle[position];
    }
}
