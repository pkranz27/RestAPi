package com.Pk.Udemy.User;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Pk.Udemy.Jpa.UserRepository;

@RestController
public class UserJPAResource {
	
	
	private UserRepository repository;
	
	private PostRepository postRepository;
	
	public UserJPAResource( UserRepository repository, PostRepository postRepository) {
	
		this.repository= repository;
		this.postRepository = postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return repository.findAll();
	}
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable Integer id){
		Optional<User> user =  repository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("Id: "+ id);
		}
		EntityModel<User> entityModel= EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id){
		repository.deleteById(id);
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public CollectionModel<Post> retrivePostForAUser(@PathVariable Integer id){
		Optional<User> user =  repository.findById(id);
		
		List<Post> userPost = user.get().getPosts();
		if (user.isEmpty()) {
			throw new UserNotFoundException("Id: "+ id);
		}
		CollectionModel<Post> collectionModel = CollectionModel.of(userPost);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUser(id));
		collectionModel.add(link.withRel("user"));
		return collectionModel;
		
	}
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post){
		Optional<User> user =  repository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("Id: "+ id);
		}
		
		post.setUser(user.get());
		
		Post savedPost =  postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		
		
		return ResponseEntity.created(location).build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
