<#import "common.ftl" as c>
<#include "security.ftl">

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
    <form method="get" action="filter" class="form-inline">
        <input type="text" class="form-control" name="filter"  placeholder="Search by tag">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary ml-2">Search</button>
    </form>
        </div>
    </div>

    <a class="btn btn-primary mb-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new message
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post"  enctype="multipart/form-data">
                <div class="form-group">
                        <input type="text" class="form-control ${(tagError??)?string('is-invalid', '')}"
                               value= "<#if message??>${message.tag}</#if>" name="tag" placeholder="Insert tag">
                    <#if tagError??>
                        <div class="invalid-feedback">
                           ${tagError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value= "<#if message??>${message.text}</#if>" name="text" placeholder="Insert text">
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="file" class="form-control"
                           name="file">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Save message</button>
                </div>

            </form>
        </div>
    </div>


    <div class="mb-3">List of messages</div>
    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <div class="m-2">
                    <div>
                        <#if message.filename??>
                            <img src="/img/${message.filename}" width="120" height="100">
                        </#if>
                    </div>
                    <span>${message.text}</span><br>
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    <a href="/user-messages/${message.author.id}">${message.authorName}</a>
                    <#if message.author.id == currentUserId>
                    <a class="btn btn-primary " href="/user-messages/${message.author.id}?message=${message.id}">
                    Edit
                    </a>
                    </#if>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@c.page>