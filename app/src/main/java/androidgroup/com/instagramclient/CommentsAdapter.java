package androidgroup.com.instagramclient;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Sowmya on 2/21/15.
 */
public class CommentsAdapter extends ArrayAdapter<Object> {

    public CommentsAdapter(Context context, List<Comments> objects) {
        super(context, 0, getCommentsObject(objects));
    }

    public static List<Object> getCommentsObject(List<Comments> comments){

        List<Object> objects = new ArrayList<Object>();
        for (Comments comment : comments){
            objects.add(comment);
        }
        return  objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Comments comment = (Comments)getItem(position);
        final PhotoViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new PhotoViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.commentslayout,parent,false);

            viewHolder.imgProfilePicture = (ImageView) convertView.findViewById(R.id.imgCommentsProfilePicture);
            viewHolder.txtUserName = (TextView) convertView.findViewById(R.id.txtUserName);
            viewHolder.txtCreatedTime = (TextView) convertView.findViewById(R.id.txtTime);
            viewHolder.txtComment = (TextView) convertView.findViewById(R.id.txtComment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PhotoViewHolder) convertView.getTag();
        }
        viewHolder.imgProfilePicture.setImageResource(0);
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.GRAY)
                .borderWidthDp(1)
                .cornerRadiusDp(20)
                .oval(true)
                .build();

        Picasso.with(getContext())
                .load(comment.getProfilePicture())
                .transform(transformation)
                .into(viewHolder
                        .imgProfilePicture);

        viewHolder.txtUserName.setText(comment.getUserName());
        Long createdTime = Long.valueOf(comment.getCommentedTime()) * 1000;
        String time = new String(DateUtils.getRelativeTimeSpanString(getContext(), createdTime).toString());
        viewHolder.txtCreatedTime.setText(time);
        viewHolder.txtComment.setText(comment.getComments());
        viewHolder.txtUserName.setText(comment.getUserName());

        return convertView;
    }


    public class PhotoViewHolder {
        public ImageView imgProfilePicture;
        public TextView txtUserName;
        public TextView txtCreatedTime;
        public TextView txtComment;
    }

}
