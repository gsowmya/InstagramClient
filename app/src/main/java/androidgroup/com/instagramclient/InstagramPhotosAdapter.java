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

import java.util.List;

/**
 * Created by Sowmya on 2/16/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhoto photo = getItem(position);
        PhotoViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new PhotoViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo,parent,false);


            viewHolder.imgProfilePicture = (ImageView) convertView.findViewById(R.id.imgProfilePicture);
            viewHolder.txtUserName = (TextView) convertView.findViewById(R.id.txtUserName);
            viewHolder.txtCreatedTime = (TextView) convertView.findViewById(R.id.txtTime);
            viewHolder.imgCreatedImage = (ImageView) convertView.findViewById(R.id.imgPicture);
            viewHolder.txtCaption = (TextView) convertView.findViewById(R.id.txtCaption);
            viewHolder.txtLikes = (TextView) convertView.findViewById(R.id.txtLikes);
            viewHolder.txtComments = (TextView) convertView.findViewById(R.id.txtComments);
            convertView.setTag(viewHolder);

        }else {
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
                    .load(photo.getProfilePicture())
                    .transform(transformation)
                    .into(viewHolder
                            .imgProfilePicture);

            viewHolder.txtUserName.setText(photo.getUserName());
            Long createdTime = Long.valueOf(photo.getCreatedTime()) * 1000;
            String time = new String(DateUtils.getRelativeTimeSpanString(getContext(),createdTime).toString());
            viewHolder.txtCreatedTime.setText(time);
            viewHolder.imgCreatedImage.setImageResource(0);
            Picasso.with(getContext()).load(photo.getCreatedImage()).into(viewHolder.imgCreatedImage);
            viewHolder.txtCaption.setText(photo.getCaption());
            viewHolder.txtLikes.setText(photo.getLikes());
            viewHolder.txtComments.setText(photo.getComments());

        return convertView;
    }

    public class PhotoViewHolder{
        public  ImageView imgProfilePicture;
        public  TextView txtUserName;
        public  TextView txtCreatedTime;
        public  ImageView imgCreatedImage;
        public  TextView txtCaption;
        public  TextView txtLikes;
        public  TextView txtComments;
    }

}


