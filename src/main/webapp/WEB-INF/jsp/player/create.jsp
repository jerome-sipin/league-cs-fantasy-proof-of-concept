<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Create a Player</h1>
    </div>
</section>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <form action="player/createSubmit", method="post">

            <div class="mt-3 row justify-content-center">
                <label for="playerName" class="col-sm-2 col-form-label">Player Name</label>
                <div class="col-sm-10 col-lg-6">
                    <input type="text" class="form-control" id="playerName" name="playerName" value="">
                </div>
            </div>

            <div class="mt-3 row justify-content-center">
                <label for="actualTeamId" class="col-sm-2 col-form-label">Team</label>
                <div class="col-sm-10 col-lg-6">
                    <select name="actualTeamId" id="actualTeamId" class="form-control">
                        <option value=""></option>
                        <c:forEach var="team" items="${teamsKey}">
                            <option value="${team.id}">${team.teamName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="mt-3 row justify-content-center">
                <label for="playerUrl" class="col-sm-2 col-form-label">Player Image URL</label>
                <div class="col-sm-10 col-lg-6">
                    <input type="text" class="form-control" id="playerUrl" name="playerUrl" value="">
                </div>
            </div>

            <div class="mt-3 row justify-content-center">
                <div class="col-sm-12 col-lg-8">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>

        </form>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>