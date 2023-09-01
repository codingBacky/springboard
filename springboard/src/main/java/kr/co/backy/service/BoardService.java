package kr.co.backy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.co.backy.domain.BoardBean;
import kr.co.backy.domain.PageDTO;

public interface BoardService {

	List<BoardBean> boardList(int start, int end);

	int boardCount();

	PageDTO pageCalcu(HttpServletRequest request, int boardCount);

	int boardAdd(BoardBean bean);

	BoardBean boardDetail(int num);

	BoardBean boardReplyView(int num);

	int boardReplyAction(BoardBean board);

	BoardBean boardModify(int num);

	int boardModifyAction(BoardBean board);

	String userCheck(int num);

	int boardDeleteAction(int num);

}
