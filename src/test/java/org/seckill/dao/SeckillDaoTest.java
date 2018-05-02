package org.seckill.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * ����spring��junit����,junit����ʱ����spring IOC����
 * @author Administrator
 *
 */
//��ʼ��Spring IOC����
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"}) //���������ļ�   
public class SeckillDaoTest {
	@Autowired
	private SeckillDao seckillDao;
	@Test
	public void testReduceNumber() {
		SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			seckillDao.reduceNumber(1000, sdFormat.parse("2018-04-30 13:24:06"));
			//System.out.println(sdFormat.parse("2018-04-29 13:24:06"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testQueryById() {
		Seckill seckill = seckillDao.queryById(1000);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public void testQueryAll() {
		List<Seckill> seckills = seckillDao.queryAll(0, 3);
		for (Seckill seckill : seckills) {
			System.out.println(seckill);
		}
	}

}
