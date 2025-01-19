<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Create a Fantasy Team</h1>
    </div>
</section>

<!-- TODO - Each TODO is its own section -->

<!-- TODO - Have a form over all of these items down to the submit button. -->

<!-- TODO - A text form for the team name.-->
<section class="bg-light1 pt-5 pb-5">
    <div class="container">
        <form action="/fantasy_team/create" class="mb-0">
            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="mb-3">
                        <label for="teamName" class="form-label">Team Name</label>
                        <input type="text" class="form-control" id="teamName" name="teamName"
                               value="${createdTeamName}">
                    </div>
                </div>
            </div>
        </form>
    </div>
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
                                <a href="/fantasy_team/create" id="cancel" name="cancel"
                                   class="btn btn-danger">Cancel</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<jsp:include page="../include/footer.jsp"/>