package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//��ʼ��Spring IOC����
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"}) //���������ļ�   
public class SuccessKilledDaoTest {
	@Autowired
	private SuccessKilledDao successKilledDao;
	 
	@Test
	public void testInsertSuccessKilled() {
		successKilledDao.insertSuccessKilled(1000, 15997299801l);
	}
	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1000,1);
		System.out.println(successKilled.toString());
		System.out.println(successKilled.getSeckill().toString());
	}

}
