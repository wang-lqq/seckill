package org.seckill.exception;
/**
 * �ظ���ɱ�쳣(�������쳣)
 * @author Administrator
 *
 */
public class RepeatKillException extends SeckillException{

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}

}
