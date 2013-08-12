package net.slipp.web.board;

import java.util.List;

import net.slipp.domain.board.Board;
import net.slipp.service.board.BoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;

	@RequestMapping("/list")
	public String boardList(Model model) throws Exception {
		model.addAttribute("boards", boardService.getBoardList());
		model.addAttribute("tags", boardService.getTagList());
		return "board/list";
	}

	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String addBoard(Board board, Model model) throws Exception {
		boardService.addBoard(board);
		
		model.addAttribute("boards", boardService.getBoardList());
		model.addAttribute("tags", boardService.getTagList());
		return "board/list";
	}
	
	@RequestMapping("/form")
	public String joinForm(Model model) throws Exception {
		model.addAttribute("board", new Board());
		return "board/form";
	}
	
	@RequestMapping("/{id}")
	public String getBoard(@PathVariable String id, Model model) throws Exception {
		Board board = boardService.findByBoardId(Integer.parseInt(id));
		model.addAttribute("board", board);
		
		List<Board> replyList = boardService.findReply(Integer.parseInt(id));
		model.addAttribute("replyList", replyList);
		
		return "board/board";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String addReply(Board board, @PathVariable String id, Model model) throws Exception {
		boardService.addBoard(board);
		
		Board originBoard = boardService.findByBoardId(Integer.parseInt(id));
		model.addAttribute("board", originBoard);
		
		List<Board> replyList = boardService.findReply(Integer.parseInt(id));
		model.addAttribute("replyList", replyList);
		
		return "board/board";
	}
	
	@RequestMapping("/tag/{tag}")
	public String getTagBoard(@PathVariable String tag, Model model) throws Exception {
		List<Board> boardByTag = boardService.findByBoardTag(tag);		
		model.addAttribute("boards", boardByTag);
		model.addAttribute("tags", boardService.getTagList());
		model.addAttribute("selectTag", tag);
		model.addAttribute("count", boardByTag.size());
		return "board/list";
	}
	
	@RequestMapping("/update")
	public String update(Board board, Model model) throws Exception {
		boardService.update(board);
		
		Board originBoard = boardService.findByBoardId(board.getOriginNo());
		model.addAttribute("board", originBoard);
		
		List<Board> replyList = boardService.findReply(board.getOriginNo());
		model.addAttribute("replyList", replyList);
		
		return "board/board";
	}
	
	@RequestMapping("/update/{id}")
	public String updateForm(@PathVariable String id, Model model) throws Exception {
		Board board = boardService.findByBoardId(Integer.parseInt(id));
		model.addAttribute("board", board);
		return "board/form";
	}
	
}
