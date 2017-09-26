package util.str;

public class StrUtil {
	
	//�����ͺ��̽��� ����Ǳ� �� XSS ���� ��� ����
	public String cleanXSS(String value) {
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		//value = value.replaceAll(" ", "&nbsp;");
		//value = value.replaceAll("\r\n", "<br>");
		
		return value;
	}
	
	//�۳��� ��½� ���� �� ���� ó��
	public String conToView(String value){
		value = value.replaceAll(" ", "&nbsp;");
		value = value.replaceAll("\r\n", "<br>");
		return value;
	}

}