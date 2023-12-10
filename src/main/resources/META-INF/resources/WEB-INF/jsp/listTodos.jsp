<%@include file="common/header.jspf"  %>
<%@include file="common/navigation.jspf"  %>
<div class="container">

<div>Hi ${name}</div>
<hr>
<h1> Here is your Todo List</h1>

<table class="table">
	<thead>
		<tr>
			<th>Description&nbsp;</th>
			<th>Target Date&nbsp;</th>
			<th>Is completed?</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${todos}" var="todo">
			<tr>
				<td> ${todo.description}&nbsp; </td>
				<td> ${todo.targetDate}&nbsp; </td>
				<td> ${todo.done} </td>
				<td><a href = "delete-todo?id=${todo.id}"class="btn btn-danger"> Delete </a></td>
				<td><a href = "update-todo?id=${todo.id}"class="btn btn-warning"> Update </a></td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>
<a href="add-todo" class="btn btn-success">Add Todo</a>
<%@include file="common/footer.jspf"  %>