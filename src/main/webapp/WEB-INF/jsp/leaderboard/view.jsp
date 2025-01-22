<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<style>
    #topPlayersDiv {
        display: none;
    }
    #topTeamsDiv {
        display: none;
    }

    .btn-primary {
        padding: 2rem;
        width: 200px;
        height: 160px;

        display: flex;
        align-items: center;
        justify-content: center;
    }
</style>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Leaderboard</h1>
    </div>
</section>

<%--TODO - Put into two columns so that there is separation between them. --%>
<section class="bg-light2">
    <div class="container">
        <div class="row pt-5 pb-5 justify-content-center">
            <div class="col-6 align-items-center text-center">
                <div class="row">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary btn-lg btn-block" id="topPlayers" name="topPlayers">View Top Players</button>
                        <button type="button" class="btn btn-primary btn-lg btn-block" id="topTeams" name="topTeams">View Top Fantasy Teams</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%--TODO - Or maybe instead of two views, have two sections blocked by if statements.
    TODO   When one of these buttons is pressed, the other is deactivated (this is important).
    TODO   Then, show the data for the selected button below. --%>

<!-- If top players button clicked... show top players -->

<section class="bg-light2 pb-5 justify-content-center">
    <div class="container" id="topPlayersDiv">
        <div class="row pb-5 justify-content-center">
            <div class="col-6 align-items-center text-center">
                <table class="table mt-5 text-center">
                    <tr>
                        <th>Player Name</th>
                        <th>Team</th>
                        <th>Cost</th>
                        <th>Points</th>
                    </tr>
                    <c:forEach var="player" items="${playersKey}" varStatus="status">
                        <tr>
                            <td>${player.playerName}</td>
                            <td>${realTeamsKey.get(status.index).teamName}</td>
                            <td>${player.cost}</td>
                            <td>${player.points}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>

<section class="bg-light2 pb-5 justify-content-center">
    <div class="container" id="topTeamsDiv">
        <div class="row pb-5 justify-content-center">
            <div class="col-6 align-items-center text-center">
                <table class="table mt-5 text-center">
                    <tr>
                        <th>User</th>
                        <th>Team Name</th>
                        <th>Points</th>
                        <th></th>
                    </tr>
                    <c:forEach var="team" items="${teamsKey}" varStatus="status">
                        <tr>
                            <td>${usersKey.get(status.index).username}</td>
                            <td>${team.teamName}</td>
                            <td>${team.points}</td>
                            <td><a href="/fantasy_team/view/${team.id}" >View</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>

<!-- If top teams button clicked... show top teams -->
<script>

    $("#topPlayers").on('click', function(){
        console.log("This button has been clicked");
        $("#topPlayersDiv").show();
        $("#topTeamsDiv").hide();
    });

    $("#topTeams").on('click', function(){
        console.log("This button has been clicked");
        $("#topPlayersDiv").hide();
        $("#topTeamsDiv").show();
    });

</script>


<jsp:include page="../include/footer.jsp"/>