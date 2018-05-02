package org.seckill.service;

import java.util.List;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/**
 * ҵ��ӿ�
 * @author Administrator
 *
 */
public interface SeckillService {
	/**
	 * ��ѯ������ɱ��Ʒ��Ϣ
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * ��ѯ������ɱ��Ʒ��Ϣ
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * ��ɱ���������ɱ�ӿڵ�ַ
	 * �������ϵͳʱ�����ɱʱ��
	 * @param seckillId
	 * @return
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * ִ����ɱ����
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
			throws SeckillException,SeckillCloseException,RepeatKillException;
}
