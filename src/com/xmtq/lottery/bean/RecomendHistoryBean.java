package com.xmtq.lottery.bean;

/**
 * 推荐历史
 */
public class RecomendHistoryBean extends EntityBase {
	private static final long serialVersionUID = -8391624578292769336L;

	// <companyId>150105005</companyId>
	// <league>法国杯</league>
	// <matchTeam>蒙彼利埃</matchTeam>
	// <matchId>62422</matchId>
	// <hostTeam>巴黎圣曼</hostTeam>
	// <gameTime>2015-01-06 03:44:00</gameTime>
	// <bqContent>胜平</bqContent>
	// <bqkj>平负</bqkj>
	// <bqHit>未命中</bqHit>
	// <commendUser>张慧华</commendUser>
	// <commendId>1</commendId>
	// <supportVotes>0</supportVotes>
	// <againstVotes>0</againstVotes>
	// <content/>

	private String companyId;
	private String league;
	private String matchTeam;
	private String matchId;
	private String hostTeam;
	private String gameTime;
	private String bqContent;
	private String bqkj;
	private String bqHit;
	private String commendUser;
	private String commendId;
	private String supportVotes;
	private String againstVotes;
	private String content;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getMatchTeam() {
		return matchTeam;
	}

	public void setMatchTeam(String matchTeam) {
		this.matchTeam = matchTeam;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getHostTeam() {
		return hostTeam;
	}

	public void setHostTeam(String hostTeam) {
		this.hostTeam = hostTeam;
	}

	public String getGameTime() {
		return gameTime;
	}

	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}

	public String getBqContent() {
		return bqContent;
	}

	public void setBqContent(String bqContent) {
		this.bqContent = bqContent;
	}

	public String getBqkj() {
		return bqkj;
	}

	public void setBqkj(String bqkj) {
		this.bqkj = bqkj;
	}

	public String getBqHit() {
		return bqHit;
	}

	public void setBqHit(String bqHit) {
		this.bqHit = bqHit;
	}

	public String getCommendUser() {
		return commendUser;
	}

	public void setCommendUser(String commendUser) {
		this.commendUser = commendUser;
	}

	public String getCommendId() {
		return commendId;
	}

	public void setCommendId(String commendId) {
		this.commendId = commendId;
	}

	public String getSupportVotes() {
		return supportVotes;
	}

	public void setSupportVotes(String supportVotes) {
		this.supportVotes = supportVotes;
	}

	public String getAgainstVotes() {
		return againstVotes;
	}

	public void setAgainstVotes(String againstVotes) {
		this.againstVotes = againstVotes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
