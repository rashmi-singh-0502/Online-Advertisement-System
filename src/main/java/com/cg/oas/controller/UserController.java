package com.cg.oas.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oas.dto.User;
import com.cg.oas.exception.RecordNotFoundException;
import com.cg.oas.exception.UserNotFoundException;
import com.cg.oas.service.UserService;
import com.cg.oas.service.UserServiceImpl;
import com.cg.oas.dto.Advertise;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
 * -------------URL FOR OAS--------------------
 */
//http://localhost:8080/cgoas/user
@RestController
@RequestMapping("/cgoas")
@CrossOrigin("*")

public class UserController 
{

	@Autowired
	private UserService userService;
	private static Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
	
	
	/*
	 * ---------------GETTING ALL THE USER DETAILS-------------
	 */
	@ApiOperation(value="View User List")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="User viewed"),
			@ApiResponse(code=404,message="No user EXIST")
	})
	@GetMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() throws RecordNotFoundException
     {
		logger.info("getAllUser: User Viwed Successfully!");
	   return userService.getAllUser();
	 } 
	
	/*
	 * ------------------RETURNS SPECIFIC USER BY ID FROM THE DATABASE----------------------
	 */
    @ApiOperation(value="Get USER by id")
    @ApiResponses(value= {
            @ApiResponse(code=201, message="Found the user"),
            @ApiResponse(code=404, message = "No user found")
    })
    @GetMapping(value="/user/search/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable("id") int user_id) throws UserNotFoundException
    {
    	logger.info("getUserById: User Displayed Successfully!");
        return userService.getUserById(user_id);
    }
    
	/*
	 *-------------------- DELETING USER BY ID----------------
	 */
	@ApiOperation(value="Delete User By Id")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="User deleted"),
			@ApiResponse(code=201,message="User deleted Successfully"),
			@ApiResponse(code=404,message="No user exists")
	})
	 @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User deleteById(@PathVariable(value="id") int user_id) throws UserNotFoundException
	 {
		   logger.info("deleteUserById: User Deleted Successfully!");
			return userService.deleteById(user_id);
	 }
	
	/*
	 * -----------------------GETTING A USER BY NAME-------------
	 */
	@ApiOperation(value="Get User By Name")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="User Displayed"),
			@ApiResponse(code=404,message="No user EXIST")
	})
	@GetMapping(value="/user/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserByName(@PathVariable("name") String name) throws 	UserNotFoundException
    {
		logger.info("getUserByName: User Displayed Successfully!");
		return userService.getUserByName(name);
	}
	
	/*
	 * -----------------------DELETING USER BY ITS NAME-----------------
	 */
	@ApiOperation(value="Delete User By Name")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="User deleted"),
			@ApiResponse(code=404,message="No user exists")
	})
	@DeleteMapping(value= "/user/{name}/delete", produces = MediaType.APPLICATION_JSON_VALUE) 
	private List<User> deleteUser(@PathVariable("name") String name)    throws UserNotFoundException
	{  
		logger.info("deleteUserByName: User Deleted Successfully!");
	      return userService.deleteUser(name);  
	}

	
	@ApiOperation("Returns all users")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="New Registration of user is created"),
			@ApiResponse(code=404,message="No such user found")
	})
	@GetMapping(value="/user", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() 
	{
		//To get all the users who posted their advertisement
		return userService.getAllUsers();
	}
	
	
	@ApiOperation("Creates a new User")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="Retriving all new advertises is successfully completed"),
			@ApiResponse(code=404,message="No advertise found")
	})
	@GetMapping(value="/advertise", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Advertise> getAllAdvertises() throws RecordNotFoundException
	{
		//To get all advertises which are posted by users
		return userService.getAllAdvertises();
	}
	
	
	@ApiOperation("Creates a new User")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="Registration of new user is successfully completed"),
			@ApiResponse(code=404,message="No such user found")
	})
	@PostMapping(value="/advertises", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Advertise> createNewAdvertise(@Valid @RequestBody Advertise advertise) {
		//To post a new advertise 
		return new ResponseEntity<Advertise>(userService.createNewAdvertise(advertise), HttpStatus.OK);
	}
}
