package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.NumberViewHolder> {

    private Message[] messages;

    private static int viewHolderCount;

    private int numberItems;

    private final ListItemClickListener onClickListener;


    public ContractAdapter(int numListItems, ListItemClickListener listener) {
        this.numberItems = numListItems;
        this.onClickListener = listener;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        Message message = messages[viewHolderCount];
        viewHolder.userTitle.setText(message.getTitle());
        viewHolder.lastChatTime.setText(message.getTime());
        viewHolder.userDescription.setText(message.getDescription());

        switch (message.getIcon()){
            case "TYPE_ROBOT":
                viewHolder.userAvatar.setImageResource(R.drawable.session_robot);
                break;
            case "TYPE_SYSTEM":
                viewHolder.userAvatar.setImageResource(R.drawable.session_system_notice);
                break;
            case "TYPE_STRANGER":
                viewHolder.userAvatar.setImageResource(R.drawable.session_stranger);
                break;

                default:
                    viewHolder.userAvatar.setImageResource(R.drawable.icon_girl);
                    break;
        }


        if (message.isOfficial()){
            viewHolder.robotNotice.setImageResource(R.drawable.im_icon_notice_official);
            viewHolder.robotNotice.setVisibility(View.VISIBLE);
        }
        else{
            viewHolder.robotNotice.setVisibility(View.INVISIBLE);
        }

        Log.d("Adpater","onCreate");
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position);
        Log.d("Adapter","onBind");
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CircleImageView userAvatar;
        private ImageView robotNotice;
        private TextView userTitle;
        private TextView userDescription;
        private TextView lastChatTime;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatar = itemView.findViewById(R.id.iv_avatar);
            robotNotice = itemView.findViewById(R.id.robot_notice);
            userTitle = itemView.findViewById(R.id.tv_title);
            userDescription = itemView.findViewById(R.id.tv_description);
            lastChatTime = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(this);
        }

        public void bind(int position){
            if (messages.length != 0){
                Message message = messages[position];
                userTitle.setText(message.getTitle());
                lastChatTime.setText(message.getTime());
                userDescription.setText(message.getDescription());

                if (message.isOfficial()){
                    robotNotice.setImageResource(R.drawable.im_icon_notice_official);
                    robotNotice.setVisibility(View.VISIBLE);
                }
                else{
                    robotNotice.setVisibility(View.INVISIBLE);
                }

                switch (message.getIcon()){
                    case "TYPE_ROBOT":
                        userAvatar.setImageResource(R.drawable.session_robot);
                        break;
                    case "TYPE_SYSTEM":
                        userAvatar.setImageResource(R.drawable.session_system_notice);
                        break;
                    case "TYPE_STRANGER":
                        userAvatar.setImageResource(R.drawable.session_stranger);
                        break;

                    default:
                        userAvatar.setImageResource(R.drawable.icon_girl);
                        break;
                }

            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (onClickListener != null) {
                onClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public void setMessages(final List<Message> messages) {
        this.messages = messages.toArray(new Message[messages.size()]);
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

}
