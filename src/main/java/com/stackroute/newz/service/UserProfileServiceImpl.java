package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.UserProfile;
import com.stackroute.newz.repository.UserProfileRepository;
import com.stackroute.newz.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.newz.util.exception.UserProfileNotExistsException;

/*
 * This class is implementing the UserProfileRepository interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */
@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	/*
	 * Add a new user. Throw UserProfileAlreadyExistsException if the userProfile
	 * with specified userId already exists.
	 */
	public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {

		Optional<UserProfile> optionalNews = userProfileRepository.findById(user.getUserId());
		if (optionalNews.isPresent()) {
			throw new UserProfileAlreadyExistsException();
		}

		return userProfileRepository.save(user);
	}

	/*
	 * Update an existing userProfile by it's userId. Throw
	 * UserProfileNotExistsException if the userProfile with specified userId does
	 * not exist.
	 */
	public UserProfile updateUserProfile(UserProfile user, String userId) throws UserProfileNotExistsException {

		Optional<UserProfile> optionalReminder = userProfileRepository.findById(userId);
		if (!optionalReminder.isPresent()) {
			throw new UserProfileNotExistsException();
		}

		return userProfileRepository.save(user);
	}

	/*
	 * Delete an existing userProfile by it's userId. Throw
	 * UserProfileNotExistsException if the userProfile with specified userId does
	 * not exist.
	 */
	public void deleteUserProfile(String userId) throws UserProfileNotExistsException {
		Optional<UserProfile> optionalReminder = userProfileRepository.findById(userId);
		if (!optionalReminder.isPresent()) {
			throw new UserProfileNotExistsException();
		}

		userProfileRepository.delete(optionalReminder.get());
	}

	/*
	 * Retrieve an existing userProfile by it's userId. Throw
	 * UserProfileNotExistsException if the userProfile with specified userId does
	 * not exist.
	 */
	public UserProfile getUserProfile(String userId) throws UserProfileNotExistsException {
		Optional<UserProfile> optionalReminder = userProfileRepository.findById(userId);
		if (!optionalReminder.isPresent()) {
			throw new UserProfileNotExistsException();
		}

		return optionalReminder.get();
	}

	/*
	 * Retrieve all existing userProfiles
	 */
	public List<UserProfile> getAllUserProfiles() {

		return userProfileRepository.findAll();
	}

}
