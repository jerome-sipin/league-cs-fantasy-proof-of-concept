<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">${teamName}</h1>
    </div>
</section>

<!-- TODO - Each TODO is its own section -->

<!-- TODO - Have a form over all of these items down to the submit button. -->

<!-- TODO - A text form for the team name.-->
<section class="bg-light1 pt-5 pb-5">


    <!-- TODO - Number showing remaining budget. Prob controlled with Javascript. -->
    <!-- TODO - Underneath is a table that shows current lineup. Buttons underneath each that says "remove from lineup" -->
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
                    <td>Placeholder for image</td>
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
                            <td>Placeholder for image!</td>
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

<script>

    // TODO
    // https://stackoverflow.com/questions/46587302/spring-boot-delete-database-entry-after-clicking-button
    // Code from this stack overflow thread was used to allow the controller to delete the record from the database.

</script>

<jsp:include page="../include/footer.jsp"/>