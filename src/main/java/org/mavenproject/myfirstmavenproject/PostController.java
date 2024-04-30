package org.mavenproject.myfirstmavenproject;

import org.mavenproject.myfirstmavenproject.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }
        User user = userOptional.get();
        Post post = new Post(request.getPostBody(), user, new Date());
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getPost(@RequestParam int postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
        return ResponseEntity.ok(postOptional.get());
    }

    @PatchMapping
    public ResponseEntity<String> editPost(@RequestBody PostRequest request) {
        Optional<Post> postOptional = postRepository.findById(request.getPostId());
        if (postOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
        Post post = postOptional.get();
        post.setPostBody(request.getPostBody());
        postRepository.save(post);
        return ResponseEntity.ok("Post edited successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deletePost(@RequestParam int postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
        postRepository.delete(postOptional.get());
        return ResponseEntity.ok("Post deleted");
    }
}
