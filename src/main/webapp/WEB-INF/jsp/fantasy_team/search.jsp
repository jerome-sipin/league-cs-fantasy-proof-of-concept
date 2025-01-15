<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Search for Fantasy Teams</h1>
    </div>
</section>

<section class="bg-light1 pt-5 pb-5">
    <div class="container">
        <form action="/fantasy_team/search" class="mb-0">
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="mb-3">
                        <label for="teamName" class="form-label">Team Name</label>
                        <input type="text" class="form-control" id="teamName" name="teamName" value="${search}">
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
            <h2 class="text-center">Teams Found (${teamsKey.size()})</h2>

            <table class="table mt-5">
                <tr>
                    <th>User</th>
                    <th>Team Name</th>
                    <th>Points</th>
                    <th></th>
                </tr>
                <c:forEach var="team" items="${teamsKey}">
                    <tr>
                        <td>${team.userId}</td>
                        <td>${team.teamName}</td>
                        <td>${team.points}</td>
                        <td><a href="/fantasy_team/view/${fantasy_team.id}" >View</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</c:if>

<jsp:include page="../include/footer.jsp"/>