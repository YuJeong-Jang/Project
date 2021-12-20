package co.bithday.access;

import java.util.ArrayList;

import co.bithday.model.Birthday;

public interface BirthdayAccess {
	public void insert(Birthday birthday);

	public void delete(String name);

	public ArrayList<Birthday> selectAll();

	public Birthday selectName(String name);

	public ArrayList<Birthday> selectBirth(String birth);

	public void update(Birthday birthday, String searchedName);

	public ArrayList<Birthday> sendMessageAll();
}