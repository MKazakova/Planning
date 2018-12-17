<!DOCTYPE html>
<html lang="fr">
<#import "models/head.ftl" as h>
<@h.head></@h.head>
<body>
<a href="/main" class="home">Page d'accueil</a>
<h1>List des utilisateurs d'Agenda</h1>
<div class="divm">
<table class="lut">
    <tr>
        <th>Code</th>
        <th>Login</th>
        <th>Roles</th>
        <th>Statut</th>
        <th></th>
    </tr>
<#list auteurs as auteur>
    <tr>
        <td>${auteur.code_autor}</td>
        <td>${auteur.login}</td>
        <td><#list auteur.roles as role>
                 ${role}<#sep>,
            </#list>
        </td>
        <td><#if auteur.enabled>Valide<#else>Bloqué</#if></td>
        <td><a href="/auteur/${auteur.code_autor}">editer</a></td>
    </tr>
</#list>
</table>
</div>
</body>
</html>