<!DOCTYPE html>
<html lang="fr">
<#import "models/head.ftl" as h>
<#import "models/calendrier.ftl" as c>
<#import "models/author.ftl" as a>
<@h.head></@h.head>
<body>
<@a.author></@a.author>
<#if actions?has_content>
<h3 class="frm">Vos actions pour ${date}</h3>
<table class="frm">
    <tr>
        <th>L'heure</th>
        <th>Action</th>
        <th>Supprimer</th>
    </tr>
  <#list actions as act>
      <tr>
        <td>${act.whenis.toLocalTime()}</td>
        <td>${act.description}</td>
        <td><form action="/supprimerAction" method="post">
                 <input type="hidden" name="date" value="${act.whenis.toLocalDate()}"/>
                 <input type="hidden" name="id" value="${act.id}"/>
                 <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                 <input type="image" src="/css/cross.png"  class="cross" >
            </form>
        </td>
      </tr>
  </#list>
</table>
<#else>
    <p><span class="pasdactions">Vous n'avez pas d'actions prevues pour ${date}</span></p>
</#if>
<a href="/addnote?date=${date}" class="bouton">Ajouter une action</a>
<@c.calendrier></@c.calendrier>
</div>
</body>
</html>