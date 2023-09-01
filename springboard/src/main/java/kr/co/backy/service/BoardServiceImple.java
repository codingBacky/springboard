package kr.co.backy.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.backy.domain.BoardBean;
import kr.co.backy.domain.PageDTO;
import kr.co.backy.repository.BoardDAO;

@Service
public class BoardServiceImple implements BoardService{
	
	//@Inject ,@Resource (java 에서 제공함)를 써도됨
	@Autowired//spring에서 제공
	private BoardDAO dao;

	@Override
	public List<BoardBean> boardList(int start, int end) {

		return dao.boardList(start, end);
	}

	@Override
	public int boardCount() {

		return dao.boardCount();//일시키기
	}

	@Override
	public PageDTO pageCalcu(HttpServletRequest request, int boardCount) {
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page")) ;
		int limit = request.getParameter("limit") == null ? 10 : Integer.parseInt(request.getParameter("limit")) ;
		PageDTO paging = new PageDTO();
		paging.calcu(page, limit, boardCount);
		return paging;
	}

	@Override
	public int boardAdd(BoardBean bean) {
		int num = dao.maxNum() + 1;
		
		return dao.boardAdd(bean, num);
		
	}

	@Override
	public BoardBean boardDetail(int num) {
		
		return dao.boardDetail(num);
	}

	@Override
	public BoardBean boardReplyView(int num) {
		
		return dao.boardReplyView(num);
	}

	@Override
	public int boardReplyAction(BoardBean board) {
		int maxNum = dao.maxNum() +1;
		dao.sequenceUpdate(board.getBOARD_RE_REF(), board.getBOARD_RE_SEQ());
		board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ()+1);
		board.setBOARD_RE_LEV(board.getBOARD_RE_LEV()+1);
		board.setBOARD_NUM(maxNum);
		int result = dao.boardReply(board, maxNum);
		return maxNum;
		
	}

	@Override
	public BoardBean boardModify(int num) {
		
		return dao.boardModify(num);
	}

	@Override
	public int boardModifyAction(BoardBean board) {
		
		return dao.modifyUpdate(board.getBOARD_NUM(),board.getBOARD_SUBJECT(),board.getBOARD_CONTENT());
		
	}

	@Override
	public String userCheck(int num) {
		
		return dao.pwd(num);
	}

	@Override
	public int boardDeleteAction(int num) {
		
		return dao.boardDeleteAction(num);
		
	}

}
