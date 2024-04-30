package org.mavenproject.myfirstmavenproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public String createPost(PostRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            return "User does not exist";
        }
        User user = userOptional.get();
        Post post = new Post(request.getPostBody(), user, new Date());
        postRepository.save(post);
        return "Post created successfully";
    }

    public Optional<Post> getPost(int postID) {
        return postRepository.findById(postID);
    }

    public String editPost(PostRequest request) {
        Optional<Post> postOptional = postRepository.findById(request.getPostId());
        if (postOptional.isEmpty()) {
            return "Post does not exist";
        }
        Post post = postOptional.get();
        post.setPostBody(request.getPostBody());
        postRepository.save(post);
        return "Post edited successfully";
    }

    public String deletePost(int postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isEmpty()) {
            return "Post does not exist";
        }
        postRepository.delete(postOptional.get());
        return "Post deleted";
    }
}
