<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Search for Players</h1>
    </div>
</section>

<section class="bg-light1 pt-5 pb-5">
    <div class="container">
        <form action="/player/search" class="mb-0">
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="mb-3">
                        <label for="playerName" class="form-label">Player</label>
                        <input type="text" class="form-control" id="playerName" name="playerName" value="${search}">
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
            <h2 class="text-center">Players Found (${playersKey.size()})</h2>

            <table class="table mt-5">
                <tr>
                    <th>Player Name</th>
                    <th>Team</th>
                    <th>Cost</th>
                    <th>Points</th>
                </tr>
                <%-- TODO - add  row  for player images. Copy what was done for fantasy_team/view--%>
                <c:forEach var="player" items="${playersKey}">
                    <tr>
                        <td>${player.playerName}</td>
                        <td>${teamsKey.teamName}</td>
                        <td>${player.cost}</td>
                        <td>${player.points}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</c:if>

<jsp:include page="../include/footer.jsp"/>