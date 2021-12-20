package jframe.window.birthday;

public class Birthday {
	protected String name;
	protected String sex;
	protected String birth;
	protected String tel;

	public Birthday() {
		super();
	}

	public Birthday(String name, String sex, String birth, String tel) {
		super();
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "이름=" + name + ", 성별=" + sex + ", 생일=" + birth + ", 전화번호=" + tel;
	}

	public void print() {
		System.out.println("이름 : " + this.name);
		System.out.println(" 성별 : " + this.sex);
		System.out.println(" 생일 : " + this.birth);
		System.out.println("전화번호 : " + this.tel);
	}
}
