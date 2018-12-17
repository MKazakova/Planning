<!DOCTYPE html>
<html lang="fr">
<#import "models/head.ftl" as h>
<@h.head></@h.head>
<a href="/main" class="home">Page d'accueil</a>
<h1>Modifier l'utilisateur N${auteur.code_autor}</h1>
<div class="divm">
<form action="/auteur" method="post">
    <span class="month"> Login : </span> <input type="text" name="login" value="${auteur.login}"/>
    <span class="month"> Roles : </span>
        <select multiple name="roles">
            <option value="admin">ADMIN</option>
            <option value="user" selected>USER</option>
        </select>
    <span class="month"> Valide : </span>
        <select name="enabled">
            <option value="oui" selected>Valide</option>
            <option value="non">Bloqué</option>
        </select>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="hidden" name="code_autor" value="${auteur.code_autor}"/>
    <input type="submit" value="Valider" class="button"/>
</form>
</div>
</body>
</html>