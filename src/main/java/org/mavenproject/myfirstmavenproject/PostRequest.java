package org.mavenproject.myfirstmavenproject;

public class PostRequest {

    private String postBody;
    private int userID;
    private int postID;

    // Constructors, getters, and setters

    public PostRequest() {
    }

    public PostRequest(String postBody, int userID) {
        this.postBody = postBody;
        this.userID = userID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserId() {
        return userID;
    }

    public void setUserId(int userID) {
        this.userID = userID;
    }

    public int getPostId() {
        return postID;
    }

    public void setPostId(int postID) {
        this.postID = postID;
    }
}
