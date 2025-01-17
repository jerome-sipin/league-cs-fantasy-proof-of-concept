<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Leaderboard</h1>
    </div>
</section>

<%--TODO - Put into two columns so that there is separation between them. --%>
<section class="bg-light2">
    <div class="container">
        <div class="row pt-5 pb-5 justify-content-center">
            <div class="col-6">
<%--                <div><button type="button" class="btn btn-primary btn-lg">View Top Players</button></div>--%>
<%--                <br>--%>
<%--                <div><button type="button" class="btn btn-primary btn-lg">View Top Fantasy Teams</button></div>--%>

                <div class="row justify-content-center">
                    <div class="col-6">
                        <button type="button" class="btn btn-primary btn-lg">View Top Players</button>
                    </div>
                    <div class="col-6">
                        <button type="button" class="btn btn-primary btn-lg">View Top Fantasy Teams</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>

<%--TODO - Or maybe instead of two views, have two sections blocked by if statements.
    TODO   When one of these buttons is pressed, the other is deactivated (this is important).
    TODO   Then, show the data for the selected button below. --%>



<jsp:include page="include/footer.jsp"/>