package androidgroup.com.instagramclient;

import java.io.Serializable;

/**
 * Created by Sowmya on 2/21/15.
 */
public class Comments implements Serializable {

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    private String profilePicture;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentedTime() {
        return commentedTime;
    }

    public void setCommentedTime(String commentedTime) {
        this.commentedTime = commentedTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    private String userName;
    private String commentedTime;
    private String comments;


}
