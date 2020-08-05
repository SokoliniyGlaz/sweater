<#macro login path isRegisteredForm>

<form action="${path}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username"  class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   value="<#if user??>${user.username}</#if>"   placeholder="User name" />
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password :</label>
        <div class="col-sm-6">
            <input type="text" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password" />
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <#if isRegisteredForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Confirm password :</label>
        <div class="col-sm-6">
            <input type="text" name="password2"  class="form-control ${(password2Error??)?string('is-invalid', '')}"
                      placeholder="Type password" />
            <#if password2Error??>
                <div class="invalid-feedback">
                    ${password2Error}
                </div>
            </#if>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email :</label>
        <div class="col-sm-6">
            <input type="email" name="email" value="<#if user??>${user.email}</#if>" class="form-control ${(emailError??)?string('is-invalid', '')}" placeholder="some@some.com" />
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
        </#if>
    </div>
    <button class="btn btn-primary" type="submit"><#if isRegisteredForm>Create<#else>Sign in</#if></button>
</form>
    <div><#if !isRegisteredForm><a href="/registration"  class="btn btn-primary" type="submit">Add new user</a></#if></div>

</#macro>