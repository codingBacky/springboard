package kr.co.backy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.backy.domain.BoardBean;
import kr.co.backy.domain.PageDTO;
import kr.co.backy.service.BoardService;

@Controller
public class BoardController {
	//항상 service를 참조
	
	@Autowired
	//이 인터페이스를 구현하고 있는 것중 @service 가 있으면 주입을 받는다.
	private BoardService service;
	
	@RequestMapping(value = "/BoardList.bo")
	public String boardList(HttpServletRequest request, Model model) {
		
		int boardCount = service.boardCount();
		PageDTO paging = service.pageCalcu(request, boardCount);
		List<BoardBean> list = service.boardList(paging.getStartNum(), paging.getEndNum());//1-10까지의 자료를 보내주세요
		
		model.addAttribute("paging", paging); 
		model.addAttribute("boardCount",boardCount);
		model.addAttribute("list", list);
		return "/board/qna_board_list";
	}
	
	@RequestMapping(value = "/BoardWrite.bo")
	public String boardWrite() {
		//보안을 강조가는 WEB-INF 하위에 있기 때문에 controller 내부에서 연결해주어야 동작함
		return "redirect:/BoardWriteForm.bo";
	}
	@RequestMapping(value = "/BoardWriteForm.bo")
	public String boardWriteForm() {
		//controller 내부 연결
		return "/board/qna_board_write";
	}
	
	@PostMapping(value = "/BoardAddAction.bo")
	public String boardAdd(BoardBean bean) {
		int result = service.boardAdd(bean);
		if( result == 0 ) {
			System.out.println("입력실패");
			return null;
		}
		return "redirect:/BoardList.bo";
	}
	@RequestMapping(value = "/BoardDetailAction.bo")
	public String boardDetail(int num, Model model) {
	//넘겨받은 num값으로 조회를 한 후에 해당 결과를 view 페이지에 넘겨준다.
		model.addAttribute("board", service.boardDetail(num));
		return "/board/qna_board_view";
	}
//여기서부터 만들어봄
	@RequestMapping(value = "/BoardReplyView.bo")
	public String boardReplyView(int num, Model model) {
		model.addAttribute("board", service.boardReplyView(num));
		return "board/qna_board_reply";
	}
	@RequestMapping(value = "/BoardReplyAction.bo")
	public String boardReplyAction(BoardBean board) {
		int result = service.boardReplyAction(board);
		return "redirect:/BoardDetailAction.bo?num="+result ;
	}
	@RequestMapping(value = "/BoardReplyForm.bo")
	public String boardReplyForm() {
		return "/board/qna_board_reply";
	}
	
	@RequestMapping(value = "/BoardModify.bo")
	public String boardModify( int num, Model model) {
		model.addAttribute("board", service.boardModify(num));
		return "/board/qna_board_modify";
	}
	@RequestMapping(value = "/BoardModifyUserCheck.bo")
	public String BoardModifyUserCheck(HttpServletRequest request, int num) {
		System.out.println("=================================");
		System.out.println(num);
		boolean result = (request.getParameter("BOARD_PASS") == service.userCheck(num))?true:false;
		if( result == false ) {
			System.out.println("입력실패");
			return null;
		}
		return  "/BoardModifyUserCheck.bo";
	}
	
	@RequestMapping(value = "/BoardModifyAction.bo")
	public String boardModifyAction(BoardBean board, int num) {
		System.out.println("------------------------------------------");
		System.out.println(num);
		service.boardModifyAction(board);
		return "/BoardDetailAction.bo?num=" + num;
	}
	@RequestMapping(value = "/BoardDeleteAction.bo")
	public String boardDeleteAction(int num) {
		service.boardDeleteAction(num);
		return "/BoardList.bo";
	}
	
}

