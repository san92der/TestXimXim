package ru.sanderror.testximxim.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import ru.sanderror.testximxim.R;
import ru.sanderror.testximxim.inerfaces.OnRecyclerViewItemClickListener;
import ru.sanderror.testximxim.models.ListItem;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<ListItem> mItems;
    private Context mContext;
    private OnRecyclerViewItemClickListener mClickListener;

    public PostsAdapter(List<ListItem> posts, Context context, OnRecyclerViewItemClickListener listener) {
        this.mItems = posts;
        this.mContext = context;
        this.mClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lits_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem post = mItems.get(position);

        holder.mPostNumber.setText(String.valueOf(position));
        holder.mPostTitle.setText(post.getTitle());
        Picasso.with(mContext)
                .load(post.getUrl())
                .placeholder( R.drawable.progress_animation )
                .into(holder.mPostImage);
    }

    @Override
    public int getItemCount() {
        if (mItems == null )
            return 0;
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView mPostNumber;
        TextView mPostTitle;
        ImageView mPostImage;

        @Override
        public void onClick(View view) {
            mClickListener.onItemClick(mItems.get(getAdapterPosition()));
        }

        public ViewHolder(View itemView) {
            super(itemView);
            mPostNumber = itemView.findViewById(R.id.post_number);
            mPostTitle =  itemView.findViewById(R.id.post_title);
            mPostImage =  itemView.findViewById(R.id.post_image);

            itemView.setOnClickListener(this);
        }
    }
}