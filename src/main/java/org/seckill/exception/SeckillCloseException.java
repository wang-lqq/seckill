package org.seckill.exception;
/**
 * ��ɱ�ر��쳣(1.��治��2.��ɱʱ�����
 * @author Administrator
 *
 */
public class SeckillCloseException extends SeckillException {

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloseException(String message) {
		super(message);
	}

}
