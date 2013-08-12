<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/stylesheets/slipp.css" rel="stylesheet">
<title>SLiPP :: Q&A 새글쓰기</title>
<%@ include file="../commons/_header.jspf"%>
</head>

<body>
	<%@ include file="../commons/_top.jspf"%>

	<center>
		<div class="wrapper">
			<div class="content">
				<div class="container">

					<section class="write-content">
					<div class="content-main">

						<c:if test="${board.id == 0}">
						<h1 class="write-title">새 글 작성</h1>
						<c:set var="actionUrl" value="/board/list" />
						</c:if>
						
						<c:if test="${board.id != 0 && board.id == board.originNo}">
						<h1 class="write-title">글 수정</h1>
						<c:set var="actionUrl" value="/board/update" />
						</c:if>
						
						<c:if test="${board.id != 0 && board.id != board.originNo}">
						<h1 class="write-title">댓글 수정</h1>
						<c:set var="actionUrl" value="/board/update" />
						</c:if>

						<form id="board" class="form-write" action="${actionUrl}" method="POST">
							<input id="id" name="id" type="hidden" value="${board.id }">
							<input id="originNo" name="originNo" type="hidden" value="${board.originNo}"/>
							<input id="name" name="name" type="hidden" value="${loginUser.name }">
							<fieldset>
							<c:if test="${not (board.id != 0 && board.id != board.originNo)}">
								<div>
									<input id="subject" name="subject" class="inp-title" placeholder="제목" type="text" value="${board.subject }" size="50" />
								</div>
							</c:if>
								<div>
									<textarea id="content" name="content" rows="15" cols="80">${board.content }</textarea>
								</div>
							<c:if test="${not (board.id != 0 && board.id != board.originNo)}">
								<div>
									<input id="tag" name="tag" class="inp-title" placeholder="태그 - 쉼표로 구분 ex) 임,성순" type="text" value="${board.tag }" size="50" />
								</div>
							</c:if>
								<div class="submit-write">
									<button type="submit" class="btn-submit">작성완료</button>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="content-sub"></div>
				</div>
			</div>
		</div>
	</center>
</body>
</html>
