package kr.co.backy.repository;

import java.util.List;

import kr.co.backy.domain.BoardBean;

public interface BoardDAO {

	List<BoardBean> boardList(int start, int end);

	int boardCount();

	int maxNum();

	int boardAdd(BoardBean bean, int num);

	BoardBean boardDetail(int num);

	BoardBean boardReplyView(int num);

	int sequenceUpdate(int board_RE_REF, int board_RE_SEQ);

	int boardReply(BoardBean board, int maxNum);

	BoardBean boardModify(int num);

	int modifyUpdate(int BOARD_NUM, String board_SUBJECT, String board_CONTENT);

	String pwd(int num);

	int boardDeleteAction(int num);

	
}
