package in.plastico;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private Context context;
    private List<FeedData> feedDataList;

    public FeedAdapter(Context context, List<FeedData> feedDataList) {
        this.context = context;
        this.feedDataList = feedDataList;
    }


    @NonNull
    @Override
    public FeedAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_feeds, null);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.FeedViewHolder feedViewHolder, int i) {
        FeedData feedData = feedDataList.get(i);
        FeedViewHolder.textUserName.setText("From :"+feedData.getUserName());
        FeedViewHolder.textFeedType.setText("feed Type: "+feedData.getFeedType());
        FeedViewHolder.textFeedData.setText("product Name :"+feedData.getProductName());
        FeedViewHolder.textFeedPrice.setText("Price :"+feedData.getProductPrice());
        FeedViewHolder.textFeedQty.setText(" Qty: "+feedData.getProductQty());
    }

    @Override
    public int getItemCount() {
        return feedDataList.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        static TextView textUserName,textFeedType,textFeedData,textFeedPrice,textFeedQty;
        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            textUserName=(TextView)itemView.findViewById(R.id.textUserName);
            textFeedType=(TextView)itemView.findViewById(R.id.textFeedType);
            textFeedData=(TextView)itemView.findViewById(R.id.textFeedData);
            textFeedPrice=(TextView) itemView.findViewById(R.id.textFeedPrice);
            textFeedQty=(TextView)itemView.findViewById(R.id.textFeedQty);
        }
    }
}
