package co.birthday.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import co.bithday.access.BirthdayAccess;
import co.bithday.access.BirthdayDAO;
import co.bithday.model.Birthday;
import message.MessageSend;

public class BirthdayCliApp {

	BirthdayAccess access = new BirthdayDAO();
	Scanner scanner = new Scanner(System.in);

	public void start() {
		int menuNum;
		do {
			menuTitle();
			System.out.println("입력 >");
			menuNum = scanner.nextInt();
			switch (menuNum) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				selectAll();
				break;
			case 5:
				selectName();
				break;
			case 6:
				selectBirth();
				break;
			case 7:
				sendMessage();
				break;
			}
		} while (menuNum != 0);
		System.out.println("리마인더 종료.");
	}

	private void insert() {
		ArrayList list = new ArrayList();
		Birthday b = new Birthday();

		System.out.println("이름입력");
		String name = scanner.next();

		System.out.println("성별입력");
		String sex = scanner.next();

		System.out.println("생일입력");
		String birth = scanner.next();

		String temp = birth.substring(0, 2);
		int age = (Calendar.getInstance().get(Calendar.YEAR)) - (1900 + (Integer.parseInt(temp)));
		System.out.println("나이" + age);

		System.out.println("전화번호입력");
		String tel = scanner.next();
		b.setName(name);
		b.setSex(sex);
		b.setBirth(birth);
		b.setTel(tel);
		access.insert(b);
	}

	private void update() {
		Birthday birthday = new Birthday();
		String searchName;
		System.out.println("조회할 이름입력");
		searchName = scanner.next();
		access.selectName(searchName);

		System.out.println("바꿀 이름입력");
		birthday.setName(scanner.next());

		System.out.println("성별입력");
		birthday.setSex(scanner.next());

		System.out.println("생일입력");
		birthday.setBirth(scanner.next());

		System.out.println("전화번호입력");
		birthday.setTel(scanner.next());

		access.update(birthday, searchName);
	}

	private void delete() {
		System.out.println("삭제할 친구를 입력하세요.");
		String name = "";
		name = scanner.next();
		access.delete(name);
	}

	private void selectAll() {
		List<Birthday> list = access.selectAll();
		System.out.println("<친구목록>");
		for (Birthday birthday : list) {
			System.out.println(birthday);
		}
	}

	private void selectName() {
		System.out.println("검색할 이름을 입력하세요.");
		String name = "";
		name = scanner.next();
		Birthday birthday = access.selectName(name);
		System.out.println(birthday);
	}

	private void selectBirth() {
		System.out.println("검색할 날짜를 입력하세요. ex)910525");
		String birth = "";
		birth = scanner.next();
		ArrayList<Birthday> birthday = access.selectBirth(birth);
		for(Birthday b : birthday) {
		System.out.println(b);
	}
	}

	private void sendMessage() {
		System.out.println("생일 축하 문자를 발송하시겠습니까? y or n");

		String answer = scanner.next();
		if (answer.equals("y")) {
			MessageSend message = new MessageSend();
			ArrayList<Birthday> birthday = access.sendMessageAll();
			for (Birthday b : birthday) {
				message.messageSend(b);
				System.out.println("메세지가 발송되었습니다.");
			}
		}

	}

	private void menuTitle() {
		System.out.println("ㅡㅡㅡㅡㅡㅡ리마인더 관리ㅡㅡㅡㅡㅡㅡ");
		System.out.println("|           1. 친구 등록            |");
		System.out.println("|           2. 정보 수정            |");
		System.out.println("|           3. 친구 삭제            |");
		System.out.println("|           4. 전체 조회            |");
		System.out.println("|           5. 이름으로 찾기        |");
		System.out.println("|           6. 날짜로 찾기          |");
		System.out.println("|           7. 문자로 츄카츄카링    |");
		System.out.println("ㅡㅡㅡㅡㅡㅡ0. 종료ㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}
}
