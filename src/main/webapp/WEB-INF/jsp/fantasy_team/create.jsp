<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Create a Fantasy Team</h1>
    </div>
</section>

<!-- TODO - Each TODO is its own section -->
<!-- TODO - A text form for the team name.-->

<!-- TODO - Number showing remaining budget. Prob controlled with Javascript. -->
<!-- TODO - Underneath is a table that shows current lineup. Buttons underneath each that says "remove from lineup" -->
<!-- Red buttons indicate you can remove from team -->
<section class="bg-light1 pt-5 pb-5">
    <div class="container">
        <h3 class="text-center">Remaining Budget: ${number}</h3>
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
            <tr>
                <td>Placeholder for image!</td>
                <td>Placeholder for image!</td>
                <td>Placeholder for image!</td>
                <td>Placeholder for image!</td>
                <td>Placeholder for image!</td>
            </tr>
            <tr>
                <td><button type="button" class="btn btn-danger">Price</button></td>
                <td><button type="button" class="btn btn-danger">Price</button></td>
                <td><button type="button" class="btn btn-danger">Price</button></td>
                <td><button type="button" class="btn btn-danger">Price</button></td>
                <td><button type="button" class="btn btn-danger">Price</button></td>
            </tr>
        </table>
    </div>
</section>

<!-- TODO - Two buttons - create team (1) and cancel (2) -->
<section class="bg-light1">
    <div class="container">
        <div class="row pt-5 pb-5 justify-content-center">
            <div class="col-6">
                <div class="row justify-content-center">
                    <div class="col-6">
                        <div class="row justify-content-center">
                            <div class="col-6">
                                <button id="submit" name="submit" class="btn btn-primary" value="1">Create Team</button>
                            </div>
                            <div class="col-6">
                                <a href="/fantasy_team/create" id="cancel" name="cancel" class="btn btn-danger">Cancel</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- TODO - Tables similar to fantasy team view screen. Each team has ITS OWN SECTION, displaying
     TODO - players, and underneath, a button with their price. -->
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
                            <td><button id="${player.id}" type="button" class="btn btn-success">$${player.cost}</button></td>
                        </c:if>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </section>
</c:forEach>

<script>

</script>


<jsp:include page="../include/footer.jsp"/>