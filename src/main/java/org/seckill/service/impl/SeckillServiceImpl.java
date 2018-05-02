package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

public class SeckillServiceImpl implements SeckillService {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private SuccessKilledDao successKilledDao;
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
		//鏈煡璇㈠埌绉掓潃鐨勫晢鍝佽褰�
		if(seckill==null) {
			return new Exposer(false, seckillId);
		}
		long nowTime = new Date().getTime();
		long startTime=seckill.getStartTime().getTime();
		long endTime=seckill.getEndTime().getTime();
		//绉掓潃鏈紑鍚�,鎴栫粨鏉�
		if(nowTime<startTime || nowTime>endTime) {
			return new Exposer(false, seckillId, nowTime, startTime, endTime);
		}
		//绉掓潃寮�鍚�
		return new Exposer(true, getMD5(seckillId), seckillId);
	}
	/**
	 * 杞寲鐗瑰畾瀛楃涓茬殑杩囩▼,涓嶅彲閫�
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
			//鎵ц绉掓潃鎿嶄綔:鍑忓簱瀛�+璁板綍璐拱琛屼负
			int updateCount = seckillDao.reduceNumber(seckillId, new Date());
			//鏇存柊澶辫触1:搴撳瓨涓�0  2:褰撳墠鏃堕棿灏忎簬绉掓潃寮�鍚椂闂存垨鑰呭綋鍓嶆椂闂村ぇ浜庣鏉�缁撴潫鏃堕棿
			//杩欎袱绉嶆儏鍐典负绉掓潃鍏抽棴寮傚父
			if(updateCount<=0) {
				throw new SeckillCloseException("seckill is closed");
			}else {
				//鎻掑叆璐拱璁板綍
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				if(insertCount<=0) {
					throw new RepeatKillException("seckill repeated");
				}
				//绉掓潃鎴愬姛
				SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
				return new SeckillExecution(seckillId, 1, "绉掓潃鎴愬姛", successKilled);
			}
		}catch (SeckillCloseException e) {
			throw e;
		}catch(RepeatKillException e){
			throw e;
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			//鎵�鏈夌紪璇戝櫒寮傚父杞寲涓鸿繍琛屾湡寮傚父
			throw new SeckillException("seckill inner error");
		}
		
		
	}
	

}
