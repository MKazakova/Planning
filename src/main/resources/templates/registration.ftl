<!DOCTYPE html>
<html lang="fr">
<#import "models/head.ftl" as h>
<@h.head></@h.head>
<h1>Créer un compte</h1>
<form action="/registration" method="post" class="mform" >
    <label> Login </label> <input type="text" name="login" value="${lg?if_exists}"/> <#if message?has_content> ${message}</#if> <br>
    <label> Mot de passe</label> <input type="password" name="pass"/> <br>
    <label> Saisissez une seconde fois le mot de passe</label> <input type="password" name="pass2"/><#if message2?has_content><span style="color:red;">${message2}</span> </#if><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/><br>
    <input type="submit" value="Valider" class="button"/>
</form>
</body>
</html>