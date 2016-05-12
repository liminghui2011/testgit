package com.lmh.function.email;

public class EmailSendTest {

	public static void main(String[] args) {
		EmailSendServer emailSendServer = new EmailSendServer("smtp.163.com","benxiaohai2012_20@163.com","benxiaohai6543");
		emailSendServer.addReceiver(new EmailReceiver("heqiuying@lutongnet.com",EmailReceiver.Type.TO));
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setTitle("恭喜道长当老爸了");
		emailDTO.setEmailContent(new EmailContent("恭喜恭喜恭喜你啊，恭喜恭喜恭喜你！"));
		emailSendServer.send(emailDTO);
	}

}
