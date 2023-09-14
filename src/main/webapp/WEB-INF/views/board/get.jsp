<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/WEB-INF/views/includes/header.jsp"%>

<script type="text/javascript">
console.log("${cri}");
console.log("${cri.pageNum}");
console.log("${cri.amount}");

$(document).ready(function() {

	var operForm = $("#operForm");

	$("button[data-oper='modify']").on("click", function(e) {
		operForm.attr("action", "/aisu/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e) {
		operForm.find("#bno").remove();
// 		operForm.append("<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>");
// 		operForm.append("<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>");
		
		operForm.attr("action", "/aisu/board/list").submit();
	});
});
</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<div class="panel-body">
			
				<div class="form-group">
					<label>글번호</label> <input class="form-control" name="bno" value="<c:out value='${board.bno }'/>" readonly="readonly">
				</div>
				<div class="form-group">
					<label>제목</label> <input class="form-control" name="title" value="<c:out value='${board.title }'/>" readonly="readonly">
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value='${board.content }'/></textarea>
				</div>
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name="writer" value="<c:out value='${board.writer }'/>" readonly="readonly">
				</div>
<%-- 				<button data-oper="modify" class="btn btn-default" onclick="location.href='/aisu/board/modify?asdf=<c:out value="${board.bno }"/>'">수정하기</button> --%>
<!-- 				<button data-oper="list" class="btn btn-info" onclick="location.href='/aisu/board/list'">목록으로 돌아가기</button> -->
				<button data-oper="modify" class="btn btn-default">수정하기</button>
				<button data-oper="list" class="btn btn-info">목록으로 돌아가기</button>
				
				<form id="operForm" action="/aisu/board/modify" method="get">
					<input type="hidden" id="bno" name="asdf" value="<c:out value='${board.bno }'/>">
					<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
					<input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp"%>