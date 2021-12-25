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

import com.stackroute.newz.model.Reminder;
import com.stackroute.newz.service.ReminderService;
import com.stackroute.newz.util.exception.ReminderNotExistsException;

@RestController
@RequestMapping("/api/v1/reminder")
public class ReminderController {

	@Autowired
	private ReminderService reminderService;

	@GetMapping
	public ResponseEntity<List<Reminder>> getAllReminders() {
		List<Reminder> newsList = reminderService.getAllReminders();
		if (!newsList.isEmpty()) {
			return new ResponseEntity<>(newsList, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{reminderId}")
	public ResponseEntity<Reminder> getRemindersById(@PathVariable Integer reminderId) {
		try {
			Reminder reminder;
			if (null != reminderId) {
				reminder = reminderService.getReminder(reminderId);
				if (null != reminder) {
					return new ResponseEntity<>(reminder, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (ReminderNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Reminder> add(@RequestBody Reminder reminderObj) {
		try {
			return new ResponseEntity<>(reminderService.addReminder(reminderObj), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("{reminderId}")
	public ResponseEntity<Reminder> update(@RequestBody Reminder reminderObj, @PathVariable Integer reminderId) {
		try {
			return new ResponseEntity<>(reminderService.updateReminder(reminderObj), HttpStatus.OK);
		} catch (ReminderNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{reminderId}")
	public ResponseEntity<Reminder> delete(@PathVariable Integer reminderId) {
		try {
			reminderService.deleteReminder(reminderId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ReminderNotExistsException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
