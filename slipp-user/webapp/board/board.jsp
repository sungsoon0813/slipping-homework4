<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<div class="wrapper">
		<div class="content" role="main">
			<div class="container">

				<section class="view-content">
				<h1 class="article-title">${board.subject }</h1>
				<div class="content-main">

					<article class="article">
					<div class="article-header">
					
						<c:if test="${board.name == loginUser.name }">
						<div class="article-header-thumb">
							<a href="/board/update/${board.id}" class="article-author-thumb"><font color="blue">수정</font></a>
						</div>
						</c:if>
						
						
						<div class="article-header-text">
							<font color="blue" class="article-author-name">${board.name}</font>
							<font color="blue" class="article-header-time">${board.date}</font>
						</div>
					</div>
					<div class="article-doc">${board.content}</div>
					</article>

					<div class="qna-comment">
						<c:forEach items="${replyList }" var="reply">
						<article class="article">

							<div class="article-header">

								<c:if test="${reply.name == loginUser.name }">
									<div class="article-header-thumb">
										<a href="/board/update/${reply.id}"
											class="article-author-thumb"><font color="blue">수정</font></a>
									</div>
								</c:if>


								<div class="article-header-text">
									<font color="blue" class="article-author-name">${reply.name}</font>
									<font color="blue" class="article-header-time">${reply.date}</font>
								</div>
							</div>
							
							<div class="article-doc">${reply.content}</div>
						</article>
						</c:forEach>
					</div>


					<c:if test="${not empty loginUser}">
					<form id="answer" class="form-write" action="/board/${board.id}" method="POST">
						<input id="name" name="name" type="hidden" value="${loginUser.name }">
						<input id="originNo" name="originNo" type="hidden" value="${board.id}"/>
						
						<fieldset>
							<legend class="title-write">의견 추가하기</legend>
							<div class="box-write">
								<textarea id="content" name="content" rows="15" cols="80"></textarea>
							</div>
							<div class="submit-write">
								<button type="submit" class="btn-submit">작성완료</button>
							</div>
						</fieldset>
					</form>
					</c:if>

				</div>
				</section>
			</div>
		</div>
	</div>
</body>
</html>