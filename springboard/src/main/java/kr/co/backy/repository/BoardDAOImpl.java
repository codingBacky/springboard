package kr.co.backy.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.backy.domain.BoardBean;

@Repository
public class BoardDAOImpl implements BoardDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	RowMapper<BoardBean> mapper = new RowMapper<BoardBean>() {
		
		@Override
		public BoardBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardBean board = new BoardBean();
			board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
			board.setBOARD_NAME(rs.getString("BOARD_NAME"));
			board.setBOARD_PASS(rs.getString("BOARD_PASS"));
			board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
			board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
			board.setBOARD_FILE(rs.getString("BOARD_FILE"));
			board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
			board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
			board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
			board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
			board.setBOARD_DATE(rs.getString("BOARD_DATE"));
			return board;
		}
	};
	
	@Override
	public List<BoardBean> boardList(int start, int end) {
		String queryString = "select * from "+
				"(select rownum rnum,imsi.* from "+
				"(select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc) imsi) "+
				"where rnum>=? and rnum<=?";
		return jdbcTemplate.query(queryString, new Object[] {start, end}, mapper);
	}

	@Override
	public int boardCount() {
		
		return jdbcTemplate.queryForObject("select count(*) from board ", Integer.class);
	}

	@Override
	public int boardAdd(BoardBean bean, int num) {
		String query = "insert into board values(?,?,?,?,?,null,?,0,0,0,sysdate)";
		return jdbcTemplate.update(query, num, bean.getBOARD_NAME(), bean.getBOARD_PASS(), bean.getBOARD_SUBJECT(), bean.getBOARD_CONTENT(), num);
	}
	
	@Override
	public int maxNum() {
		String query = "select max(BOARD_NUM) from board";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	/*
	public int seqNum() {
		return jdbcTemplate.queryForObject("select myseq.nextval from dual", Integer.class);
	}
	*/

	@Override
	public BoardBean boardDetail(int num) {
		String query = "select * from board where BOARD_NUM = ?";
		return jdbcTemplate.queryForObject(query,new Object[] {num}, mapper);
	}

	@Override
	public BoardBean boardReplyView(int num) {
		
		return boardDetail(num);
	}

	@Override
	public int sequenceUpdate(int board_RE_REF, int board_RE_SEQ) {
		String query="update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 where BOARD_RE_REF=? and BOARD_RE_SEQ>?";
		return jdbcTemplate.update(query,board_RE_REF, board_RE_SEQ );
		
	}

	@Override
	public int boardReply(BoardBean board, int maxNum) {
		String query = "insert into board(BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT, " +
				 "BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ, " + 
				 "BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)"; 
		/* String query = "insert into board values(?,?,?,?,?,null,?,?,?,0,sysdate)"; */
		return jdbcTemplate.update(query, board.getBOARD_NUM(), board.getBOARD_NAME(), board.getBOARD_PASS(),
										board.getBOARD_SUBJECT(), board.getBOARD_CONTENT(), board.getBOARD_RE_REF(),
										board.getBOARD_RE_LEV(), board.getBOARD_RE_SEQ());

	}

	@Override
	public BoardBean boardModify(int num) {
		
		return boardDetail(num);
	}

	@Override
	public int modifyUpdate(int board_NUM, String board_SUBJECT, String board_CONTENT) {
		String query = "update board set BOARD_SUBJECT=?,BOARD_CONTENT=? where BOARD_NUM=?";
		return jdbcTemplate.update(query, board_SUBJECT, board_CONTENT, board_NUM);
		
	}

	@Override
	public String pwd(int num) {
		String query = "select BOARD_PASS from board where BOARD_NUM=?";
		return jdbcTemplate.queryForObject(query, String.class, num);
	}

	@Override
	public int boardDeleteAction(int num) {
		String query = "delete * from board where num=?";
		return jdbcTemplate.update(query, num);
	}


	
}
