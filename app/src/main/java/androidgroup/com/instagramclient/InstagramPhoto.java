package androidgroup.com.instagramclient;

/**
 * Created by Sowmya on 2/16/15.
 */
public class InstagramPhoto {

   private String userName;
   private String profilePicture;
   private String createdTime;
   private String createdImage;
   private String likes;
   private String comments;
   private String caption;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedImage() {
        return createdImage;
    }

    public void setCreatedImage(String createdImage) {
        this.createdImage = createdImage;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
