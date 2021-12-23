package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.Reminder;
import com.stackroute.newz.repository.ReminderRepository;
import com.stackroute.newz.util.exception.ReminderNotExistsException;

/*
 * This class is implementing the ReminderService interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */
@Service
@Transactional
public class ReminderServiceImpl implements ReminderService {

	@Autowired
	private ReminderRepository reminderRepository;

	/*
	 * Add a new reminder.
	 */
	public Reminder addReminder(Reminder reminder) {
		return reminderRepository.save(reminder);
	}

	/*
	 * Update an existing reminder by it's reminderId. Throw
	 * ReminderNotExistsException if the reminder with specified reminderId does not
	 * exist.
	 */
	public Reminder updateReminder(Reminder reminder) throws ReminderNotExistsException {

		Optional<Reminder> optionalReminder = reminderRepository.findById(reminder.getReminderId());
		if (!optionalReminder.isPresent()) {
			throw new ReminderNotExistsException();
		}

		return reminderRepository.save(reminder);
	}

	/*
	 * Delete an existing reminder by it's reminderId. Throw
	 * ReminderNotExistsException if the reminder with specified reminderId does not
	 * exist.
	 */
	public void deleteReminder(int reminderId) throws ReminderNotExistsException {

		Optional<Reminder> optionalReminder = reminderRepository.findById(reminderId);
		if (!optionalReminder.isPresent()) {
			throw new ReminderNotExistsException();
		}

		reminderRepository.delete(optionalReminder.get());
	}

	/*
	 * Retrieve an existing reminder by it's reminderId. Throw
	 * ReminderNotExistsException if the reminder with specified reminderId does not
	 * exist.
	 */
	public Reminder getReminder(int reminderId) throws ReminderNotExistsException {

		Optional<Reminder> optionalReminder = reminderRepository.findById(reminderId);
		if (!optionalReminder.isPresent()) {
			throw new ReminderNotExistsException();
		}

		return optionalReminder.get();
	}

	/*
	 * Retrieve all existing reminders
	 */
	public List<Reminder> getAllReminders() {
		return reminderRepository.findAll();
	}

}
