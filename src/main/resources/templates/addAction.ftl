<!DOCTYPE html>
<html lang="fr">
<#import "models/head.ftl" as h>
<#import "models/calendrier.ftl" as c>
<#import "models/author.ftl" as a>
<@h.head></@h.head>
<body>
<@a.author></@a.author>
<a href="/main" class="home">Page d'accueil</a>
<h3>Ajouter une nouvelle action pour le ${date}</h3>
<form method="post" action="/addnote">
    <span class="frm">L'action aura lieu le</span>
    <select name="timeaction">
        <#list 0..23 as x>
        <option value="${x}">${x}</option>
        </#list>
    </select>
    <input type="text" name="description" placeholder="Decrivez votre action"/>
    <input type="hidden" name="date" value="${date}"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" class="button">Submit</button>
</form>
<@c.calendrier></@c.calendrier>
</div>
</body>
</html>