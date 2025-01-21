<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../include/header.jsp"/>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <h1 class="text-center">Create a Real Team</h1>
    </div>
</section>

<section class="bg-light2 pt-5 pb-5">
    <div class="container">
        <form action="real_team/createSubmit", method="post">

            <div class="mt-3 row justify-content-center">
                <label for="teamName" class="col-sm-2 col-form-label">Team Name</label>
                <div class="col-sm-10 col-lg-6">
                    <input type="text" class="form-control" id="teamName" name="teamName" value="">
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