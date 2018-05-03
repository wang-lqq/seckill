package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
@Service
public class SeckillServiceImpl implements SeckillService {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	@Autowired
	private SeckillDao seckillDao;
	
	private final String slat="sdewrerf+s@&*(*)(|?>]-33SDIWEi";
	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		if(seckill==null) {
			return new Exposer(false, seckillId);
		}
		long nowTime = new Date().getTime();
		long startTime=seckill.getStartTime().getTime();
		long endTime=seckill.getEndTime().getTime();
		if(nowTime<startTime || nowTime>endTime) {
			return new Exposer(false, seckillId, nowTime, startTime, endTime);
		}
		return new Exposer(true, getMD5(seckillId), seckillId);
	}
	/**
	 * 
	 * @param seckillId
	 * @return
	 */
	public String getMD5(long seckillId) {
		String base=seckillId+"/"+slat;
		String md5=DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	@Override
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException {
		if(md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		try {
			int updateCount = seckillDao.reduceNumber(seckillId, new Date());
			if(updateCount<=0) {
				throw new SeckillCloseException("seckill is closed");
			}else {
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				if(insertCount<=0) {
					throw new RepeatKillException("seckill repeated");
				}
				SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
				return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS, successKilled);
			}
		}catch (SeckillCloseException e) {//秒杀关闭
			throw e;
		}catch(RepeatKillException e){//重复秒杀
			throw e;
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new SeckillException("seckill inner error");
		}
	}
	

}
