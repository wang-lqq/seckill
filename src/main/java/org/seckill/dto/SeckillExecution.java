package org.seckill.dto;
/**
 * ��װ��ɱִ�к�Ľ��
 * @author Administrator
 *
 */

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

public class SeckillExecution {
	private long seckillId;
	
	//��ɱִ�н��״̬
	private int state;
	
	//״̬��ʾ
	private String stateInfo;
	
	//��ɱ�ɹ�����
	private SuccessKilled successKilled;

	public SeckillExecution(long seckillId,SeckillStatEnum seckillStatEnum, SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = seckillStatEnum.getState();
		this.stateInfo = seckillStatEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public SeckillExecution(long seckillId,SeckillStatEnum seckillStatEnum) {
		this.seckillId = seckillId;
		this.state = seckillStatEnum.getState();
		this.stateInfo = seckillStatEnum.getStateInfo();
	}

	/**
	 * @return the seckillId
	 */
	public long getSeckillId() {
		return seckillId;
	}

	/**
	 * @param seckillId the seckillId to set
	 */
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * @param stateInfo the stateInfo to set
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	/**
	 * @return the successKilled
	 */
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	/**
	 * @param successKilled the successKilled to set
	 */
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
	
	
}
