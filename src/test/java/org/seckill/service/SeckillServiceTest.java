package org.seckill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"
})//���������ļ�   
public class SeckillServiceTest {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;
	@Test
	public void testGetSeckillList() {
		List<Seckill> seckillList = seckillService.getSeckillList();
		logger.info("seckill=={}",seckillList);
		/*seckill==[Seckill [seckillId=1000, name=1000Ԫ��ɱiphone6, number=100, startTime= 2018, endTime= 2018, createTime= 2018],
		  Seckill [seckillId=1001, name=500Ԫ��ɱipad2, number=200, startTime= 2018, endTime= 2018, createTime= 2018],
		  Seckill [seckillId=1002, name=300Ԫ��ɱС��4, number=300, startTime= 2018, endTime= 2018, createTime= 2018],
		  Seckill [seckillId=1003, name=200Ԫ��ɱ����note, number=400, startTime= 2018, endTime= 2018, createTime= 2018]]*/
	}

	@Test
	public void testGetById() {
		long seckillId=1000;
		Seckill seckill = seckillService.getById(seckillId);
		logger.info("seckill:{}",seckill);
		/*seckill:Seckill [seckillId=1000, name=1000Ԫ��ɱiphone6,
				number=100, startTime=Tue May 01 19:30:19 GMT+08:00 2018,
				endTime=Mon Apr 30 00:00:00 GMT+08:00 2018,
				createTime=Mon Apr 30 18:47:54 GMT+08:00 2018]
*/
	}

	@Test
	public void testExportSeckillUrl() {
		long seckillId=1000;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		//��ɱ����
		if(exposer.isExposed()) {
			//exposer  --- ��ɱ�ӿڵ�ַ(Md5ֵ)
			logger.info("exposer:{}",exposer);
			long userPhone=17754451375l;
			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId,userPhone , exposer.getMd5());
				logger.info("seckillExecution++:++{}",seckillExecution);
			} catch (SeckillCloseException e) {
				//��ɱ�ر�---��治��
				logger.info(e.getMessage());
			}catch (RepeatKillException e) {
				//�ظ���ɱ
				logger.info(e.getMessage());
			}
		}else {
			//��ɱδ����(ʱ��û��)
			
		}
		
	}
	@Test
	public void testExecuteSeckill() {
		seckillService.executeSeckill(1000, 15997299801l, "f6e67e14e55fdddad7b127b0acf750f8");
	}

}
