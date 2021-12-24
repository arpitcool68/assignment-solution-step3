package com.stackroute.newz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.UserProfile;
import com.stackroute.newz.service.UserProfileService;
import com.stackroute.newz.util.exception.UserProfileNotExistsException;

@RestController
@RequestMapping("/api/v1/user")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@PostMapping
	public ResponseEntity<UserProfile> register(@RequestBody UserProfile userProfileObj) {
		try {
			UserProfile searchedUserProfile = userProfileService.getUserProfile(userProfileObj.getUserId());
			if (searchedUserProfile.getUserId() == userProfileObj.getUserId()) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			UserProfile savedUserProfileObj = userProfileService.registerUser(userProfileObj);
			return new ResponseEntity<>(savedUserProfileObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<UserProfile>> getAllUserProfiles() {
		List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();
		if (!userProfiles.isEmpty()) {
			return new ResponseEntity<>(userProfiles, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("{userId}")
	public ResponseEntity<UserProfile> update(@RequestBody UserProfile userProfileObj, @PathVariable String userId) {
		try {
			return new ResponseEntity<>(userProfileService.updateUserProfile(userProfileObj, userId), HttpStatus.OK);
		} catch (UserProfileNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserProfile> getUserProfileById(@PathVariable String userId) {
		try {
			UserProfile userProfileObj;
			if (null != userId) {
				userProfileObj = userProfileService.getUserProfile(userId);
				if (null != userProfileObj) {
					return new ResponseEntity<>(userProfileObj, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (UserProfileNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{userId}")
	public ResponseEntity<UserProfile> delete(@PathVariable String userId) {
		try {
			UserProfile existingNews = userProfileService.getUserProfile(userId);
			userProfileService.deleteUserProfile(existingNews.getUserId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserProfileNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
