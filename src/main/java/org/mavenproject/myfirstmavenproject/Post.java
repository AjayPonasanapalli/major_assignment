package org.mavenproject.myfirstmavenproject;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TABLE_A")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;

    private String postBody;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    private Date date;

    public Post() {
    }

    public Post(String postBody, User user, Date date) {
        this.postBody = postBody;
        this.user = user;
        this.date = date;
    }

    @JsonProperty("userID")
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
