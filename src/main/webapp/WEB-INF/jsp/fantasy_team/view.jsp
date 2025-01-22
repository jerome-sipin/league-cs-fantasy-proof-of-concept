<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Overview for ${teamInformationKey.teamName}</h1>
        <br>
        <h3 class="text-center">${teamInformationKey.points} Points</h3>
    </div>
</section>
<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <table class="table mt-5 text-center">
            <tr>
                <th>Player 1</th>
                <th>Player 2</th>
                <th>Player 3</th>
                <th>Player 4</th>
                <th>Player 5</th>
            </tr>
            <tr>
                <c:forEach var="player" items="${playersKey}">
                    <td>
                        <img src="${player.imageUrl}" alt="${player.playerName}-image"/>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach var="player" items="${playersKey}">
                    <td>${player.playerName}</td>
                </c:forEach>
            </tr>
        </table>
    </div>
</section>

<c:if test="${canEdit == true}">
    <section class="bg-light2">
        <div class="container">
            <div class="row pt-5 pb-5 justify-content-center">
                <div class="col-6">
                    <div class="row justify-content-center">
                        <div class="col-6">
                            <div class="row justify-content-center">
                                <div class="col-6">
                                    <a href="/fantasy_team/edit/${teamInformationKey.id}"  class="btn btn-primary">Edit Team</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>

<jsp:include page="../include/footer.jsp"/>