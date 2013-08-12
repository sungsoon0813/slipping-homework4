package net.slipp.dao.board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.slipp.domain.board.Board;

import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDao {

	// 게시물의 Unique ID
	private static int uniqueId = 0;
	// 게시물들을 저장하는 ArrayList
	private static List<Board> boardStorage = new ArrayList<Board>();
	// 태그를 저장하는 TagStorage
	private static Map<String, Integer> tagStorage = new HashMap<String, Integer>();

	/**
	 * boardStorage에서 해당 글의 uniqueID와 원글ID가 동일한 것을 글목록으로 return
	 */
	public List<Board> getBoardList() {

		List<Board> board = new ArrayList<Board>();  

		for (int i = 0; i < boardStorage.size(); i++) {

			// 원글인 게시물만 추가
			if (checkOriginBoard(i))
				board.add(boardStorage.get(i));

		}

		return board;
	}

	public void boardInsert(Board board) {
		// 게시글 추가
		board.setId(++uniqueId);
		board.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date()));

		if (board.getOriginNo() == 0)
			board.setOriginNo(uniqueId);

		boardStorage.add(board);

		// 없는 태그 추가
		if (board.getTag() != null)
			tagInsert(board.getTag());
	}

	public Map<String, Integer> getTagList() {
		return tagStorage;
	}

	private void tagInsert(String tagString) {

		if (tagString == null || tagString == "")
			return;

		// 태그 목록 관리
		String[] tagArr = tagSplit(tagString);

		for (String tag : tagArr) {
			tag = tag.trim();

			// 있는 태그이면 count + 1
			if (tagStorage.containsKey(tag)) {
				int count = tagStorage.get(tag);
				tagStorage.put(tag, count+1);
			}
			else 
				tagStorage.put(tag, 1);

		}

	}

	private String[] tagSplit(String tag) {
		return tag.split(",");
	}

	public Board getBoard(int id) {

		for (Board board : boardStorage) {
			if (board.getId() == id)
				return board;
		}

		return null;
	}

	public List<Board> getReplyList(int id) {

		List<Board> replyList = new ArrayList<Board>();

		for (Board board : boardStorage) {
			if (board.getId() != board.getOriginNo() && board.getOriginNo() == id)
				replyList.add(board);
		}

		return replyList;
	}

	public List<Board> getBoardByTag(String tag) {

		List<Board> board = new ArrayList<Board>();  

		for (int i = 0; i < boardStorage.size(); i++) {

			// 기존의 글 중 선택된 태그를 포함하고 있으면서 원글인 것을 리스트에 추가
			if (checkOriginBoard(i) && isExistTag(boardStorage.get(i).getTag(), tag))
				board.add(boardStorage.get(i));

		}

		return board;
	}

	// 해당 게시물이 원글인지 리플인지 확인
	private boolean checkOriginBoard(int i)
	{
		// 원글
		if (boardStorage.get(i).getId() == boardStorage.get(i).getOriginNo())
			return true;

		// 리플
		return false;
	}

	// 해당 게시물이 선택된 필드를 포함하는지 확인
	private boolean isExistTag(String boardTag, String compareTag)
	{
		compareTag = compareTag.trim();

		String[] tagArr = boardTag.split(",");

		for (String tag : tagArr) {
			tag = tag.trim();

			// 같은 태그가 있으면 리턴
			if (tag.equals(compareTag))
				return true;

		}

		return false;

	}

	public void update(Board board) {
		// 데이터 업데이트
		Board originBoard = getBoard(board.getId());
		originBoard.setSubject(board.getSubject());
		originBoard.setContent(board.getContent());
		
		if (board.getTag() != null) {
			// 기존 태그 삭제
			tagDelete(originBoard.getTag());
			// 새로운 태그 추가
			tagInsert(board.getTag());
			originBoard.setTag(board.getTag());
		}
		
	}
	
	private void tagDelete(String tagString)
	{
		// tag update
		if (tagString == null || tagString == "")
			return;
		
		String[] tagArr = tagString.split(",");

		// 기존 태그 삭제
		for (String tag : tagArr) {
			tag = tag.trim();
			
			int count = tagStorage.get(tag);

			if (count == 1)
				tagStorage.remove(tag);

			else
				tagStorage.put(tag, count-1);

		}
	}


}
