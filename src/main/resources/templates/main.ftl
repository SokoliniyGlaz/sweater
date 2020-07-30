<#import "common.ftl" as c>

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
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/main" enctype="multipart/form-data">
                <div class="form-group">
                        <input type="text" class="form-control"
                                name="tag" placeholder="Insert tag">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control"
                            name="text" placeholder="Insert text">
                </div>
                <div class="form-group">
                    <input type="file" class="form-control"
                           name="file">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
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
                            <img src="/img/${message.filename}">
                        </#if>
                    </div>
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@c.page>