package in.plastico;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

interface ClickListener {
    void onClick(View view, int position);
    void onLongClick(View view,int position);
}


public class FeedsBuyFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    List<FeedData> feedDataList;
    List<String> feedIds;
    RecyclerView recyclerView;

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeds_buy, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBuy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        feedDataList = new ArrayList<>();
        FeedData fd = new FeedData("1","ravi","DemoName","5","1200","DemoBrand","buy","10%");
        feedDataList.add(new FeedData("1","ravi","DemoName","5","1200","DemoBrand","buy","10%"));
        feedDataList.add(new FeedData("1","ravi","DemoName","5","1200","DemoBrand","buy","10%"));
        feedDataList.add(new FeedData("1","ravi","DemoName","5","1200","DemoBrand","buy","10%"));
        feedDataList.add(fd);
        feedDataList.add(fd);
        feedDataList.add(fd);
        feedDataList.add(fd);
        feedDataList.add(fd);
        feedDataList.add(fd);
        feedDataList.add(fd);


        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int resId = R.anim.layout_animation_fall_down;
                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
                recyclerView.setLayoutAnimation(animation);
                swipeRefreshLayout.setRefreshing(true);

                feedDataList.removeAll(feedDataList);

                feedDataList.add(new FeedData("1","ravi","DemoName","5","1200","DemoBrand","buy","10%"));
                feedDataList.add(new FeedData("1","ravi","DemoName","5","1200","DemoBrand","buy","10%"));
                feedDataList.add(new FeedData("1","ravi","DemoName","5","1200","DemoBrand","buy","10%"));
                FeedData fd = new FeedData("1","ravi","DemoName","5","1200","DemoBrand","buy","10%");
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                feedDataList.add(fd);
                swipeRefreshLayout.setRefreshing(false);


            }
        });

        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        //swipeRefreshLayout.setColorSchemeColors(R.color.pink, R.color.indigo, R.color.lime);

        FeedAdapter feedAdapter = new FeedAdapter(getContext(),feedDataList);
        recyclerView.setAdapter(feedAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Toast.makeText(getContext(), "Feed Selected", Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
