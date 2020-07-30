<#macro login path isRegisteredForm>

<form action="${path}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="User name" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password :</label>
        <div class="col-sm-6">
            <input type="text" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <button class="btn btn-primary" type="submit"><#if isRegisteredForm>Create<#else>Sign in</#if></button>
</form>

</#macro>