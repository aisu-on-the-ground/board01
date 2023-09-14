<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/WEB-INF/views/includes/header.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		var formObj = $("form");

		$("button").on("click", function(e) {
			
			e.preventDefault();

			var operation = $(this).data("oper");
			
			console.log(operation);
			alert(operation);

			if (operation === "list") {
				formObj.attr("action", "/aisu/board/list").attr("method", "get");
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				// => formObj.append("<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>");
				
				
				formObj.empty(); //<form>태그 안의 몯느 내용을 삭제
				formObj.append(pageNumTag);
				formObj.append(amountTag);
// 				self.location = "/aisu/board/list";
// 				return;
			} else if (operation === "remove") {
				formObj.attr("action", "/aisu/board/remove").attr("method", "post");
			} else if (operation === "modify") {
				formObj.attr("action", "/aisu/board/modify").attr("method", "post");
			}
			formObj.submit();
		});
	});
</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Modify</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify Page</div>
			<div class="panel-body">


				<form role="form" action="/aisu/board/modify" method="post">
					<div class="form-group">
						<label>글번호</label> 
						<input class="form-control" name="bno" value="<c:out value='${board.bno }'/>" readonly="readonly">
					</div>
					<div class="form-group">
						<label>제목</label> 
						<input class="form-control" name="title" value="<c:out value='${board.title }'/>">
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="3" name="content"><c:out value='${board.content }' /></textarea>
					</div>
					<div class="form-group">
						<label>작성자</label> 
						<input class="form-control" name="writer" value="<c:out value='${board.writer }'/>">
					</div>
					
					<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
					<input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
					
					<!-- 작성일과 작성자는 modify에 필요한 파라미터가 아니므로 보내지 않는다. (name 속성을 삭제하거나, <input>이 아닌 다른 태그로 화면에 보여줄 것.) -->
					<div class="form-group">
						<label>작성일</label>
						<input class="form-control" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${board.regdate }' />" readonly="readonly">
					</div>
					<div class="form-group">
						<label>수정일</label> 
						<input class="form-control" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${board.updateDate }' />" readonly="readonly">
					</div>
					<button type="button" data-oper="modify" class="btn btn-default">수정</button>
					<button type="button" data-oper="remove" class="btn btn-danger">삭제</button>
					<button type="button" data-oper="list" class="btn btn-info">목록으로 돌아가기</button>
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp"%>