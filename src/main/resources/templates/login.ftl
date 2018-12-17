<!DOCTYPE html>
<html lang="fr">
<#import "models/head.ftl" as h>
<@h.head></@h.head>
<body>
        <h1>Autorisation</h1>
        <#if message?has_content> ${message}</#if>
         <form action="login" method="post" class="mform">
              <label> Login </label> <input type="text" name="username"/> <br>
              <label> Mot de Passe </label> <input type="password" name="password"/> <br>
              <input type="hidden" name="_csrf" value="${_csrf.token}"/>
              <input type="submit" value="Sign In" class="button"/>
          </form>
</body>
</html>