<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">${teamName}</h1>
    </div>
</section>

<section class="bg-light1 pt-5 pb-5">

    <!-- Red buttons indicate you can remove from team -->

    <div class="container">
        <h3 class="text-center">Remaining Budget: $${budget}</h3>
    </div>
    <div class="container">
        <div><b>Your Team</b></div>
        <table class="table mt-5 text-center">
            <tr>
                <th>Player 1</th>
                <th>Player 2</th>
                <th>Player 3</th>
                <th>Player 4</th>
                <th>Player 5</th>
            </tr>
            <!-- TODO - Ask Eric about this. This doesn't seem efficient. -->
            <tr>
                <c:forEach var="player" items="${currentTeamPlayersKey}">
                    <td>
                        <img src="${player.imageUrl}" alt="${player.playerName}-image"/>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="player" items="${currentTeamPlayersKey}">
                        <td>${player.playerName}</td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="player" items="${currentTeamPlayersKey}">
                    <td>
                        <form action="/fantasy_team/dropPlayer/${player.id}" method="post">
                            <button id="fantasy-team-${player.id}" type="submit" class="btn btn-danger">$${player.cost}</button>
                        </form>
                    </td>
                </c:forEach>
            </tr>
        </table>
    </div>

    <div class="container">
        <div class="row pt-5 pb-5 justify-content-center">
            <div class="col-6">
                <div class="row justify-content-center">
                    <div class="col-6">
                        <div class="row justify-content-center">
                            <div class="col-6">
                                <button id="submit" name="submit" class="btn btn-primary" value="1">Save Changes</button>
                            </div>
                            <div class="col-6">
                                <a href="" id="cancel" name="cancel" class="btn btn-danger">Cancel</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Tables similar to fantasy team view screen. Each team has ITS OWN SECTION, displaying players,
and underneath, a button with their price. This buttons adds the player to the team-->
<c:forEach var="team" items="${realTeamsKey}" varStatus="status">
    <section class="bg-light1 pt-5 pb-5">
        <div class="container">
            <div><b>${team.teamName}</b></div>
            <table class="table mt-5 text-center">
                <tr>
                    <th>Player 1</th>
                    <th>Player 2</th>
                    <th>Player 3</th>
                    <th>Player 4</th>
                    <th>Player 5</th>
                </tr>
                <!-- TODO - Ask Eric about this. This doesn't seem efficient. -->
                <tr>
                    <c:forEach var="player" items="${playersKey}">
                        <c:if test="${player.teamActualId == team.id}">
                            <td>
                                <img src="${player.imageUrl}" alt="${player.playerName}-image"/>
                            </td>
                        </c:if>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach var="player" items="${playersKey}">
                        <c:if test="${player.teamActualId == team.id}">
                            <td>${player.playerName}</td>
                        </c:if>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach var="player" items="${playersKey}">
                        <c:if test="${player.teamActualId == team.id}">
                            <td>
                                <form action="/fantasy_team/addPlayer/${player.id}" method="post">
                                    <button id="${player.id}"
                                            type="submit" class="btn btn-success">$${player.cost}</button>
                                </form>
                            </td>
                        </c:if>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </section>
</c:forEach>


<jsp:include page="../include/footer.jsp"/>