package org.seckill.dto;
/**
 * 封装秒杀执行后的结果
 * @author Administrator
 *
 */

import org.seckill.entity.SuccessKilled;

public class SeckillExecution {
	private long seckillId;
	
	//秒杀执行结果状态
	private int state;
	
	//状态表示
	private String stateInfo;
	
	//秒杀成功对象
	private SuccessKilled successKilled;

	public SeckillExecution(long seckillId, int state, String stateInfo, SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = state;
		this.stateInfo = stateInfo;
		this.successKilled = successKilled;
	}

	public SeckillExecution(long seckillId, int state, String stateInfo) {
		this.seckillId = seckillId;
		this.state = state;
		this.stateInfo = stateInfo;
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
