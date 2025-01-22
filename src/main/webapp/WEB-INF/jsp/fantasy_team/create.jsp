<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Create a Fantasy Team</h1>
    </div>
</section>

<style>
    .form_input_error {
        color:red;
    }
</style>

<section class="bg-light1 pt-5 pb-5">
    <div class="container">
        <form action="/fantasy_team/createSubmit" method="post">

            <div class="row justify-content-center">
                <div class="col-6">
                    <div class="mb-3">
                        <label for="teamName" class="form-label">Team Name</label>
                        <input type="text" class="form-control" id="teamName" name="teamName" value="">
                    </div>
                </div>
            </div>
            <c:if test="${bindingResult.hasFieldErrors('teamName')}">
                <div class="row justify-content-center">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-10 col-lg-6">
                        <c:forEach var="error" items="${bindingResult.getFieldErrors('teamName')}">
                            <dd class="mb-0 form_input_error">${error.getDefaultMessage()}</dd>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <div class="row justify-content-center">
                <div class="col-3">
                    <div class="row justify-content-center">
                        <div class="col-6">
                            <button id="submit" name="submit" class="btn btn-primary">Create Team</button>
                        </div>
                        <div class="col-6">
                            <a href="/fantasy_team/create" id="cancel" name="cancel" class="btn btn-danger">Cancel</a>
                        </div>
                    </div>
                </div>
            </div>

        </form>
    </div>

</section>


<jsp:include page="../include/footer.jsp"/>