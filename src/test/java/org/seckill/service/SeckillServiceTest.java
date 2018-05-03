package org.seckill.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
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
		logger.info("exposer:{}",exposer);
	}

	@Test
	public void testExecuteSeckill() {
		fail("Not yet implemented");
	}

}
