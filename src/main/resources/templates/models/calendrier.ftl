<#macro calendrier>
<div class="clndr"><span class="year">${date.year}</span><br>
        <a href="/calendrier?date=${date}&&direction=backward">
            <img src="/css/arrow-l.png" class="arrow"></a>
        <span class="month">${date.month}</span>
        <a href="/calendrier?date=${date}&&direction=forward">
            <img src="/css/arrow-r.png" class="arrow"></a>
<table>
    <tr>
        <th>lu</th>
        <th>ma</th>
        <th>me</th>
        <th>je</th>
        <th>ve</th>
        <th>sa</th>
        <th>di</th>
    </tr>
    <tr>
        <#list calendar as cal>
        <td><a href="/main?date=${date}&&day=${cal}">${cal}</a></td>
        <#if cal?index%7==0>
    </tr><tr>
        </#if>
        </#list>
    </tr>
</table>
</div>
</#macro>