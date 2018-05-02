package org.seckill.exception;
/**
 * 秒杀关闭异常(1.库存不足2.秒杀时间结束
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
