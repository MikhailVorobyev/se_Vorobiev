package sef.module9.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of a Radar 
 * 
 *
 */
public class RadarImpl implements Radar {

	private List<RadarContact> contacts;
	
	/**
	 *  Constructs a new Radar 
	 */
	public RadarImpl(){
		contacts = new ArrayList<>();
	}
	
	
	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#addContact(sef.module8.activity.RadarContact)
	 */
	public RadarContact addContact(RadarContact contact) {
		RadarContact cont = findElement(contact.getContactID());
		if (cont != null) {
				cont.setBearing(contact.getBearing());
				cont.setDistance(contact.getDistance());
				return contact;
			}
		contacts.add(contact);
		return contact;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContact(java.lang.String)
	 */
	public RadarContact getContact(String id) {

		return findElement(id);
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContactCount()
	 */
	public int getContactCount() {
		return contacts.size();
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#removeContact(java.lang.String)
	 */
	public RadarContact removeContact(String id) {
		RadarContact cont = findElement(id);
		if (cont != null) {
			contacts.remove(cont);
			return cont;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts()
	 */
	public List<RadarContact> returnContacts() {
		return new ArrayList<>(contacts);
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts(java.util.Comparator)
	 */
	public List<RadarContact> returnContacts(Comparator<RadarContact> comparator) {
		Collections.sort(contacts, new DistanceComparator());
		return new ArrayList<>(contacts);
	}

	private RadarContact findElement(String id) {
		for (RadarContact cont : contacts) {
			if (cont.getContactID().equals(id)) {
				return cont;
			}
		}
		return null;
	}
}
