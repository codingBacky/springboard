package kr.co.backy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardBean {
	private int BOARD_NUM;
	private String BOARD_NAME;
	private String BOARD_PASS;
	private String BOARD_SUBJECT;
	private String BOARD_CONTENT;
	private String BOARD_FILE;
	private int BOARD_RE_REF;
	private int BOARD_RE_LEV;
	private int BOARD_RE_SEQ;
	private int BOARD_READCOUNT;
	private String BOARD_DATE;

}
