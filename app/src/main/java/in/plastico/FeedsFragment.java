package in.plastico;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import in.plastico.R;

public class FeedsFragment extends Fragment {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feeds, container,false);
        mSectionsPageAdapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setText("Buyer's Feeds");
        tabLayout.getTabAt(1).setText("Seller's Feeds");
        return rootView;
    }
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new FeedsBuyFragment());
        adapter.addFragment(new FeedsSellFragment());
        viewPager.setAdapter(adapter);
    }
}
