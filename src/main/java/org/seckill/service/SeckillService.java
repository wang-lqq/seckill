package org.seckill.service;

import java.util.List;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/**
 * 业务接口
 * @author Administrator
 *
 */
public interface SeckillService {
	/**
	 * 查询所有秒杀商品信息
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个秒杀商品信息
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 秒杀开启输出秒杀接口地址
	 * 否则输出系统时间和秒杀时间
	 * @param seckillId
	 * @return
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
			throws SeckillException,SeckillCloseException,RepeatKillException;
}
