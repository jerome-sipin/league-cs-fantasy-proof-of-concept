<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Search for Users</h1>
    </div>
</section>

<section class="bg-light1 pt-5 pb-5">
    <div class="container">
        <form action="/user/search" class="mb-0">
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="mb-3">
                        <label for="username" class="form-label">User</label>
                        <input type="text" class="form-control" id="username" name="username" value="${search}">
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6">
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </div>
        </form>
    </div>
</section>

<c:if test="${not empty search}">
    <section class="bg-light2 pt-5 pb-5">
        <div class="container">
            <h2 class="text-center">Users Found (${usersKey.size()})</h2>

            <table class="table mt-5">
                <tr>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="user" items="${usersKey}">
                    <tr>
                        <td>${user.username}</td>
                        <td><a href="/fantasy_team/view/${user.id}" >View User's Team</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</c:if>

<jsp:include page="../include/footer.jsp"/>