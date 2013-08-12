<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/stylesheets/slipp.css" rel="stylesheet">
<title>SLiPP :: Q&A</title>
<%@ include file="../commons/_header.jspf"%>
</head>

<body>
	<%@ include file="../commons/_top.jspf"%>

	<div class="wrapper">

		<div class="content">
			<div class="container">
				<div class="list-content">
					<div class="content-main">
						<section class="qna-list"> 
						
						<c:if test="${empty selectTag}">
						<h1>글목록</h1>
						</c:if>

						<c:if test="${not empty selectTag}">
						<header>
							<h1> ${selectTag } <small>: ${count}개의 글</small></h1>
						</header>
						</c:if>
						

						<ul class="list">
						
						<c:forEach items="${boards}" var="board">
							<li>
								<div class="wrap">
									<div class="main">
										<strong class="subject"> <a href="/board/${board.id}">${board.subject}</a></strong>

											<div class="tags">
												<span class="tag-list">
													<span class="tag">${board.tag }</span>
												</span>
											</div>

											<div class="auth-info">
											<span class="type">${board.name}</span>
											<span class="time">${board.date}</span> 
										</div>
										
									</div>
								</div>
							</li>
						</c:forEach>

						</ul>
						</section>
					</div>

					<div class="content-sub">

						<section class="qna-tags">
						<h1>태그목록</h1>
						<ul>
								<li><a href="/board/list" class="tag">전체보기</a></li>
							</ul>
						<c:forEach items="${tags}" var="tag">
							<ul>
								<li><a href="/board/tag/${tag.key}" class="tag">${tag.key}<span class="count">${tag.value}</span></a></li>
							</ul>
						</c:forEach>
						</section>

					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
