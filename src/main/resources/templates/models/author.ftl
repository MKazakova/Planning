<#macro author>
    <h1> Agenda </h1>
    <div class="divm">
    <div class="author">
        Vous êtes autorisé en tant que ${login}<br>
        <#if admin><a href="/auteur">Liste des utilisateurs d'Agenda</a></#if>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Déconnecter" class="btn"/>
        </form>
    </div>
</#macro>