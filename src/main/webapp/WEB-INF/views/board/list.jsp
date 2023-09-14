<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <%@ include file="../includes/header.jsp"%> --%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>

<script type="text/javascript">
	console.log("${result}");
	console.log("${pageNum}");
	console.log("${amount}");
	console.log("${cri}");
	console.log("${cri.pageNum}");
	console.log("${cri.amount}");

	$(document).ready(function() {
		var result = '<c:out value="${result}"/>';
		checkModal(result);
		
		// 뒤로가기 history 이슈를 해결하기 위한 2줄짜리 코드
		history.replaceState({}, null, null); // (매개변수는 data, title, url 순서) history를 clear하는 작업
		console.log("히스토리 상태 = " + history.state);
		
		function checkModal(result) {
// 			if (result === "") {
// 				return;
// 			}

			// 뒤로가기 history 이슈를 해결하기 if 조건 변경
			if (result === "" || history.state ) {
				return;
			}
			
			if (result === "success") {
				$(".modal-body").html("정상적으로 처리되었습니다.");
			} else if (parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click", function() {
			self.location="/aisu/board/register";
		});
		
		var actionForm = $("#actionForm"); 
		
		$(".paginate_button a").on("click", function(e) {
			e.preventDefault();
			console.log("click");
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		$(".move").on("click", function(e) {
			e.preventDefault();
// 			actionForm.append("<  input type='hidden' name='asdf' value=' "+$(this).attr('href')+" '  >").val($(this).attr("href"));
			actionForm.append("<input type='hidden' name='asdf' value='"+$(this).attr('href')+"'>").val($(this).attr("href"));
			actionForm.attr("action", "/aisu/board/get");
			actionForm.submit();
		});
		
		
		
	});
</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				글 목록
				<button id="regBtn" type="button" class="btn btn-xs pull-right">새 글 작성</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<!-- <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example"> -->
				<table width="100%" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="sorting sorting_asc">글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<c:forEach var="board" items="${list }" varStatus="status">
						<tr>
							<td><c:out value="${board.bno }" /></td>
							<!-- 새 탭에서 조회하고 싶을 경우: <a target="_blank"> 속성 이용 -->
<%-- 							<td><a href="/aisu/board/get?asdf=<c:out value='${board.bno }' />"><c:out value="${board.title }" /></a></td> --%>
							<td><a class="move" href="<c:out value='${board.bno }' />"><c:out value="${board.title }" /></a></td>
							<td><c:out value="${board.writer }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }" /></td>
						</tr>
					</c:forEach>
				</table>

				<!-- Pagination -->
				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous"><a href="${pageMaker.startPage -1 }"><c:out value="Prev"/></a></li>
						</c:if>
						
						<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
							<li class="paginate_button ${pageMaker.cri.pageNum == num ? 'active':'' }"><a href="${num }"><c:out value="${num }"/></a></li>
						</c:forEach>
						
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next"><a href="${pageMaker.endPage +1 }"><c:out value="Next"/></a></li>
						</c:if>
					</ul>
				</div>
				<form id="actionForm" action="/aisu/board/list" method="get">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
					<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
				</form>
				
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal title</h4>
							</div>
							<div class="modal-body"></div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<style>
#regBtn {
	border: 1px solid black;
}
</style>

<%@ include file="../includes/footer.jsp"%>