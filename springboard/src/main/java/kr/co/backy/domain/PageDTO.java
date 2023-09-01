package kr.co.backy.domain;



public class PageDTO {
	private int page;//현재 페이지 값을 나타내는 필드
	private int startNum;//현재 페이지에 시작 넘버
	private int endNum;//현재 페이지에 조회 종료 넘버
	private int startPage;//목록 출력시 하단부의 시작 페이지 수
	private int endPage;//목록 출력시 하단부의 종료 페이지 수
	private int maxPage;//전체 페이지 수
	private int limitList = 10;//한 페이지에 나타낼 수 있는 게시글 수 (기본값으로 10)
	public int getPage() {
		return page;
	}
	public int getStartNum() {
		return startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public int getLimitList() {
		return limitList;
	}
	
	//setter 메서드를 대신해서
	public void calcu(int page, int limit, int boardCount) {
		this.page = page;
		this.limitList = limit;
		this.startNum = (page-1) * limit + 1;
		this.endNum = (page * limit) > boardCount? boardCount: page * limit;
		this.maxPage = (int)Math.ceil(boardCount*1.0/limit);
		this.startPage = (page /10) * 10 + 1;
		this.endPage = startPage + 9 > maxPage ? maxPage : startPage + 9;
		
	}
	
}
