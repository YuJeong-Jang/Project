package message;

import java.util.HashMap;

import org.json.simple.JSONObject;

import co.bithday.model.Birthday;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class MessageSend {

	public void messageSend(Birthday birthday) {
		String api_key = "NCS3BSRILDCLCB2G";
		String api_secret = "GRGZCWP7WANVY3A6YLEUGVPE83TTGR5V";
		Message coolsms = new Message(api_key, api_secret);

		HashMap<String, String> params = new HashMap<String, String>();

		params.put("to", birthday.getTel());
		params.put("from", "01041650784");
		params.put("type", "SMS");
		params.put("text", "사랑하는" + birthday.getName() + "(이)의 생일을 축하합니다!!!!!");
		params.put("app_version", "test app 1.2"); 
		try {
			coolsms.send(params);
		} catch (CoolsmsException e) {
			e.printStackTrace();
		}
	}
}